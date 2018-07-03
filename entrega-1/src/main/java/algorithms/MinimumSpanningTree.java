package algorithms;

import model.Graph;

import java.util.Arrays;
import java.util.Set;

public class MinimumSpanningTree {

    public static String Kruskal(Graph graph) {
        var sb = new StringBuilder();
        final var template = "%s - %s | %s" + System.lineSeparator();

        int size = graph.getAllVertexes().size();
        int[] parents = initializeParents(size + 1);

        Graph.Edge[] sortedEdges = getEdgesSorted(graph.getAllEdges());

        int maximumNumberOfEdges = size - 1;

        int edgeIndex = 0;
        int edgeNumber = 0;

        while (edgeNumber < maximumNumberOfEdges) {
            Graph.Edge actualEdge = sortedEdges[edgeIndex];

            int src = actualEdge.getStart();
            int dest = actualEdge.getEnd();

            int srcParent = find(parents, src);
            int destParent = find(parents, dest);

            boolean belongToSameSubset = (srcParent == destParent);

            if (!belongToSameSubset) {
                edgeNumber++;
                union(parents, srcParent, destParent);
                sb.append(String.format(template, src, dest, edgeNumber));
            }

            edgeIndex++;
        }

        return sb.toString();
    }

    private static Graph.Edge[] getEdgesSorted(Set<Graph.Edge> edges) {
        var sortedEdges = edges.toArray(new Graph.Edge[edges.size()]);
        Arrays.sort(sortedEdges);

        return sortedEdges;
    }

    private static int find(int[] parents, int v) {
        if (parents[v] == -1) {
            return v;
        }

        return find(parents, parents[v]);
    }

    private static void union(int[] parents, int x, int y) {
        int xset = find(parents, x);
        int yset = find(parents, y);
        parents[xset] = yset;
    }

    private static int[] initializeParents(int size) {
        int[] array = new int[size];

        for (int i = 0; i < size; i++) {
            array[i] = -1;
        }

        return array;
    }
}
