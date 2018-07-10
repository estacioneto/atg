const files = [
      './temp_graph/graph-0-200.json'
    , './temp_graph/graph-200-400.json'
    , './temp_graph/graph-400-600.json'
    , './temp_graph/graph-600-800.json'
    , './temp_graph/graph-800-1000.json'
];

const loadConcatGraph = files => files.map(f => require(f)).reduce((ac, it) => Object.assign(ac, it), {});
let graph = loadConcatGraph(files);

const output = '../graph/graph.json';
require('fs').writeFileSync(output, JSON.stringify(graph, null, 2));