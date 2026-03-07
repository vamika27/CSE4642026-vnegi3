package graph;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

public class GraphParser {

    public static Graph parseGraph(String filepath) throws IOException {
        Graph graph = new Graph();
        List<String> lines = Files.readAllLines(Paths.get(filepath));

        for (String line : lines) {
            line = line.trim();

            if (line.isEmpty() ||
                    line.startsWith("digraph") ||
                    line.equals("{") ||
                    line.equals("}")) {
                continue;
            }

            if (line.endsWith(";")) {
                line = line.substring(0, line.length() - 1).trim();
            }

            if (line.contains("->")) {
                String[] parts = line.split("->");
                if (parts.length == 2) {
                    String src = parts[0].trim();
                    String dst = parts[1].trim();
                    graph.addEdge(src, dst);
                }
            } else {
                graph.addNode(line);
            }
        }

        return graph;
    }
}