package algorithms;

import model.Graph;

import java.util.HashMap;

public class BellmanFord {

    static final int ZERO = 0;

    public static String BF(Graph graph, int v1, int v2) {

        HashMap<Integer, Double> distances = new HashMap<>();

        for (Integer vertex : graph.getAllVertexes()) {
            Double value = vertex == v1 ? ZERO : Double.POSITIVE_INFINITY;
            distances.put(vertex, value);
        }

        for (Integer current : graph.getAllVertexes()) {

            Double currentWeight = distances.get(current);
            if (currentWeight == Double.POSITIVE_INFINITY) continue;


            for (Graph.Edge edge : graph.getAllEdgesFromVertix(current)) {
                int connected = edge.getStart() != current ? edge.getStart() : edge.getEnd();
                if (connected == v1) continue;

                Double weight = edge.getWeight();

                Double value = Math.min(distances.get(connected), currentWeight + weight);
                distances.put(connected, value);
            }
        }

        // TODO: retornar o caminho, e nao o custo
        return distances.get(v2).toString();
    }
}
