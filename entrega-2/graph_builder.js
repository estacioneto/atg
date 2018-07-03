const input = require('./data.json');//.playlists.slice(0,300);

// Grafo 
const graph = {};

const getArtists = p => p.tracks.map(t => t.artist_uri);
const getId = p => p.pid;
const getSongs = p => p.tracks.map(t => t.track_uri);

/**
 * @param {Playlist} p1
 * @param {Playlist} p2
 * @param {Function(p: Playlist): [String]} fn 
 */
const fastProportion = (p1, p2, fn) => {
    const from = fn(p1);
    const to = fn(p2);
     
    let total = from.length;
    const tempMap = to.reduce((ac, it) => ({ [it]: 1 }), {});
    const both = from.reduce((ac, it) => {
        const matched = tempMap[it];
        if (matched == 1) {
            tempMap[it] = 2;
            ac++;
        } else if (matched == 2) {
            total--;
        }
        return ac;
    }, 0);

    return both / total;
};

const getSimilarityByArtist = (p1, p2) => {
    return fastProportion(p1, p2, getArtists);
};

const getSimilarityBySongs = (p1, p2) => {
    return fastProportion(p1, p2, getSongs);
};

/**
 * Calcula a similaridade entre duas playlists.
 * 
 * São utilizados como critérios para comparação de 
 * similaridade os artistas e músicas em comum, partindo
 * da playlist atual (p1).
 * 
 * A similaridade da P1 para P2 não necessariamente é igual 
 * a similaridade de P2 para P1.
 * 
 * Similaridade de artistas (Sa): proporção de artistas de P1 
 *      que estão em P2 sobre o total de artistas em P1
 * Similaridade de músicas (Sm): proporção de músicas de P1 
 *      que estão em P2 sobre o total de músicas em P1
 * 
 * Similaridade total: Sa + ((1 - Sa) * Sm)
 * 
 * A similaridade de músicas entra como "bonus" a similaridade de artistas
 */
const getSimilarity = (p1, p2) => {
    const similarityByArtist = getSimilarityByArtist(p1, p2);
    const similarityBySong = getSimilarityBySongs(p1, p2);

    const maxBoostSimilarity = 1 - similarityByArtist;
    const boostBySongsSimilarity = maxBoostSimilarity * similarityBySong;

    const calculated = similarityByArtist + boostBySongsSimilarity;
    calculated > 1 && console.log(`<<< SA: ${similarityByArtist}, SS: ${similarityBySong}`);
    return 100 * calculated;
};

// Playlist1, Playlist2, Similarity
const makeNode = (p1, p2) => ({
    p1: getId(p1),
    p2: getId(p2),
    s: getSimilarity(p1, p2)
});

const main = () => {
    console.log('Iniciando processamento de', input.length, 'playlists');
    const parte = Math.floor(input.length / 10);

    input.forEach((playlist, index) => {
        const links = [];
        graph[getId(playlist)] = links;
        
        !(index % parte) && console.log(`>> Processando ${index}/${input.length}`);

        input.forEach(playlistAux => {
            if (playlist === playlistAux) return;
    
            const node = makeNode(playlist, playlistAux);
            node.s > 0 && links.push(node);
        });

        links.sort((a, b) => b.s - a.s);
    });
};

try { 
    main();
} catch (e) {
    console.log('Extração de dados falhou com', e);
    console.log('Finalizando, salvando dados processados.');
}

require('fs').writeFileSync('graph.json', JSON.stringify(graph, null, 2));