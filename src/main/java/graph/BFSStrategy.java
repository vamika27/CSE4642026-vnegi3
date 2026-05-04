package graph;

public class BFSStrategy implements SearchStrategy {

    private final GraphSearchTemplate searchTemplate = new BFSSearch();

    @Override
    public Path search(Graph graph, String src, String dst) {
        return searchTemplate.search(graph, src, dst);
    }
}