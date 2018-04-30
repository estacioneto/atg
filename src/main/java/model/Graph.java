package model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

public class Graph {

    private Map<Integer, List<Edge>> nodes;

    public Graph() {
        this.nodes = new HashMap<>();
    }

    private void checkConnect(int nodeA, int nodeB, int weight) {
        if (!this.nodes.containsKey(nodeA)) {
            this.nodes.put(nodeA, new ArrayList<>());
        }
        if (!this.nodes.containsKey(nodeB)) {
            this.nodes.put(nodeB, new ArrayList<>());
        }

        this.nodes.get(nodeA).add(new Edge(nodeA, nodeB, weight));
        this.nodes.get(nodeB).add(new Edge(nodeB, nodeA, weight));
    }

    public void connect(String pair) {
        var nodes = pair.split(" ");
        var nodeA = Integer.parseInt(nodes[0]);
        var nodeB = Integer.parseInt(nodes[1]);

        checkConnect(nodeA, nodeB, 1);
    }

    public void connectWeighted(String triple) {
        var nodes = triple.split(" ");
        var nodeA = Integer.parseInt(nodes[0]);
        var nodeB = Integer.parseInt(nodes[1]);
        var weight = Integer.parseInt(nodes[2]);

        checkConnect(nodeA, nodeB, weight);
    }

    public Map<Integer, List<Edge>> getGraphAsMap() {
        return this.nodes;
    }

    public int[][] getNodesAsMatrix() {
        int matrix[][] = new int[this.nodes.size() + 1][this.nodes.size() + 1];
        var edges = this.getAllEdges();
        for (var edge : edges) {
            matrix[edge.start][edge.end]++;
        }
        return matrix;
    }

    public List<Edge> getAllEdges() {
        return this.nodes.values().stream().flatMap(List::stream).collect(Collectors.toList());
    }

    private class Edge {
        int weight;
        int start, end;

        Edge(int start, int end) {
            this.start = start;
            this.end = end;
        }

        Edge(int start, int end, int weight) {
            this(start, end);
            this.weight = weight;
        }

        @Override
        public int hashCode() {
            return Objects.hash(weight, start, end);
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Edge edge = (Edge) o;
            return weight == edge.weight &&
                    start == edge.start &&
                    end == edge.end;
        }
    }
}
