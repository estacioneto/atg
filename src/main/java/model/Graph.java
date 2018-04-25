package model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Graph {

	private Map<Node, List<Node>> nodes;

	public Graph() {
		this.nodes = new HashMap<Node, List<Node>>();
	}

	private void checkConnect(Node nodeA, Node nodeB) {
		if (!this.nodes.containsKey(nodeA)) {
			this.nodes.put(nodeA, new ArrayList<Node>());
		}
		if (!this.nodes.containsKey(nodeB)) {
			this.nodes.put(nodeB, new ArrayList<Node>());
		}
		this.nodes.get(nodeA).add(nodeB);
		this.nodes.get(nodeB).add(nodeA);
	}

	public void connect(String pair) {
		String[] nodes = pair.split(" ");
		Node nodeA = new Node(nodes[0]);
		Node nodeB = new Node(nodes[1]);
		checkConnect(nodeA, nodeB);
	}

	public void connectWeighted(String triple) {
		String[] nodes = triple.split(" ");
		Node nodeA = new Node(nodes[0], nodes[2]);
		Node nodeB = new Node(nodes[1], nodes[2]);
		checkConnect(nodeA, nodeB);
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
			if (value != other.value)
				return false;
			return true;
		}
	}
}
