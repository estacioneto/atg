package pratica2;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

import java.io.FileNotFoundException;

import org.junit.Before;
import org.junit.Test;

import exception.GraphLibraryException;
import library.GraphLibrary;
import model.Graph;
import model.Vertex;

public class OtherTest {

	GraphLibrary manager;
	Graph grafoSemPeso;
	Graph grafoComPeso;
			
	@Before
	public void setup() throws FileNotFoundException, GraphLibraryException {
		manager = new GraphLibrary();
		grafoSemPeso = manager.readGraph("src/pratica2/casesGraphRepresentationTest/grafoSemPeso.txt");
		grafoComPeso = manager.readWeightedGraph("src/pratica2/casesGraphRepresentationTest/grafoComPeso.txt");	
	}
	
	@Test
	public void getMeanEdgeTest1() {
		assertEquals(2.0, manager.getMeanEdge(grafoSemPeso));
	}
	
	@Test
	public void connectedTest1SemPeso() {
		assertTrue(manager.connected(grafoSemPeso));
	}
	
	@Test
	public void connectedTest1ComPeso() {
		assertTrue(manager.connected(grafoSemPeso));
	}
	
	@Test
	public void connectedTest2() {
		assertEquals("Exception", conectedExepection(null));
	}
	
	@Test
	public void shortestPathTest1() throws GraphLibraryException {
		assertEquals("3 5 4", manager.shortestPath(grafoSemPeso, new Vertex(3), new Vertex(4)));
	}
	
	@Test
	public void shortestPathTest2() {
		assertEquals("Exception", shortestPathExepection(grafoSemPeso, 3, 8));
	}
	
	@Test
	public void shortestPathTest3() {
		assertEquals("Exception", shortestPathExepection(null, 3, 4));
	}
	
	
	@Test
	public void mstTest1SemPeso() throws GraphLibraryException {
		String esperado = "1 - 2 | 1\r\n4 - 5 | 2\r\n3 - 5 | 3\r\n2 - 5 | 4\r\n";
		assertEquals(esperado, manager.MST(grafoSemPeso));	}
	
	@Test
	public void mstTest1ComPeso() throws GraphLibraryException {
		String esperado = "3 - 4 1\r\n1 - 2 2\r\n2 - 5 3\r\n3 - 5 4\r\n";
		assertEquals(esperado, manager.MST(grafoComPeso));
	}
	
	@Test
	public void mstTest2() {
		assertEquals("Exception", mstExepection(null));
	}
	
	private String shortestPathExepection(Graph graph, Integer v1, Integer v2) {
		try {
			manager.shortestPath(graph, new Vertex(v1), new Vertex(v2));
		} catch (Exception e) {
			return "Exception";
		}
		
		return "";
	}
	
	private String mstExepection(Graph graph) {
		try {
			manager.MST(graph);
		} catch (Exception e) {
			return "Exception";
		}
		
		return "";
	}
	
	
	private String conectedExepection(Graph graph) {
		try {
			manager.connected(graph);
		} catch (Exception e) {
			return "Exception";
		}
		
		return "";
	}
	
	@Test
	public void getMeanEdgeTest2() {
		try {
			manager.getMeanEdge(null);
		} catch (Exception e) {
			return;
		}
		fail("");
	}
	

}
