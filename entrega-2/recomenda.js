const fs = require('fs');

// from input
const files = [
    'graph-0-200.json'
    , 'graph-200-400.json'
    , 'graph-400-600.json'
    , 'graph-600-800.json'
    , 'graph-800-1000.json'
];
// from input
const id = '26500';

const loadConcatGraph = files => files.map(f => fs.readFileSync(f)).map(JSON.parse).reduce((ac, it) => Object.assign(ac, it), {});
let graph = loadConcatGraph(files);

let playlists = [];
const loadPlaylists = () => {
    let temp = fs.readFileSync('data.json');
    temp = JSON.parse(temp);
    playlists = temp.playlists;
};

const getBestMatch = id => {
    const bestMatch = graph[id][0];
    if (bestMatch) return bestMatch.p2;
    throw 'Não existem dados suficientes para a recomendação.';
};

const getSimilarityGroup = id => {
    const group = graph[id];
    if (group.length < 5) throw 'Não existem dados suficientes para gerar grupo de similares.';
    return group.slice(0, 20).map(edge => edge.p2);
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

const recomendaPara = idPlaylistSource => {
    const bestMatchId = getBestMatch(idPlaylistSource);
    const similarityGroup = getSimilarityGroup(bestMatchId);

    // liberando memória
    graph = null;
    loadPlaylists();

    const rankSongsMap = getSongRanking(similarityGroup);
    const recommendedOrdered = generateRecommendationFor(idPlaylistSource, rankSongsMap);
    return recommendedOrdered;
};

recomendaPara(id);