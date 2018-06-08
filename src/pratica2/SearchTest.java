package pratica2;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.FileNotFoundException;

import org.junit.Before;
import org.junit.Test;

import exception.GraphLibraryException;
import library.GraphLibrary;
import model.Graph;
import model.Vertex;

public class SearchTest {
	GraphLibrary manager;
	Graph grafoSemPeso, grafoComPeso;
	
	@Before
	public void setup() throws FileNotFoundException, GraphLibraryException {
		manager = new GraphLibrary();
		grafoSemPeso = manager.readGraph("src/pratica2/casesGraphRepresentationTest/grafoSemPeso.txt");
		grafoComPeso = manager.readWeightedGraph("src/pratica2/casesGraphRepresentationTest/grafoComPeso.txt");
	}
	
	@Test
	public void BFSTest1() {
		String esperadoSemPeso = "1 - - 0\r\n2 - 1 1\r\n\r\n3 - 5 2\r\n4 - 5 2\r\n5 - 1 1\r\n";
		assertEquals(esperadoSemPeso, manager.BFS(grafoSemPeso, new Vertex(1)));
		String esperadoComPeso = "1 - - 0\r\n2 - 1 1\r\n3 - 5 2\r\n4 - 3 3\r\n5 - 1 1\r\n";
		assertEquals(esperadoComPeso, manager.BFS(grafoComPeso, new Vertex(1)));
	}
	
	@Test
	public void BFSTest2() {
		assertEquals("Exception", BFSException(grafoSemPeso, 9));
	}
	
	@Test
	public void BFSTest3() {
		assertEquals("Exception", BFSException(grafoSemPeso, (Integer) null));
	}
	
	
	@Test
	public void BFSTest4() {
		assertEquals("Exception", BFSException(null, 2));
	}
	
	@Test
	public void DFSTest1() {
		String saidaComPeso = "1 - - 0\r\n2 - 1 1\r\n3 - 5 3\r\n4 - 3 4\r\n5 - 2 2";
		String saidaSemPeso = "1 - - 0\r\n2 - 1 1\r\n3 - 5 3\r\n4 - 5 3\r\n5 - 2 2\r\n";
		assertEquals(saidaComPeso.trim(), manager.DFS(grafoComPeso, new Vertex(1)).trim());
		assertEquals(saidaSemPeso.trim(), manager.DFS(grafoSemPeso, new Vertex(1)).trim());
	}
	
	@Test
	public void DFSTest2() {
		assertEquals("Exception", DFSException(grafoSemPeso, 8));
	}
	
	@Test
	public void DFSTest3() {
		assertEquals("Exception", DFSException(grafoSemPeso, (Integer) null));
	}
	
	@Test
	public void DFSTest4() {
		assertEquals("Exception", DFSException(null, 2));
	}
	
	
	private String DFSException(Graph graph, Integer v){
		try {
			manager.DFS(graph, new Vertex(v));
		} catch (Exception e) {
			return "Exception";
		}
		
		return "";
	}
	
	private String BFSException(Graph graph, Integer v){
		try {
			manager.BFS(graph, new Vertex(v));
		} catch (Exception e) {
			return "Exception";
		}
		
		return "";
	}
}
