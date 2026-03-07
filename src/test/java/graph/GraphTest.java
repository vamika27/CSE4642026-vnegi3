package graph;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class GraphTest {

    @Test
    public void testParseGraph() throws Exception {
        Graph graph = GraphParser.parseGraph("sample_inputs/input1.dot");

        assertEquals(8, graph.getNodeCount());
        assertEquals(9, graph.getEdgeCount());

        assertTrue(graph.getNodes().contains("a"));
        assertTrue(graph.getNodes().contains("h"));

        assertTrue(graph.getEdges().get("a").contains("b"));
        assertTrue(graph.getEdges().get("g").contains("h"));
    }

    @Test
    public void testOutputDOTGraph() throws Exception {
        Graph graph = GraphParser.parseGraph("sample_inputs/input1.dot");
        graph.outputDOTGraph("sample_inputs/output1.dot");

        String content = java.nio.file.Files.readString(
                java.nio.file.Paths.get("sample_inputs/output1.dot"));

        assertTrue(content.contains("digraph {"));
        assertTrue(content.contains("a -> b;"));
        assertTrue(content.contains("g -> h;"));
    }

    @Test
    public void testOutputGraphics() throws Exception {
        Graph graph = GraphParser.parseGraph("sample_inputs/input1.dot");
        graph.outputGraphics("sample_inputs/graph_output", "png");

        java.io.File file = new java.io.File("sample_inputs/graph_output.png");
        assertTrue(file.exists());
    }
}