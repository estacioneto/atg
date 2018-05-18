package model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

public class Graph {

	private Map<Integer, Set<Edge>> nodes;

	private Boolean weighted;

	public Graph() { 
		this.nodes = new HashMap<>();
		this.weighted = false;
	}

	/**
	 * Construtor com parâmetro `weighted`
	 * 
	 * @param {Boolean} weighted - Indica se o grafo tem pesos nas arestas
	 */
	public Graph(Boolean weighted) { 
		this.nodes = new HashMap<>();
		this.weighted = weighted;
	}
	
	public Boolean isWeighted(){
		return weighted;
	}

	public Set<Integer> getConnected(Integer node) {
		return nodes.get(node).stream()
				.map(edge -> node == edge.start ? edge.end : edge.start)
				.collect(Collectors.toSet());
	}

	private void checkConnect(int nodeA, int nodeB, Double weight) {
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

		checkConnect(nodeA, nodeB, 1.0);
	}

	public void connectWeighted(String triple) {
		var nodes = triple.split(" ");
		var nodeA = Integer.parseInt(nodes[0]);
		var nodeB = Integer.parseInt(nodes[1]);
		var weight = Double.parseDouble(nodes[2]);

		checkConnect(nodeA, nodeB, weight);
	}

	public Map<Integer, Set<Edge>> getGraphAsMap() {
		return this.nodes;
	}

	public List<Integer> getOrderedNodes(){
		return new ArrayList<Integer>(this.nodes.keySet());
	}

	/**
	 * Gera matrix de adjacência dos vértices, adicionando os pesos das arestas, caso existam.
	 * 
	 * Caso o peso seja 1.0 (utilizado como padrão para grafos sem pesos), é adicionado apenas 1
	 * na matriz
	 * 
	 * @return {double[][]} matriz de adjacência
	 */
	public double[][] getNodesAsMatrix() {
		double matrix[][] = new double[this.nodes.size() + 1][this.nodes.size() + 1];
		var edges = this.getAllEdges();
		for (var edge : edges) {
			if (edge.weight != 1.0){
				matrix[edge.start][edge.end] = edge.weight;
				matrix[edge.end][edge.start] = edge.weight;
			}else {
				matrix[edge.start][edge.end]++;
				matrix[edge.end][edge.start]++;
			}
		}
		return matrix;
	}

	public Set<Edge> getAllEdgesFromVertex(Integer v) {
		return this.nodes.get(v);
	}

	public Set<Edge> getAllEdges() {
		return this.nodes.values().stream()
				.flatMap(Set::stream)
				.collect(Collectors.toSet());
	}

	
	public Set<Integer> getAllVertexes() {
		return this.nodes.keySet();
	}

	public static class Edge implements Comparable<Edge> {

		public double getWeight() {
			return weight;
		}

		public int getStart() {
			return start;
		}

		public int getEnd() {
			return end;
		}

		private double weight;
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

		Edge(int start, int end, Double weight) {
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

		@Override
		public int compareTo(Edge edge) {
			return Double.compare(this.weight, edge.getWeight());
		}
	}
}
