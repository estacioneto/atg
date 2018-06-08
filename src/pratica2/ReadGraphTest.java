package pratica2;

import static org.junit.jupiter.api.Assertions.*;

import java.io.FileNotFoundException;

import org.junit.Test;

import exception.GraphLibraryException;
//import manager.GraphManager;
//import model.Graph;

import library.GraphLibrary;
import model.Graph;
import model.Vertex;
import model.Edge;

public class ReadGraphTest {

	//GraphManager manager = new GraphManager();
	GraphLibrary manager = new GraphLibrary();
	
	@Test
	public void readGraphTest1() throws FileNotFoundException, GraphLibraryException {
		Graph<Edge> graphExpected = new Graph<Edge>(5);
		Vertex v1 = new Vertex(1);
		Vertex v2 = new Vertex(2);
		Vertex v3 = new Vertex(3);
		Vertex v4 = new Vertex(4);
		Vertex v5 = new Vertex(5);
		
		Edge e1 = new Edge(v1, v2);
		Edge e2 = new Edge(v3, v2);
		Edge e3 = new Edge(v1, v4);
		Edge e4 = new Edge(v2, v5);
		Edge e5 = new Edge(v4, v1);
		
		graphExpected.addEdge(e1);
		graphExpected.addEdge(e2);
		graphExpected.addEdge(e3);
		graphExpected.addEdge(e4);
		graphExpected.addEdge(e5);
		
		Graph graphReturn = manager.readGraph("src/pratica2/casesReadGraph/case1.txt");
		assertEquals(manager.graphRepresentation(graphExpected, "AL") , manager.graphRepresentation(graphReturn, "AL"));
//		assertEquals(graphExpected , graphReturn);
	}
	
	@Test
	public void readGraphTest2() {
		assertEquals("Exception", testGraphException("src/pratica2/casesReadGraph/case4.txt"));
	}
	
	@Test
	public void readGraphTest3() {
		assertEquals("Exception", testGraphException("src/pratica2/casesReadGraph/case3.txt"));
	}		
	
	@Test
	public void readGraphTest4() {
		assertEquals("Exception", testGraphException("src/pratica2/casesReadGraph/case4.txt"));
	}
	
	@Test
	public void readGraphTest5() {
		assertTrue(testVertexType("src/pratica2/casesReadGraph/case5.txt"));
	}
	
	@Test
	public void readGraphTest6() {
		assertTrue(testVertexType("src/pratica2/casesReadGraph/case6.txt"));
	}

	@Test
	public void readGraphTest7() {
		assertTrue(testVertexType("src/pratica2/casesReadGraph/case7.txt"));
	}
	
	
	@Test
	public void readGraphTest8() {
		assertTrue(testVertexType("src/pratica2/casesReadGraph/case8.txt"));
	}
	
	@Test
	public void readGraphTest9() {
		assertEquals("Exception", testGraphException("src/pratica2/casesReadGraph/case9.txt"));
	}
	
	@Test
	public void readGraphTest10() {
		assertTrue(testVertexType("src/pratica2/casesReadGraph/case10.txt"));
	}
	
	@Test
	public void readGraphTest11() {
		assertEquals("Exception", testGraphException("src/pratica2/casesReadGraph/case11.txt"));
	}
	
	
	@Test
	public void readGraphTest12() {
		assertFalse(testVertexType("src/pratica2/casesReadGraph/case12.txt"));
	}
	
	
	
	private boolean testVertexType(String path){
		try {
			manager.readGraph(path);
		} catch (Exception e) {
			return false;
		}
		return true;
	}
	
	private String testGraphException(String path) {
		try {
			manager.readGraph(path);
		} catch (Exception e) {
			return "Exception";
		}
		
		return "";
	}
}
