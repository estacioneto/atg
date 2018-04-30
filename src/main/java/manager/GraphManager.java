package manager;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

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
		int edges = sc.nextInt();
		Graph graph = new Graph();
		while (sc.hasNext()) {
			String line = sc.nextLine();
			graph.connect(line);
		}
		return graph;
	}

	public Graph readWeightedGraph(String path) {
		Scanner sc = getScanner(path);
		int edges = sc.nextInt();
		Graph graph = new Graph();
		while (sc.hasNext()) {
			String line = sc.nextLine();
			graph.connectWeighted(line);
		}
		return graph;
	}

	public int getVertexNumber(Graph graph) {
		return graph.getNodesAsMap().size();
	}

	public int getEdgeNumber(Graph graph) {
		var nodes = graph.getNodesAsMap();
		var edges = 0;
		for (var connection : nodes.keySet()) {
			edges += nodes.get(connection).size();
		}
		return edges / 2;
	}

	public float getMeanEdge(Graph graph) {
		return (2 * this.getEdgeNumber(graph)) / this.getVertexNumber(graph);
	}

	public String graphRepresentation(Graph graph, String type) {
		// TODO Auto-generated method stub
		return null;
	}

	public String BFS(Graph graph, int v) {
		// TODO Auto-generated method stub
		return null;
	}

	public String DFS(Graph graph, int v) {
		// TODO Auto-generated method stub
		return null;
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
