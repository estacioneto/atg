package manager;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Scanner;
import java.util.Set;

import javafx.util.Pair;
import model.Graph;
import model.Graph.Edge;

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
		var sb = new StringBuilder();
		var visited = new HashSet<Integer>();
		var queue = new LinkedList<Pair<Integer, Integer>>();
		queue.addLast(new Pair<>(init, 1));
		visited.add(init);
		
		final var template = "%s - %s %s" + System.lineSeparator();
		sb.append(String.format(template, init, "-", 0));
		while (!queue.isEmpty()) {
			var next = queue.pollFirst();
			for (Integer child : graph.getConnected(next.getKey())) {
				if (visited.contains(child)) {
					continue;
				}
				visited.add(child);
				queue.add(new Pair<>(child, next.getValue() + 1));
				sb.append(String.format(template, child, next.getKey(), next.getValue()));
			}
		}
		
		return sb.toString();
	}

	private String innerDFS(Graph graph, Integer node, Integer parent, int depth, Set<Integer> visited) {
		var sb = new StringBuilder();
		final var template = "%s - %s %s" + System.lineSeparator();
		sb.append(String.format(template, node, parent == null ? "-" : parent, depth));
		visited.add(node);
		for (Integer child : graph.getConnected(node)) {
			if (visited.contains(child)) { continue; }
			sb.append(innerDFS(graph, child, node, depth + 1, visited));
		}
		return sb.toString();
	}
	
	public String DFS(Graph graph, int init) {
		return innerDFS(graph, init, null, 0, new HashSet<Integer>());
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
