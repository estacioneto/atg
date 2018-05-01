package algorithms;

import javafx.util.Pair;
import model.Graph;

import java.util.HashSet;
import java.util.LinkedList;

public class Dijkstra {
    public static String BFS(Graph graph, int init) {
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
}
