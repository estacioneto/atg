package pratica2;

import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.*;


import org.junit.jupiter.api.Test;

import manager.GraphManager;
import model.Graph;

class ReadGraphTest {

	GraphManager manager = new GraphManager();
	
	@Test
	void readGraphTest1() {
		Graph graphExpected = new Graph(false);
		graphExpected.connect("1 2");
		graphExpected.connect("3 2");
		graphExpected.connect("1 4");
		graphExpected.connect("2 5");
		graphExpected.connect("4 1");
		
		Graph graphReturn = manager.readGraph("src/test/java/pratica2/casesReadGraph/case1.txt");
		assertEquals(manager.graphRepresentation(graphExpected, "AL") , manager.graphRepresentation(graphReturn, "AL"));
//		assertEquals(graphExpected , graphReturn);
	}
	
	@Test
	void readGraphTest2() {
		assertThrows(Exception.class , manager.readGraph("src/test/java/pratica2/casesReadGraph/case2.txt"));
	}
	
	@Test
	void readGraphTest3() {
		assertThrows(Exception.class , manager.readGraph("src/test/java/pratica2/casesReadGraph/case3.txt"));
	}
	
	@Test
	void readGraphTest4() {
		assertThrows(Exception.class , manager.readGraph("src/test/java/pratica2/casesReadGraph/case4.txt"));
	}
	
	@Test
	void readGraphTest5() {
		assertFalse(testVertexType("src/test/java/pratica2/casesReadGraph/case5.txt"));
	}
	
	@Test
	void readGraphTest6() {
		assertFalse(testVertexType("src/test/java/pratica2/casesReadGraph/case6.txt"));
	}

	@Test
	void readGraphTest7() {
		assertFalse(testVertexType("src/test/java/pratica2/casesReadGraph/case7.txt"));
	}
	
	
	@Test
	void readGraphTest8() {
		assertFalse(testVertexType("src/test/java/pratica2/casesReadGraph/case8.txt"));
	}
	
	@Test
	void readGraphTest9() {
		assertFalse(testVertexType("src/test/java/pratica2/casesReadGraph/case9.txt"));
	}
	
	@Test
	void readGraphTest10() {
		assertFalse(testVertexType("src/test/java/pratica2/casesReadGraph/case10.txt"));
	}
	
	@Test
	void readGraphTest11() {
		assertFalse(testVertexType("src/test/java/pratica2/casesReadGraph/case11.txt"));
	}
	

	
	private boolean testVertexType(String path){
		try {
			manager.readGraph(path);
		} catch (Exception e) {
			return false;
		}
		return true;
	}
}
