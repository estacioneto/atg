package resources;

import manager.GraphManager;
import model.Graph;

public class GraphManagerTest {
	
	public static void main(String[] args) {
		GraphManager manager = new GraphManager();
		Graph graph = manager.readGraph("src/test/java/resources/case1.txt");
		Graph graph2 = manager.readWeightedGraph("src/test/java/resources/case2.txt");

		System.out.println(">> Should have 5 vertexes: " + manager.getVertexNumber(graph));
		// existem 2 vertices iguais, apenas invertidos
		System.out.println(">> Should have 4 edges: " + manager.getEdgeNumber(graph));
		
		System.out.println(">> BFS");
		System.out.println(manager.BFS(graph, 1));
		
		System.out.println(">> DFS");
		System.out.println(manager.DFS(graph, 1));

		System.out.println(">> Shortest Path");
		System.out.println(manager.shortestPath(1, 5) + System.lineSeparator());

		System.out.println(">> MST");
		System.out.println(manager.mst(graph2));
		
		System.out.println(manager.connected(graph));
	}
}
