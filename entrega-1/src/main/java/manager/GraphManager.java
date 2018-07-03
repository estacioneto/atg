package manager;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import algorithms.BreadthSearch;
import algorithms.GraphRepresentation;
import algorithms.Connected;
import algorithms.DeepSearch;
import algorithms.MinimumSpanningTree;
import algorithms.ShortestPath;
import model.Graph;

public class GraphManager implements GraphManagerInterface {

	private Scanner getScanner(String path) {
		File file = new File(path);
		try {
			return new Scanner(file);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public Graph readGraph(String path) {
		Scanner sc = getScanner(path);
		String edges = sc.nextLine();
		Graph graph = new Graph(false);
		while (sc.hasNext()) {
			String line = sc.nextLine();
			graph.connect(line);
		}

		return graph;
	}

	public Graph readWeightedGraph(String path) {
		Scanner sc = getScanner(path);
		String edges = sc.nextLine();
		Graph graph = new Graph(true);
		while (sc.hasNext()) {
			String line = sc.nextLine();
			graph.connectWeighted(line);
		}

		return graph;
	}

	public int getVertexNumber(Graph graph) {
		return graph.getAllVertexes().size();
	}
	public int getEdgeNumber(Graph graph) {
		return graph.getAllEdges().size();
	}

	public float getMeanEdge(Graph graph) {
		return (2 * this.getEdgeNumber(graph)) / this.getVertexNumber(graph);
	}

	public String graphRepresentation(Graph graph, String type) {
		switch (type){
			case "AL":
				return GraphRepresentation.graphRepresentationAL(graph);
			case "AM":
				return GraphRepresentation.graphRepresentationAM(graph);
			default:
				return null;
		}
	}

	public String BFS(Graph graph, int init) {
		return BreadthSearch.BFS(graph, init);
	}
	
	public String DFS(Graph graph, int init) {
		return DeepSearch.DFS(graph, init);
	}

	public boolean connected(Graph graph) {
		return Connected.connected(graph);
	}

	public String shortestPath(Graph graph, int v1, int v2) {
		return ShortestPath.BellmanFord(graph, v1, v2);
	}

	public String mst(Graph graph) {
		return MinimumSpanningTree.Kruskal(graph);
	}

}
