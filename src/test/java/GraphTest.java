import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import manager.GraphManager;
import model.Graph;

class GraphTest {

//    GraphManager manager = new GraphManager();
//    Graph normalGraph = manager.readGraph("src/test/java/resources/case1.txt");
//    Graph weightedGraph = manager.readWeightedGraph("src/test/java/resources/case2.txt");
//    Graph normalGraph2 = manager.readGraph("src/test/java/resources/case3.txt");
//
//    @Test
//    void testReadGraph() {
//        assertNotNull(normalGraph);
//        assertNotNull(normalGraph2);
//    }
//
//    @Test
//    void testReadWeightedGraph() {
//        assertNotNull(weightedGraph);
//    }
//
//    @Test
//    void testGetVertexNumber(){
//        assertEquals(manager.getVertexNumber(normalGraph), 5);
//        assertEquals(manager.getVertexNumber(weightedGraph), 5);
//        assertEquals(manager.getVertexNumber(normalGraph2), 5);
//    }
//
//    @Test
//    void testGetEdgeNumber(){
//        assertEquals(manager.getEdgeNumber(normalGraph), 4);
//        assertEquals(manager.getEdgeNumber(weightedGraph), 6);
//        assertEquals(manager.getEdgeNumber(normalGraph2), 5);
//    }
//
//    @Test
//    void testMeanEdge(){
//        assertEquals(manager.getMeanEdge(normalGraph), 1);
//        assertEquals(manager.getMeanEdge(weightedGraph), 2);
//        assertEquals(manager.getMeanEdge(normalGraph2), 2);
//    }
//
//    @Test
//    void testBFS(){
//        assertEquals(manager.BFS(normalGraph, 1), "1 - - 0\n2 - 1 1\n4 - 1 1\n3 - 2 2\n5 - 2 2\n");
//        assertEquals(manager.BFS(weightedGraph, 1), "1 - - 0\n2 - 1 1\n5 - 1 1\n3 - 5 2\n4 - 5 2\n");
//        assertEquals(manager.BFS(normalGraph2, 1), "1 - - 0\n2 - 1 1\n5 - 1 1\n3 - 5 2\n4 - 5 2\n");
//    }
//
//    @Test
//    void testDFS(){
//        assertEquals(manager.DFS(normalGraph,1), "1 - - 0\n2 - 1 1\n3 - 2 2\n5 - 2 2\n4 - 1 1\n");
//        assertEquals(manager.DFS(weightedGraph,1), "1 - - 0\n2 - 1 1\n5 - 2 2\n3 - 5 3\n4 - 3 4\n");
//        assertEquals(manager.DFS(normalGraph2, 1), "1 - - 0\n2 - 1 1\n5 - 2 2\n3 - 5 3\n4 - 5 3\n");
//    }
//
//    @Test
//    void testConnected(){
//        assertTrue(manager.connected(normalGraph));
//        assertTrue(manager.connected(weightedGraph));
//        assertTrue(manager.connected(normalGraph2));
//    }
//
//    @Test
//    void testShortestPath(){
//        assertEquals(manager.shortestPath(weightedGraph, 2,1), "2 1");
//        assertEquals(manager.shortestPath(weightedGraph, 3,1), "3 4 5 1");
//        assertEquals(manager.shortestPath(weightedGraph, 2, 3), "2 5 3");
//    }
//
//    @Test
//    void testMst(){
//        assertEquals(manager.mst(normalGraph), "1 - 2 | 1\n2 - 3 | 2\n1 - 4 | 3\n2 - 5 | 4\n");
//        assertEquals(manager.mst(weightedGraph), "3 - 4 | 1\n1 - 2 | 2\n2 - 5 | 3\n4 - 5 | 4\n");
//        assertEquals(manager.mst(normalGraph2), "1 - 2 | 1\n4 - 5 | 2\n3 - 5 | 3\n2 - 5 | 4\n");
//    }
//
//    @Test
//    void testGraphRepresentationAM(){
//        assertEquals(manager.graphRepresentation(normalGraph, "AM"), "  1 2 3 4 5 \n1 0 1 0 1 0 \n2 1 0 1 0 1 \n3 0 1 0 0 0 \n4 1 0 0 0 0 \n5 0 1 0 0 0 \n");
//        assertEquals(manager.graphRepresentation(weightedGraph, "AM"), "  1 2 3 4 5 \n1 0 0.1 0 0 1 \n2 0.1 0 0 0 0.2 \n3 0 0 0 -9.5 5 \n4 0 0 -9.5 0 2.3 \n5 1 0.2 5 2.3 0 \n");
//        assertEquals(manager.graphRepresentation(normalGraph2, "AM"), "  1 2 3 4 5 \n1 0 1 0 0 1 \n2 1 0 0 0 1 \n3 0 0 0 0 1 \n4 0 0 0 0 1 \n5 1 1 1 1 0 \n");
//    }
//
//    @Test
//    void testGraphRepresentationAL(){
//        assertEquals(manager.graphRepresentation(normalGraph, "AL"), "1 - 2 4 \n2 - 1 3 5 \n3 - 2 \n4 - 1 \n5 - 2 \n");
//        assertEquals(manager.graphRepresentation(weightedGraph, "AL"), "1 - 2(0.1) 5(1) \n2 - 1(0.1) 5(0.2) \n3 - 4(-9.5) 5(5) \n4 - 3(-9.5) 5(2.3) \n5 - 1(1) 2(0.2) 3(5) 4(2.3) \n");
//        assertEquals(manager.graphRepresentation(normalGraph2, "AL"), "1 - 2 5 \n2 - 1 5 \n3 - 5 \n4 - 5 \n5 - 1 2 3 4 \n");
//    }
}