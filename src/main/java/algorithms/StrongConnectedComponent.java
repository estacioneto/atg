package algorithms;

import java.util.Arrays;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import model.Graph;

public class StrongConnectedComponent {

	public static String SCC(Graph graph) {
		var vertexes = graph.getAllVertexes();
		var allComps = List.of(vertexes.stream()
					.map(vertex -> {
						var components = DFS(vertex, vertex, graph, new HashSet<Integer>(), new HashSet<>());
						if (components.size() == 0) {
							components.add(Set.of(vertex));
						}
						return components;
					})
					.flatMap(Set::stream)
					.toArray());
		
		Comparator<Set> comparator = (Set a, Set b) -> b.size() - a.size();
		var sortedComponents = new HashSet((allComps))
				.stream()
				.sorted(comparator)
				.map(comp -> ((Set) comp).stream().map(e -> e.toString()).collect(Collectors.toCollection(HashSet<String>::new)))
				.reduce("", (ac,e) -> ac + String.join(" ", ((Set<String>) e)) + System.lineSeparator())
				.toString();

		return sortedComponents;
	}

	private static Set<Set<Integer>> DFS(int actual, int start, Graph graph, Set<Integer> visited, Set<Integer> path) {
		path.add(actual);
		var out = new HashSet<Set<Integer>>();
		visited.add(actual);

		graph.getConnected(actual).stream().forEach(vertex -> {
			var thisPath = new HashSet<Integer>();
			thisPath.addAll(path);
			thisPath.add(vertex);

			if (!visited.contains(vertex)) {
				var innerOut = DFS(vertex, start, graph, visited, thisPath);
				out.addAll(innerOut);
			} else if (thisPath.size() > 2 && vertex == start) {
				out.add(thisPath);
			}
		});
		
		visited.remove(actual);
		return out;
	}
}
