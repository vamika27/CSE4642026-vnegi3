package graph;

import java.util.*;

public abstract class GraphSearchTemplate {

    public Path search(Graph graph, String src, String dst) {
        if (!graph.getNodes().contains(src) || !graph.getNodes().contains(dst)) {
            return null;
        }

        Collection<Path> frontier = createFrontier();

        Path startPath = new Path();
        startPath.addNode(src);
        addPath(frontier, startPath);

        Set<String> visited = new HashSet<>();
        visited.add(src);

        while (!frontier.isEmpty()) {
            Path currentPath = getNextPath(frontier);
            String currentNode = currentPath.getNodes().get(currentPath.getNodes().size() - 1);

            if (currentNode.equals(dst)) {
                return currentPath;
            }

            for (String neighbor : graph.getNeighbors(currentNode)) {
                if (!visited.contains(neighbor)) {
                    visited.add(neighbor);

                    Path newPath = new Path();
                    for (String node : currentPath.getNodes()) {
                        newPath.addNode(node);
                    }
                    newPath.addNode(neighbor);

                    addPath(frontier, newPath);
                }
            }
        }

        return null;
    }

    protected abstract Collection<Path> createFrontier();

    protected abstract void addPath(Collection<Path> frontier, Path path);

    protected abstract Path getNextPath(Collection<Path> frontier);
}