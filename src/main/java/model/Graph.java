package model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Graph {

	private Map<Node, List<Node>> nodes;

	public Graph() {
		this.nodes = new HashMap<>();
	}

	private void checkConnect(Node nodeA, Node nodeB) {
		if (!this.nodes.containsKey(nodeA)) {
			this.nodes.put(nodeA, new ArrayList<>());
		}
		if (!this.nodes.containsKey(nodeB)) {
			this.nodes.put(nodeB, new ArrayList<>());
		}
		this.nodes.get(nodeA).add(nodeB);
		this.nodes.get(nodeB).add(nodeA);
	}

	public void connect(String pair) {
		var nodes = pair.split(" ");
		var nodeA = new Node(nodes[0]);
		var nodeB = new Node(nodes[1]);
		checkConnect(nodeA, nodeB);
	}

	public void connectWeighted(String triple) {
		var nodes = triple.split(" ");
		var nodeA = new Node(nodes[0], nodes[2]);
		var nodeB = new Node(nodes[1], nodes[2]);
		checkConnect(nodeA, nodeB);
	}

	public Map<Node, List<Node>> getNodesAsMap() {
		return this.nodes;
	}

	public int[][] getNodesAsMatrix() {
		int matrix[][] = new int[this.nodes.size() + 1][this.nodes.size() + 1];
		for (var node : this.nodes.keySet()) {
			for (var connection : this.nodes.get(node)) {
				matrix[node.value][connection.value]++;
			}
		}
		return matrix;
	}

	private class Node {
		int weight;
		int value;

		Node(String value) {
			this.value = Integer.parseInt(value);
		}

		Node(String value, String weight) {
			this(value);
			this.weight = Integer.parseInt(weight);
		}

		@Override
		public int hashCode() {
			final int prime = 31;
			int result = 1;
			result = prime * result + value;
			return result;
		}

		@Override
		public boolean equals(Object obj) {
			if (this == obj)
				return true;
			if (obj == null)
				return false;
			if (getClass() != obj.getClass())
				return false;
			Node other = (Node) obj;
			return value == other.value;
		}
	}
}
