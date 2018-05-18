import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import manager.GraphManager;
import model.Graph;

class GraphTest {

    GraphManager manager = new GraphManager();
    Graph normalGraph = manager.readGraph("src/test/java/resources/case1.txt");
    Graph weightedGraph = manager.readGraph("src/test/java/resources/case2.txt");
    Graph normalGraph2 = manager.readGraph("src/test/java/resources/case3.txt");

    @Test
    void testReadGraph() {
        assertNotNull(normalGraph);
        assertNotNull(normalGraph2);
    }

    @Test
    void testReadWeightedGraph() {
        assertNotNull(weightedGraph);
    }

    @Test
    void testGetVertexNumber(){
        assertEquals(manager.getVertexNumber(normalGraph), 5);
        assertEquals(manager.getVertexNumber(weightedGraph), 5);
        assertEquals(manager.getVertexNumber(normalGraph2), 5);
    }

    @Test
    void testGetEdgeNumber(){
        assertEquals(manager.getEdgeNumber(normalGraph), 4);
        assertEquals(manager.getEdgeNumber(weightedGraph), 6);
        assertEquals(manager.getEdgeNumber(normalGraph2), 5);
    }

    @Test
    void testMeanEdge(){
        assertEquals(manager.getMeanEdge(normalGraph), 1);
        assertEquals(manager.getMeanEdge(weightedGraph), 2);
        assertEquals(manager.getMeanEdge(normalGraph2), 2);
    }

    @Test
    void testBFS(){
        assertEquals(manager.BFS(normalGraph, 1), "1 - - 0\n2 - 1 1\n4 - 1 1\n3 - 2 2\n5 - 2 2\n");
        assertEquals(manager.BFS(weightedGraph, 1), "1 - - 0\n2 - 1 1\n5 - 1 1\n3 - 5 2\n4 - 5 2\n");
        assertEquals(manager.BFS(normalGraph2, 1), "1 - - 0\n2 - 1 1\n5 - 1 1\n3 - 5 2\n4 - 5 2\n");
    }

    @Test
    void testDFS(){
        assertEquals(manager.DFS(normalGraph,1), "1 - - 0\n2 - 1 1\n3 - 2 2\n5 - 2 2\n4 - 1 1\n");
        assertEquals(manager.DFS(weightedGraph,1), "1 - - 0\n2 - 1 1\n5 - 2 2\n3 - 5 3\n4 - 3 4\n");
        assertEquals(manager.DFS(normalGraph2, 1), "1 - - 0\n2 - 1 1\n5 - 2 2\n3 - 5 3\n4 - 5 3\n");
    }

    @Test
    void testConnected(){
        assertTrue(manager.connected(normalGraph));
        assertTrue(manager.connected(weightedGraph));
        assertTrue(manager.connected(normalGraph2));
    }

    @Test
    void testShortestPath(){
        assertEquals(manager.shortestPath(2,1), "1.0");
        assertEquals(manager.shortestPath(3,1), "2.0");
        assertEquals(manager.shortestPath(2, 1), "1.0");
    }

    @Test
    void testMst(){
        assertEquals(manager.mst(normalGraph), "1 - 2 | 1\n2 - 3 | 2\n1 - 4 | 3\n2 - 5 | 4\n");
        assertEquals(manager.mst(weightedGraph), "1 - 2 | 1\n3 - 4 | 2\n4 - 5 | 3\n2 - 5 | 4\n");
        assertEquals(manager.mst(normalGraph2), "1 - 2 | 1\n4 - 5 | 2\n3 - 5 | 3\n2 - 5 | 4\n");
    }

}