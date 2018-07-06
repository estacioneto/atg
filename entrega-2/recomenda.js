/**
 * Args:
 *   p = PID da playlist a se recomendar
 *   q(opcional) = quantidade de músicas a se recomendar
 */

const fs = require('fs');
const args = process.argv;
const getArg = (arg, defaultValue = 0) =>
    (args.filter(x => x.startsWith(`${arg}=`))[0] || `=${defaultValue}`).split('=')[1];

const files = [
    'graph-0-200.json'
    , 'graph-200-400.json'
    , 'graph-400-600.json'
    , 'graph-600-800.json'
    , 'graph-800-1000.json'
];

// from input
const ID_INPUT = getArg('p', '');
const LIMIT_REC = getArg('q', 15);
const GROUP_SIZE = getArg('gz', 20);

if (!ID_INPUT) throw 'Você deve passar uma playlist válida';
// End input

const loadConcatGraph = files => files.map(f => fs.readFileSync(f)).map(JSON.parse).reduce((ac, it) => Object.assign(ac, it), {});
let graph = loadConcatGraph(files);

let playlists = [];
const loadPlaylists = () => {
    let temp = fs.readFileSync('data.json');
    temp = JSON.parse(temp);
    playlists = temp.playlists;
};

// log
const result = {
    playlistEntrada: null,
    playlistBase: null,
    playlistsGrupoRecomendacao: [],
    musicasRecomendadas: []
};

// Algorithm
const getBestMatch = id => {
    const bestMatch = graph[id][0];
    if (bestMatch) return bestMatch.p2;
    throw 'Não existem dados suficientes para a recomendação.';
};

const getSimilarityGroup = (id) => {
    const group = graph[id];
    if (group.length < 5) throw 'Não existem dados suficientes para gerar grupo de similares.';
    return group.slice(0, GROUP_SIZE).map(edge => edge.p2);
};

const getSongRanking = group => group
    // Mapeando id para playlist
    .map(id => playlists.find(p => p.pid == id))
    .reduce((ranking, playlist) => {
        const songs = playlist.tracks;
        songs.forEach(song => {
            ranking[song.track_uri] = ranking[song.track_uri] || { name: song.track_name, artist: song.artist_name, count: 0 };
            ranking[song.track_uri].count++;
        });
        return ranking;
    }, {});

const generateRecommendationFor = (idPlaylistSource, otherSongs) => {
    const sourcePlayList = playlists.find(p => p.pid == idPlaylistSource);
    sourcePlayList.tracks.forEach(song => {
        delete otherSongs[song.track_uri];
    });

    const differentSongs = Object.values(otherSongs).sort((s1, s2) => s2.count - s1.count);
    return differentSongs;
};

const recommend = idPlaylistSource => {
    result.playlistEntrada = idPlaylistSource;

    const bestMatchId = getBestMatch(idPlaylistSource);
    result.playlistBase = bestMatchId;

    const similarityGroup = getSimilarityGroup(bestMatchId);
    result.playlistsGrupoRecomendacao = similarityGroup;

    // liberando memória
    graph = null;
    loadPlaylists();

    const rankSongsMap = getSongRanking(similarityGroup);
    const recommendedOrdered = generateRecommendationFor(idPlaylistSource, rankSongsMap);
    result.musicasRecomendadas = recommendedOrdered;

    return result;
};

(() => {
    const r = recommend(ID_INPUT);

    console.log('');
    console.log('=====================');
    console.log('> Playlist entrada:', result.playlistEntrada);
    console.log('> Playlist de maior compatibilidade:', result.playlistBase);
    console.log('> Grupo de similaridade:', result.playlistsGrupoRecomendacao.join(', '));
    console.log('');
    console.log(`> Recomendações: ${LIMIT_REC}/${result.musicasRecomendadas.length}`);
    console.log(result.musicasRecomendadas.slice(0, LIMIT_REC).map((obj, i) => `${i + 1}. ${obj.name} - ${obj.artist}`).join('\n'));
    console.log('=====================');
    console.log('');
})();