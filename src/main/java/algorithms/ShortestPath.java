package algorithms;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

import model.Graph;

public class ShortestPath {

    static final int ZERO = 0;

    public static String BellmanFord(Graph graph, int v1, int v2) {

        HashMap<Integer, Double> distances = new HashMap<>();
        HashMap<Integer, Integer> parents = new HashMap<>();

        for (Integer vertex : graph.getAllVertexes()) {
            Double value = vertex == v1 ? ZERO : Double.POSITIVE_INFINITY;
            distances.put(vertex, value);
            parents.put(vertex, vertex);
        }

        for (Integer current : graph.getAllVertexes()) {

            Double currentWeight = distances.get(current);
            if (currentWeight == Double.POSITIVE_INFINITY) continue;


            for (Graph.Edge edge : graph.getAllEdgesFromVertex(current)) {
                int connected = edge.getStart() != current ? edge.getStart() : edge.getEnd();
                if (connected == v1) continue;

                Double weight = edge.getWeight();
                Double newDistance = currentWeight + weight;
                if (distances.get(connected) > newDistance) {
                    distances.put(connected, newDistance);
                    parents.put(connected, current);
                }
            }
        }

        List<String> arr = new ArrayList<>();
        Integer temp = v2;
        do {
        	arr.add(temp.toString());
            temp = parents.get(temp);
        } while(!temp.equals(parents.get(temp)));
        
        arr.add(temp.toString());

        Collections.reverse(arr);
        return String.join(" ", arr);
    }
}
