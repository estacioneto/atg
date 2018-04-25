package manager;

import model.Graph;

/**
 * Graph Manager Interface. The library facade.
 */
public interface GraphManagerInterface {

    /**
     * According to the specification:
     *
     * "A biblioteca deve ler um grafo a partir de um arquivo de texto. O
     * grafo será descrito segundo o seguinte formato: a primeira linha informa o número
     * de vértices do grafo. Cada linha subsequente informa as arestas do mesmo."
     *
     * @param path Path to the input file
     */
    Graph readGraph(String path);

    /**
     * According to the specification:
     *
     * "Grafos com pesos. A biblioteca deve ser capaz de
     * representar e manipular grafos não-direcionados que possuam pesos nas arestas. Os
     * pesos, que serão representados por valores reais, devem estar associados às arestas.
     * O arquivo de entrada agora terá uma terceira coluna, que representa o peso da aresta."
     *
     * @param path Path to the input file
     */
    Graph readWeightedGraph(String path);

    /*
     * The following methods:
     *
     * "A biblioteca deve ser calcular asseguintes informações sobre o grafo: número
     * de vértices, número de arestas e grau médio."
     *
     */

    int getVertexNumber(Graph graph);

    int getEdgeNumber(Graph graph);

    float getMeanEdge(Graph graph);

    /**
     * According to the specification:
     *
     * "Representação de grafos. A biblioteca deve
     * ser capaz de representar grafos utilizando tanto uma matriz de adjacência (type =
     * “AM”), quanto uma lista de adjacência (type = “AL"). O usuário poderá escolher a
     * representação a ser utilizada."
     *
     * @param graph Graph to be represented.
     * @param type Type of representation.
     * @return Graph representation.
     */
    String graphRepresentation(Graph graph, String type);

    /*
     * The following methods:
     *
     * "Busca em grafos: largura e profundidade. A
     * biblioteca deve ser capaz de percorrer o grafo utilizando busca em largura e busca em
     * profundidade. O vértice inicial será fornecido pelo usuário da biblioteca. A respectiva
     * árvore de busca deve ser gerada assim como o nível de cada vértice na árvore (nível
     * da raiz é zero). Para descrever a árvore gerada, basta informar o pai de cada vértice e
     * seu nível na String de saída."
     *
     */

    String BFS(Graph graph, int v);

    String DFS(Graph graph, int v);

    /**
     * According to the specification:
     *
     * "Componentes fortemente conectados. A biblioteca deve ser capaz
     * descobrir os componentes fortemente conexos de um grafo. Saída: cada linha contém
     * a lista de vértices pertencentes a um componente. Os componentes devem estar
     * listados em ordem decrescente de tamanho (listar primeiro o componente com o
     * maior número de vértices, etc)."
     *
     * @param graph Graph to be analysed
     * @return The SCC representation.
     */
    String SCC(Graph graph);

    /**
     * According to the specification:
     *
     * "Caminho mínimo. A biblioteca deve ser capaz de
     * encontrar o caminho mais curto entre dois vértices."
     *
     * @param v1 Start vertex
     * @param v2 End vertex
     * @return Shortest path representation.
     */
    String shortestPath(int v1, int v2);

    /**
     * According to the specification:
     *
     * "Arvore geradora mínima. A biblioteca deve ser capaz de encontrar
     * uma árvore geradora mínima de um grafo. Você deve escolher um algoritmo
     * apropriado para este problema. Para descrever a árvore gerada, basta informar o pai
     * de cada vértice e seu nível na String de saída (semelhante ao método BFS(graph, v))."
     *
     * @param graph Graph to be analysed
     * @return The minimum spanning tree represented as String.
     */
    String mst(Graph graph);

}
