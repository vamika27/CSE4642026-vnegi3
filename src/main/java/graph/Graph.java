package graph;

import java.util.*;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class Graph {

    private Set<String> nodes;
    private Map<String, Set<String>> edges;

    public Graph() {
        nodes = new LinkedHashSet<>();
        edges = new LinkedHashMap<>();
    }

    public void addNode(String label) {
        nodes.add(label);
        edges.putIfAbsent(label, new LinkedHashSet<>());
    }

    public void addNodes(String[] labels) {
        for (String label : labels) {
            addNode(label);
        }
    }

    public void addEdge(String src, String dst) {
        addNode(src);
        addNode(dst);
        if (!getNeighbors(src).contains(dst)) {
            getNeighbors(src).add(dst);
        }
    }

    public void removeNode(String label) {
        if (!nodes.contains(label)) {
            throw new NoSuchElementException("Node '" + label + "' does not exist in the graph.");
        }
        nodes.remove(label);
        edges.remove(label);
        for (Set<String> dsts : edges.values()) {
            dsts.remove(label);
        }
    }

    public void removeNodes(String[] labels) {
        for (String label : labels) {
            removeNode(label);
        }
    }

    public void removeEdge(String srcLabel, String dstLabel) {
        if (!isValidNodes(srcLabel, dstLabel)) {
            throw new NoSuchElementException(
                    "Node '" + srcLabel + "' or '" + dstLabel + "' does not exist in the graph.");
        }
        if (!edges.containsKey(srcLabel) || !getNeighbors(srcLabel).contains(dstLabel)) {
            throw new NoSuchElementException(
                    "Edge '" + srcLabel + " -> " + dstLabel + "' does not exist in the graph.");
        }
        getNeighbors(srcLabel).remove(dstLabel);
    }

    private boolean isValidNodes(String src, String dst) {
        return nodes.contains(src) && nodes.contains(dst);
    }

    private Path buildPath(String dst, Map<String, String> parentMap) {
        Path path = new Path();
        List<String> reversed = new ArrayList<>();

        String node = dst;
        while (node != null) {
            reversed.add(node);
            node = parentMap.get(node);
        }

        Collections.reverse(reversed);

        for (String n : reversed) {
            path.addNode(n);
        }

        return path;
    }

    public Set<String> getNeighbors(String node) {
        return edges.getOrDefault(node, Collections.emptySet());
    }

    private String getNextNode(Deque<String> frontier, Algorithm algo) {
        if (algo == Algorithm.BFS) {
            return frontier.pollFirst();
        }
        return frontier.pollLast();
    }

    public Path GraphSearch(String src, String dst, Algorithm algo) {
        GraphSearchTemplate searcher;

        if (algo == Algorithm.BFS) {
            searcher = new BFSSearch();
        } else {
            searcher = new DFSSearch();
        }

        return searcher.search(this, src, dst);
    }

    public int getNodeCount() {
        return nodes.size();
    }

    public int getEdgeCount() {
        int count = 0;
        for (Set<String> dsts : edges.values()) {
            count += dsts.size();
        }
        return count;
    }

    public Set<String> getNodes() {
        return nodes;
    }

    public Map<String, Set<String>> getEdges() {
        return edges;
    }

    public void outputGraph(String filepath) throws IOException {
        Files.writeString(Paths.get(filepath), this.toString());
    }

    public void outputDOTGraph(String path) throws IOException {
        StringBuilder sb = new StringBuilder();
        sb.append("digraph {\n");
        for (String src : edges.keySet()) {
            for (String dst : getNeighbors(src)) {
                sb.append("    ").append(src).append(" -> ").append(dst).append(";\n");
            }
        }
        sb.append("}\n");
        Files.writeString(Paths.get(path), sb.toString());
    }

    public void outputGraphics(String path, String format) throws IOException, InterruptedException {
        String dotFile = path + ".dot";
        String outputFile = path + "." + format;

        outputDOTGraph(dotFile);

        ProcessBuilder pb = new ProcessBuilder(
                "dot",
                "-T" + format,
                dotFile,
                "-o",
                outputFile
        );

        pb.inheritIO();
        Process process = pb.start();
        int exitCode = process.waitFor();

        if (exitCode != 0) {
            throw new RuntimeException("Graphviz failed to generate graphics.");
        }
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();

        sb.append("Number of nodes: ").append(getNodeCount()).append("\n");
        sb.append("Nodes: ").append(nodes).append("\n");
        sb.append("Number of edges: ").append(getEdgeCount()).append("\n");
        sb.append("Edges:\n");

        for (String src : edges.keySet()) {
            for (String dst : getNeighbors(src)) {
                sb.append(src).append(" -> ").append(dst).append("\n");
            }
        }

        return sb.toString();
    }
}