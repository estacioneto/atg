// invocar com "node graph_builder.js inicio=0 fim=100" para definir partição
const input = require('./source_data/data.json');
const playlists = input.playlists;

const args = process.argv;
const getArg = (arg, defaultValue = 0) =>
  (args.filter(x => x.startsWith(`${arg}=`))[0] || `=${defaultValue}`).split(
    '='
  )[1];

const inicioParticao = Number(getArg('inicio', 0));
const fimParticao = Number(getArg('fim', playlists.length));
const maxVertices = Number(getArg('vertices', Number.MAX_SAFE_INTEGER)); // only need 80
const maxArestas = Number(getArg('arestas', Number.MAX_SAFE_INTEGER)); // only need 120

const status = {
  vertices: 0,
  arestas: 0,
};

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
const fastProportion = (p1, p2, fn, rankValue) => {
  const from = fn(p1);
  const to = fn(p2);

  // let total = from.length;
  const tempMap = to.reduce((ac, it) => {
    ac[it] = 1;
    return ac;
  }, {});
  const rankTotal = from.reduce((ac, it) => {
    const matched = tempMap[it];
    if (matched == 1) {
      tempMap[it] = 2;
      ac += rankValue;
    } else if (matched == 2) {
      // total--;
    }
    return ac;
  }, 0);

  return rankTotal;
};

const getSimilarityByArtist = (p1, p2) => {
  return fastProportion(p1, p2, getArtists, 1);
};

const getSimilarityBySongs = (p1, p2) => {
  return fastProportion(p1, p2, getSongs, 2);
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
 * Similaridade total: Sa * 0.7 + Sm * 0.3
 */
const getSimilarity = (p1, p2) => {
  const similarityRankArtist = getSimilarityByArtist(p1, p2);
  const similarityRankSong = getSimilarityBySongs(p1, p2);

  const calculated = similarityRankArtist + similarityRankSong;
  return calculated;
};

// Playlist1, Playlist2, Similarity
const makeNode = (p1, p2) => ({
  p1: getId(p1),
  p2: getId(p2),
  s: getSimilarity(p1, p2),
});

const main = () => {
  console.log(
    'Iniciando processamento de',
    fimParticao - inicioParticao,
    'playlists'
  );
  const parte = Math.floor((fimParticao - inicioParticao) / 10);

  graphBuild: for (let index = inicioParticao; index < fimParticao; index++) {
    const playlist = playlists[index];
    const links = [];
    status.vertices++;

    !(index % parte) &&
      console.log(`>> Processando ${index}/${fimParticao - inicioParticao}`);
    // console.log(
    //   `- Foram processados ${status.vertices} vertices e ${
    //     status.arestas
    //   } arestas`
    // );

    for (const playlistAux of playlists) {
      if (playlist === playlistAux) continue;
      if (status.vertices >= maxVertices && status.arestas >= maxArestas)
        break graphBuild;

      const node = makeNode(playlist, playlistAux);
      if (node.s > 0) {
        links.push(node);
        status.arestas++;
      }
    }

    links.sort((a, b) => b.s - a.s);
    // As 30 mais semelhantes
    graph[getId(playlist)] = links.slice(0, 30);
  }
};

try {
  main();
} catch (e) {
  console.log('Extração de dados falhou com', e);
  console.log('Finalizando, salvando dados processados.');
}

require('fs').writeFileSync(
  `temp_graph/graph-${inicioParticao}-${fimParticao}.json`,
  JSON.stringify(graph, null, 2)
);
