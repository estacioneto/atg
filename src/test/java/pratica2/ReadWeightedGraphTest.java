package pratica2;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.Test;

import manager.GraphManager;
import model.Graph;

public class ReadWeightedGraphTest {

	GraphManager manager = new GraphManager();
	
	@Test
	public void readGraphTest1() {
		Graph graphExpected = new Graph(true);
		graphExpected.connectWeighted("1 2 4");
		graphExpected.connectWeighted("3 2 6");
		graphExpected.connectWeighted("1 4 3");
		graphExpected.connectWeighted("2 5 6");
		graphExpected.connectWeighted("4 1 1");
		
		Graph graphReturn = manager.readWeightedGraph("src/test/java/pratica2/casesReadWeightedGraph/case1.txt");
		assertEquals(manager.graphRepresentation(graphExpected, "AL") , manager.graphRepresentation(graphReturn, "AL"));
//		assertEquals(graphExpected, graphReturn);
	}
	
	@Test
	public void readWeightedGraphTest2() {
		assertEquals("Exception", testWeightedGraphException("src/test/java/pratica2/casesReadWeightedGraph/case2.txt"));
	}
	
	@Test
	public void readWeightedGraphTest3() {
		assertEquals("Exception", testWeightedGraphException("src/test/java/pratica2/casesReadWeightedGraph/case3.txt"));
	}
	
	
	@Test
	public void readWeightedGraphTest4() {
		assertEquals("Exception", testWeightedGraphException("src/test/java/pratica2/casesReadWeightedGraph/case4.txt"));
	}

	@Test
	public void readWeightedGraphTest5() {
		assertEquals("Exception", testWeightedGraphException("src/test/java/pratica2/casesReadWeightedGraph/case5.txt"));
	}
	
	@Test
	public void readWeightedGraphTest6() {
		assertTrue(testVertexType("src/test/java/pratica2/casesReadWeightedGraph/case6.txt"));
	}
	
	@Test
	public void readWeightedGraphTest7() {
		assertTrue(testVertexType("src/test/java/pratica2/casesReadWeightedGraph/case7.txt"));
	}
	
	@Test
	public void readWeightedGraphTest8() {
		assertTrue(testVertexType("src/test/java/pratica2/casesReadWeightedGraph/case8.txt"));
	}
	
	@Test
	public void readWeightedGraphTest9() {
		assertTrue(testVertexType("src/test/java/pratica2/casesReadWeightedGraph/case9.txt"));
	}
	
	@Test
	public void readWeightedGraphTest10() {
		assertTrue(testVertexType("src/test/java/pratica2/casesReadWeightedGraph/case10.txt"));
	}
	
	@Test
	public void readWeightedGraphTest11() {
		assertTrue(testVertexType("src/test/java/pratica2/casesReadWeightedGraph/case11.txt"));
	}
	
	@Test
	public void readWeightedGraphTest12() {
		assertTrue(testVertexType("src/test/java/pratica2/casesReadWeightedGraph/case12.txt"));
	}
	
	@Test
	public void readWeightedGraphTest13() {
		assertTrue(testVertexType("src/test/java/pratica2/casesReadWeightedGraph/case13.txt"));
	}
	
	@Test
	public void readWeightedGraphTest14() {
		assertTrue(testVertexType("src/test/java/pratica2/casesReadWeightedGraph/case14.txt"));
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
