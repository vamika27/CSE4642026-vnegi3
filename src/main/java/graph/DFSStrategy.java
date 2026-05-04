package graph;

public class DFSStrategy implements SearchStrategy {

    private final GraphSearchTemplate searchTemplate = new DFSSearch();

    @Override
    public Path search(Graph graph, String src, String dst) {
        return searchTemplate.search(graph, src, dst);
    }
}