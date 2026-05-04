package graph;

public class RandomWalkStrategy implements SearchStrategy {

    private final GraphSearchTemplate searchTemplate = new RandomWalkSearch();

    @Override
    public Path search(Graph graph, String src, String dst) {
        return searchTemplate.search(graph, src, dst);
    }
}