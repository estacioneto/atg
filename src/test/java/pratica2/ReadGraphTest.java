package pratica2;

import static org.junit.jupiter.api.Assertions.*;


import org.junit.Test;

import manager.GraphManager;
import model.Graph;

public class ReadGraphTest {

	GraphManager manager = new GraphManager();
	
	@Test
	public void readGraphTest1() {
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
	public void readGraphTest2() {
		assertEquals("Exception", testGraphException("src/test/java/pratica2/casesReadGraph/case4.txt"));
	}
	
	@Test
	public void readGraphTest3() {
		assertEquals("Exception", testGraphException("src/test/java/pratica2/casesReadGraph/case3.txt"));
	}		
	
	@Test
	public void readGraphTest4() {
		assertEquals("Exception", testGraphException("src/test/java/pratica2/casesReadGraph/case4.txt"));
	}
	
	@Test
	public void readGraphTest5() {
		assertTrue(testVertexType("src/test/java/pratica2/casesReadGraph/case5.txt"));
	}
	
	@Test
	public void readGraphTest6() {
		assertTrue(testVertexType("src/test/java/pratica2/casesReadGraph/case6.txt"));
	}

	@Test
	public void readGraphTest7() {
		assertTrue(testVertexType("src/test/java/pratica2/casesReadGraph/case7.txt"));
	}
	
	
	@Test
	public void readGraphTest8() {
		assertTrue(testVertexType("src/test/java/pratica2/casesReadGraph/case8.txt"));
	}
	
	@Test
	public void readGraphTest9() {
		assertTrue(testVertexType("src/test/java/pratica2/casesReadGraph/case9.txt"));
	}
	
	@Test
	public void readGraphTest10() {
		assertTrue(testVertexType("src/test/java/pratica2/casesReadGraph/case10.txt"));
	}
	
	@Test
	public void readGraphTest11() {
		assertTrue(testVertexType("src/test/java/pratica2/casesReadGraph/case11.txt"));
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
