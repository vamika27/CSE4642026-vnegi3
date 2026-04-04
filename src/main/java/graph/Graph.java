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
        if (!edges.get(src).contains(dst)) {
            edges.get(src).add(dst);
        }
    }

    // ========== Remove APIs ==========

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
        if (!nodes.contains(srcLabel) || !nodes.contains(dstLabel)) {
            throw new NoSuchElementException(
                "Node '" + srcLabel + "' or '" + dstLabel + "' does not exist in the graph.");
        }
        if (!edges.containsKey(srcLabel) || !edges.get(srcLabel).contains(dstLabel)) {
            throw new NoSuchElementException(
                "Edge '" + srcLabel + " -> " + dstLabel + "' does not exist in the graph.");
        }
        edges.get(srcLabel).remove(dstLabel);
    }

    // ========== Graph Search (merged BFS + DFS with Algorithm enum) ==========

    public Path GraphSearch(String src, String dst, Algorithm algo) {
        if (!nodes.contains(src) || !nodes.contains(dst)) {
            return null;
        }

        Deque<String> frontier = new LinkedList<>();
        Map<String, String> parentMap = new HashMap<>();
        Set<String> visited = new HashSet<>();

        frontier.add(src);
        visited.add(src);
        parentMap.put(src, null);

        while (!frontier.isEmpty()) {
            String current;
            if (algo == Algorithm.BFS) {
                current = frontier.pollFirst();  // Queue behavior: remove from front
            } else {
                current = frontier.pollLast();   // Stack behavior: remove from back
            }

            if (current.equals(dst)) {
                // Reconstruct path
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

            if (edges.containsKey(current)) {
                for (String neighbor : edges.get(current)) {
                    if (!visited.contains(neighbor)) {
                        visited.add(neighbor);
                        parentMap.put(neighbor, current);
                        frontier.add(neighbor);
                    }
                }
            }
        }
        return null;
    }

    // ========== Existing APIs ==========

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