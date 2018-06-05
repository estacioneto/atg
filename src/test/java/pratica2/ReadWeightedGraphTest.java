package pratica2;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import manager.GraphManager;
import model.Graph;

class ReadWeightedGraphTest {

	GraphManager manager = new GraphManager();
	
	@Test
	void readGraphTest1() {
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
	void readWeightedGraphTest2() {
		assertThrows(Exception.class , manager.readWeightedGraph("src/test/java/pratica2/casesReadWeightedGraph/case2.txt"));
	}
	
	@Test
	void readWeightedGraphTest3() {
		assertThrows(Exception.class , manager.readWeightedGraph("src/test/java/pratica2/casesReadWeightedGraph/case3.txt"));
	}
	
	
	@Test
	void readWeightedGraphTest4() {
		assertThrows(Exception.class , manager.readWeightedGraph("src/test/java/pratica2/casesReadWeightedGraph/case4.txt"));
	}

	@Test
	void readWeightedGraphTest5() {
		assertThrows(Exception.class , manager.readWeightedGraph("src/test/java/pratica2/casesReadWeightedGraph/case5.txt"));
	}
	
	@Test
	void readWeightedGraphTest6() {
		assertFalse(testVertexType("src/test/java/pratica2/casesReadWeightedGraph/case6.txt"));
	}
	
	@Test
	void readWeightedGraphTest7() {
		assertFalse(testVertexType("src/test/java/pratica2/casesReadWeightedGraph/case7.txt"));
	}
	
	@Test
	void readWeightedGraphTest8() {
		assertFalse(testVertexType("src/test/java/pratica2/casesReadWeightedGraph/case8.txt"));
	}
	
	@Test
	void readWeightedGraphTest9() {
		assertFalse(testVertexType("src/test/java/pratica2/casesReadWeightedGraph/case9.txt"));
	}
	
	@Test
	void readWeightedGraphTest10() {
		assertFalse(testVertexType("src/test/java/pratica2/casesReadWeightedGraph/case10.txt"));
	}
	
	@Test
	void readWeightedGraphTest11() {
		assertFalse(testVertexType("src/test/java/pratica2/casesReadWeightedGraph/case11.txt"));
	}
	
	@Test
	void readWeightedGraphTest12() {
		assertFalse(testVertexType("src/test/java/pratica2/casesReadWeightedGraph/case12.txt"));
	}
	
	@Test
	void readWeightedGraphTest13() {
		assertFalse(testVertexType("src/test/java/pratica2/casesReadWeightedGraph/case13.txt"));
	}
	
	@Test
	void readWeightedGraphTest14() {
		assertFalse(testVertexType("src/test/java/pratica2/casesReadWeightedGraph/case14.txt"));
	}
	
	private boolean testVertexType(String path){
		try {
			manager.readWeightedGraph(path);
		} catch (Exception e) {
			return false;
		}
		return true;
	}
	
}
