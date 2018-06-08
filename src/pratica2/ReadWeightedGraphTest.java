package pratica2;

import static org.junit.jupiter.api.Assertions.*;

import java.io.FileNotFoundException;

import org.junit.Test;

import exception.GraphLibraryException;
import library.GraphLibrary;
import model.Edge;
import model.Graph;
import model.Vertex;
import model.WeightedEdge;

public class ReadWeightedGraphTest {

	GraphLibrary manager = new GraphLibrary();
	
	@Test
	public void readGraphTest1() throws GraphLibraryException, FileNotFoundException {
		Graph<Edge> graphExpected = new Graph<Edge>(5);
		Vertex v1 = new Vertex(1);
		Vertex v2 = new Vertex(2);
		Vertex v3 = new Vertex(3);
		Vertex v4 = new Vertex(4);
		Vertex v5 = new Vertex(5);
		
		Edge e1 = new WeightedEdge(v1, v2, 4);
		Edge e2 = new WeightedEdge(v3, v2, 6);
		Edge e3 = new WeightedEdge(v1, v4, 3);
		Edge e4 = new WeightedEdge(v2, v5, 6);
		Edge e5 = new WeightedEdge(v4, v1, 1);
		
		graphExpected.addEdge(e1);
		graphExpected.addEdge(e2);
		graphExpected.addEdge(e3);
		graphExpected.addEdge(e4);
		graphExpected.addEdge(e5);
		
		Graph graphReturn = manager.readWeightedGraph("src/pratica2/casesReadWeightedGraph/case1.txt");
		assertEquals(manager.graphRepresentation(graphExpected, "AL") , manager.graphRepresentation(graphReturn, "AL"));
//		assertEquals(graphExpected, graphReturn);
	}
	
	@Test
	public void readWeightedGraphTest2() {
		assertEquals("Exception", testWeightedGraphException("src/pratica2/casesReadWeightedGraph/case2.txt"));
	}
	
	@Test
	public void readWeightedGraphTest3() {
		assertEquals("Exception", testWeightedGraphException("src/pratica2/casesReadWeightedGraph/case3.txt"));
	}
	
	
	@Test
	public void readWeightedGraphTest4() {
		assertEquals("Exception", testWeightedGraphException("src/pratica2/casesReadWeightedGraph/case4.txt"));
	}

	@Test
	public void readWeightedGraphTest5() {
		assertEquals("Exception", testWeightedGraphException("src/pratica2/casesReadWeightedGraph/case5.txt"));
	}
	
	@Test
	public void readWeightedGraphTest6() {
		assertTrue(testVertexType("src/pratica2/casesReadWeightedGraph/case6.txt"));
	}
	
	@Test
	public void readWeightedGraphTest7() {
		assertTrue(testVertexType("src/pratica2/casesReadWeightedGraph/case7.txt"));
	}
	
	@Test
	public void readWeightedGraphTest8() {
		assertTrue(testVertexType("src/pratica2/casesReadWeightedGraph/case8.txt"));
	}
	
	@Test
	public void readWeightedGraphTest9() {
		assertTrue(testVertexType("src/pratica2/casesReadWeightedGraph/case9.txt"));
	}
	
	@Test
	public void readWeightedGraphTest10() {
		assertTrue(testVertexType("src/pratica2/casesReadWeightedGraph/case10.txt"));
	}
	
	@Test
	public void readWeightedGraphTest11() {
		assertTrue(testVertexType("src/pratica2/casesReadWeightedGraph/case11.txt"));
	}
	
	@Test
	public void readWeightedGraphTest12() {
		assertEquals("Exception", testWeightedGraphException("src/pratica2/casesReadWeightedGraph/case12.txt"));
	}
	
	@Test
	public void readWeightedGraphTest13() {
		assertEquals("Exception", testWeightedGraphException("src/pratica2/casesReadWeightedGraph/case13.txt"));
	}
	
	@Test
	public void readWeightedGraphTest14() {
		assertEquals("Exception", testWeightedGraphException("src/pratica2/casesReadWeightedGraph/case14.txt"));
	}
	
	private boolean testVertexType(String path){
		try {
			manager.readWeightedGraph(path);
		} catch (Exception e) {
			return false;
		}
		return true;
	}
	
	private String testWeightedGraphException(String path) {
		try {
			manager.readWeightedGraph(path);
		} catch (Exception e) {
			return "Exception";
		}
		
		return "";
	}
	
}
