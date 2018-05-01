package model;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

public class Graph {

	private Map<Integer, Set<Edge>> nodes;

	public Graph() {
		this.nodes = new HashMap<>();
	}
	
	public Set<Integer> getConnected(Integer node) {
		return nodes.get(node).stream()
				.map(edge -> node == edge.start ? edge.end : edge.start)
				.collect(Collectors.toSet());
	}

	private void checkConnect(int nodeA, int nodeB, int weight) {
		if (!this.nodes.containsKey(nodeA)) {
			this.nodes.put(nodeA, new HashSet<>());
		}
		if (!this.nodes.containsKey(nodeB)) {
			this.nodes.put(nodeB, new HashSet<>());
		}

		var edge = new Edge(nodeA, nodeB, weight);
		this.nodes.get(nodeA).add(edge);
		this.nodes.get(nodeB).add(edge);
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

	public Map<Integer, Set<Edge>> getGraphAsMap() {
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

	public Set<Edge> getAllEdges() {
		return this.nodes.values().stream()
				.flatMap(Set::stream)
				.collect(Collectors.toSet());
	}

	
	public Set<Integer> getAllVertexes() {
		return this.nodes.keySet();
	}

	public static class Edge {
		private int weight;
		private int start, end;

		Edge(int start, int end) {
			if (start > end) {
				var temp = start;
				start = end;
				end = temp;
			}
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
