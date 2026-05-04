package graph;

import java.util.*;

public class RandomWalkSearch extends GraphSearchTemplate {
    private final Random random = new Random();

    @Override
    public Path search(Graph graph, String src, String dst) {
        if (!graph.getNodes().contains(src) || !graph.getNodes().contains(dst)) {
            return null;
        }

        System.out.println("random testing");

        Path path = new Path();
        path.addNode(src);

        Set<String> visited = new HashSet<>();
        visited.add(src);

        String current = src;
        System.out.println("visiting " + path);

        while (!current.equals(dst)) {
            List<String> availableNeighbors = new ArrayList<>();

            for (String neighbor : graph.getNeighbors(current)) {
                if (!visited.contains(neighbor)) {
                    availableNeighbors.add(neighbor);
                }
            }

            if (availableNeighbors.isEmpty()) {
                return null;
            }

            String next = availableNeighbors.get(random.nextInt(availableNeighbors.size()));
            path.addNode(next);
            visited.add(next);
            current = next;

            System.out.println("visiting " + path);
        }

        return path;
    }

    @Override
    protected Collection<Path> createFrontier() {
        return new LinkedList<>();
    }

    @Override
    protected void addPath(Collection<Path> frontier, Path path) {
        frontier.add(path);
    }

    @Override
    protected Path getNextPath(Collection<Path> frontier) {
        return frontier.iterator().next();
    }
}