package graph;

import java.util.*;

public class RandomWalkSearch extends GraphSearchTemplate {
    private final Random random = new Random();
    private static final int MAX_ATTEMPTS = 20;

    @Override
    public Path search(Graph graph, String src, String dst) {
        if (!graph.getNodes().contains(src) || !graph.getNodes().contains(dst)) {
            return null;
        }

        System.out.println("random testing");

        for (int attempt = 1; attempt <= MAX_ATTEMPTS; attempt++) {
            Path path = new Path();
            path.addNode(src);

            Set<String> visited = new HashSet<>();
            visited.add(src);

            String current = src;
            System.out.println("attempt " + attempt);
            System.out.println("visiting " + path);

            while (true) {
                if (current.equals(dst)) {
                    return path;
                }

                List<String> availableNeighbors = new ArrayList<>();

                for (String neighbor : graph.getNeighbors(current)) {
                    if (!visited.contains(neighbor)) {
                        availableNeighbors.add(neighbor);
                    }
                }

                if (availableNeighbors.isEmpty()) {
                    System.out.println("dead end, restarting");
                    break;
                }

                String next = availableNeighbors.get(random.nextInt(availableNeighbors.size()));
                path.addNode(next);
                visited.add(next);
                current = next;

                System.out.println("visiting " + path);
            }
        }

        return null;
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