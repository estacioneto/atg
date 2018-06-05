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
	
	
	

}
