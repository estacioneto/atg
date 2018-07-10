/**
 * Args:
 *   p = PID da playlist a se recomendar
 *   q(opcional) = quantidade de músicas a se recomendar
 */

const args = process.argv;
const getArg = (arg, defaultValue = 0) =>
    (args.filter(x => x.startsWith(`${arg}=`))[0] || `=${defaultValue}`).split('=')[1];

const files = [
    './graph-0-200.json'
    , './graph-200-400.json'
    , './graph-400-600.json'
    , './graph-600-800.json'
    , './graph-800-1000.json'
];

const playlistsData = './data.json';

// from input
// const ID_INPUT = getArg('p', '');
const LIMIT_REC = getArg('q', 15);
const GROUP_SIZE = getArg('gz', 20);

// if (!ID_INPUT) throw 'Você deve passar uma playlist válida';
// End input

const loadConcatGraph = files => files.map(f => require(f)).reduce((ac, it) => Object.assign(ac, it), {});
let graph = loadConcatGraph(files);

let playlists = [];
const loadPlaylists = () => {
    let temp = require(playlistsData);
    playlists = temp.playlists;
};
loadPlaylists();

// Algorithm
const getBestMatch = id => {
    const bestMatch = graph[id][0];
    if (bestMatch) return bestMatch;
    throw 'Não existem dados suficientes para a recomendação.';
};

const getSimilarityGroup = (id) => {
    const group = graph[id];
    if (group.length < 5) throw 'Não existem dados suficientes para gerar grupo de similares.';
    return group.slice(0, GROUP_SIZE);
};

const getSongRanking = group => group
    // Mapeando id para playlist
    .map(id => playlists.find(p => p.pid == id))
    .reduce((ranking, playlist) => {
        const songs = playlist.tracks;
        songs.forEach(song => {
            ranking[song.track_uri] = ranking[song.track_uri]
                || Object.assign({}, song, { count: 0 });
            ranking[song.track_uri].count++;
        });
        return ranking;
    }, {});

const generateRecommendationFor = (idPlaylistSource, otherSongs) => {
    const sourcePlayList = playlists.find(p => p.pid == idPlaylistSource);
    sourcePlayList.tracks.forEach(song => {
        delete otherSongs[song.track_uri];
    });

    const differentSongs = Object.keys(otherSongs)
        .map(k => otherSongs[k])
        .sort((s1, s2) => s2.count - s1.count);
    return differentSongs;
};

const recommend = idPlaylistSource => {
    const result = {
        inputPlaylist: null,
        basePlaylist: null,
        recommendationGroup: [],
        recommendedSongs: []
    };

    const mapPlaylist = id => playlists.find(p => p.pid == id);
    result.inputPlaylist = mapPlaylist(idPlaylistSource);

    const bestMatch = getBestMatch(idPlaylistSource);
    const bestMatchId = bestMatch.p2;
    result.basePlaylist = mapPlaylist(bestMatchId);
    result.basePlaylist.similarity = bestMatch.s;

    const similarityGroup = getSimilarityGroup(bestMatchId);
    const similarityGroupIds = similarityGroup.map(edge => edge.p2);
    result.recommendationGroup = similarityGroupIds
        .map((pId, index) => Object.assign({}, mapPlaylist(pId), { similarity: similarityGroup[index].s }));

    const rankSongsMap = getSongRanking(similarityGroupIds);
    const recommendedOrdered = generateRecommendationFor(idPlaylistSource, rankSongsMap);
    result.recommendedSongs = recommendedOrdered.slice(0, GROUP_SIZE);

    return result;
};

module.exports.recommend = recommend;
