package graph;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.util.NoSuchElementException;

public class GraphTest {

    // ========== Existing Part 1 Tests ==========

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
    public void testAddNode() {
        Graph graph = new Graph();
        graph.addNode("x");
        graph.addNode("x");
        assertEquals(1, graph.getNodeCount());
        assertTrue(graph.getNodes().contains("x"));
    }

    @Test
    public void testAddNodes() {
        Graph graph = new Graph();
        graph.addNodes(new String[]{"a", "b", "c", "a"});
        assertEquals(3, graph.getNodeCount());
        assertTrue(graph.getNodes().contains("a"));
        assertTrue(graph.getNodes().contains("b"));
        assertTrue(graph.getNodes().contains("c"));
    }

    @Test
    public void testAddEdge() {
        Graph graph = new Graph();
        graph.addEdge("a", "b");
        graph.addEdge("a", "b");
        assertEquals(2, graph.getNodeCount());
        assertEquals(1, graph.getEdgeCount());
        assertTrue(graph.getEdges().get("a").contains("b"));
    }

    @Test
    public void testOutputGraph() throws Exception {
        Graph graph = GraphParser.parseGraph("sample_inputs/input1.dot");
        graph.outputGraph("sample_inputs/outputGraph.txt");
        String content = java.nio.file.Files.readString(
                java.nio.file.Paths.get("sample_inputs/outputGraph.txt"));
        assertTrue(content.contains("Number of nodes: 8"));
        assertTrue(content.contains("Number of edges: 9"));
        assertTrue(content.contains("a -> b"));
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

    // ========== Scenario 1: Correct removal of nodes and edges ==========

    @Test
    public void testRemoveNode() throws Exception {
        Graph graph = GraphParser.parseGraph("sample_inputs/input1.dot");
        graph.removeNode("a");
        assertEquals(7, graph.getNodeCount());
        assertFalse(graph.getNodes().contains("a"));
        assertFalse(graph.getEdges().containsKey("a"));
        assertFalse(graph.getEdges().get("d").contains("a"));
    }

    @Test
    public void testRemoveNodes() throws Exception {
        Graph graph = GraphParser.parseGraph("sample_inputs/input1.dot");
        graph.removeNodes(new String[]{"f", "g"});
        assertEquals(6, graph.getNodeCount());
        assertFalse(graph.getNodes().contains("f"));
        assertFalse(graph.getNodes().contains("g"));
        assertFalse(graph.getEdges().get("e").contains("f"));
        assertFalse(graph.getEdges().get("e").contains("g"));
    }

    @Test
    public void testRemoveEdge() throws Exception {
        Graph graph = GraphParser.parseGraph("sample_inputs/input1.dot");
        int edgesBefore = graph.getEdgeCount();
        graph.removeEdge("a", "b");
        assertEquals(edgesBefore - 1, graph.getEdgeCount());
        assertFalse(graph.getEdges().get("a").contains("b"));
        assertTrue(graph.getNodes().contains("a"));
        assertTrue(graph.getNodes().contains("b"));
    }

    // ========== Scenario 2: Removing non-existent nodes causes exceptions ==========

    @Test
    public void testRemoveNonExistentNode() {
        Graph graph = new Graph();
        graph.addNode("a");
        assertThrows(NoSuchElementException.class, () -> {
            graph.removeNode("z");
        });
    }

    @Test
    public void testRemoveNodesOneNonExistent() throws Exception {
        Graph graph = GraphParser.parseGraph("sample_inputs/input1.dot");
        assertThrows(NoSuchElementException.class, () -> {
            graph.removeNodes(new String[]{"a", "nonexistent"});
        });
    }

    // ========== Scenario 3: Removing non-existent edges causes exceptions ==========

    @Test
    public void testRemoveNonExistentEdge() {
        Graph graph = new Graph();
        graph.addEdge("a", "b");
        graph.addNode("c");
        assertThrows(NoSuchElementException.class, () -> {
            graph.removeEdge("a", "c");
        });
    }

    @Test
    public void testRemoveEdgeWithNonExistentNode() {
        Graph graph = new Graph();
        graph.addNode("a");
        assertThrows(NoSuchElementException.class, () -> {
            graph.removeEdge("a", "z");
        });
    }

    // ========== BFS Graph Search Tests ==========

    @Test
    public void testBFSPathExists() throws Exception {
        // input1.dot: a->b, b->c, c->d, d->a, a->e, e->f, e->g, f->h, g->h
        // BFS from a to h: a -> e -> f -> h (or a -> e -> g -> h)
        Graph graph = GraphParser.parseGraph("sample_inputs/input1.dot");
        Path path = graph.GraphSearch("a", "h");
        assertNotNull(path);
        assertEquals("a", path.getNodes().get(0));
        assertEquals("h", path.getNodes().get(path.getNodes().size() - 1));
    }

    @Test
    public void testBFSPathNotExists() throws Exception {
        // h has no outgoing edges, so no path from h to a
        Graph graph = GraphParser.parseGraph("sample_inputs/input1.dot");
        Path path = graph.GraphSearch("h", "a");
        assertNull(path);
    }

    @Test
    public void testBFSSameNode() {
        Graph graph = new Graph();
        graph.addNode("a");
        Path path = graph.GraphSearch("a", "a");
        assertNotNull(path);
        assertEquals(1, path.getNodes().size());
        assertEquals("a", path.getNodes().get(0));
    }

    @Test
    public void testBFSNodeNotInGraph() {
        Graph graph = new Graph();
        graph.addNode("a");
        Path path = graph.GraphSearch("a", "z");
        assertNull(path);
    }
}