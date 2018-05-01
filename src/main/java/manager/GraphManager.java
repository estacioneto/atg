package manager;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import algorithms.DeepSearch;
import algorithms.BreadthSearch;
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
		Graph graph = new Graph();
		while (sc.hasNext()) {
			String line = sc.nextLine();
			graph.connect(line);
		}
		return graph;
	}

	public Graph readWeightedGraph(String path) {
		Scanner sc = getScanner(path);
		String edges = sc.nextLine();
		Graph graph = new Graph();
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
		// TODO Auto-generated method stub
		return null;
	}

	public String BFS(Graph graph, int init) {
		return BreadthSearch.BFS(graph, init);
	}
	
	public String DFS(Graph graph, int init) {
		return DeepSearch.DFS(graph, init);
	}

	public String SCC(Graph graph) {
		// TODO Auto-generated method stub
		return null;
	}

	public String shortestPath(int v1, int v2) {
		// TODO Auto-generated method stub
		return null;
	}

	public String mst(Graph graph) {
		// TODO Auto-generated method stub
		return null;
	}

}
