package pratica2;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.Test;

import algorithms.BreadthSearch;
import algorithms.DeepSearch;
import manager.GraphManager;
import model.Graph;

public class SearchTest {

	GraphManager manager = new GraphManager();
	Graph grafoSemPeso = manager.readGraph("src/test/java/pratica2/casesGraphRepresentationTest/grafoSemPeso.txt");
	Graph grafoComPeso = manager.readWeightedGraph("src/test/java/pratica2/casesGraphRepresentationTest/grafoComPeso.txt");
	
	@Test
	public void BFSTest1() {
		String esperadoSemPeso = "1 - - 0\r\n2 - 1 1\r\n5 - 1 1\r\n3 - 5 2\r\n4 - 5 2\r\n";
		assertEquals(esperadoSemPeso, manager.BFS(grafoSemPeso, 1));
		String esperadoComPeso = "1 - - 0\r\n2 - 1 1\r\n5 - 1 1\r\n3 - 5 2\r\n4 - 3 3\r\n";
		assertEquals(esperadoComPeso, manager.BFS(grafoComPeso, 1));
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
		String saidaComPeso = "1 - - 0\r\n2 - 1 1\r\n5 - 2 2\r\n3 - 5 3\r\n4 - 3 4";
		String saidaSemPeso = "1 - - 0\r\n2 - 1 1\r\n5 - 2 2\r\n3 - 5 3\r\n4 - 5 3\r\n";
		assertEquals(saidaComPeso.trim(), manager.DFS(grafoComPeso, 1).trim());
		assertEquals(saidaSemPeso.trim(), manager.DFS(grafoSemPeso, 1).trim());
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
			manager.DFS(graph, v);
		} catch (Exception e) {
			return "Exception";
		}
		
		return "";
	}
	
	private String BFSException(Graph graph, Integer v){
		try {
			manager.BFS(graph, v);
		} catch (Exception e) {
			return "Exception";
		}
		
		return "";
	}
}
