package algorithms;

import model.Graph;

import java.util.HashSet;
import java.util.Set;

public class DeepSearch {
    public static String DFS(Graph graph, int init) {
        return DeepSearch.innerDFS(graph, init, null, 0, new HashSet<>());
    }

    private static String innerDFS(Graph graph, Integer node, Integer parent, int depth, Set<Integer> visited) {
        var sb = new StringBuilder();
        final var template = "%s - %s %s" + System.lineSeparator();
        sb.append(String.format(template, node, parent == null ? "-" : parent, depth));
        visited.add(node);
        for (Integer child : graph.getConnected(node)) {
            if (visited.contains(child)) {
                continue;
            }
            sb.append(innerDFS(graph, child, node, depth + 1, visited));
        }
        return sb.toString();
    }
}
