package algorithms;

import java.util.HashSet;
import java.util.Set;

import model.Graph;

public class Connected {
	public static boolean connected(Graph graph) {
		var visited = new HashSet<Integer>();
		var vertexes = graph.getAllVertexes();
		if (vertexes.isEmpty()) return false;
		DFS(vertexes.iterator().next(), graph, visited);
		return visited.size() == vertexes.size();
	}
	
	private static void DFS(int actual, Graph graph, Set<Integer> visited) {
		visited.add(actual);
		graph.getConnected(actual).stream()
			.forEach(vertex -> {
				if (!visited.contains(vertex)) {
					DFS(vertex, graph, visited);
				}
			});
	}
}
