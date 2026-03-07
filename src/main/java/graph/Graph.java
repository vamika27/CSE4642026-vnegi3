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
        edges.get(src).add(dst);
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

    public void outputDOTGraph(String path) throws IOException {
        StringBuilder sb = new StringBuilder();
        sb.append("digraph {\n");

        for (String src : edges.keySet()) {
            for (String dst : edges.get(src)) {
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
            for (String dst : edges.get(src)) {
                sb.append(src).append(" -> ").append(dst).append("\n");
            }
        }

        return sb.toString();
    }
}