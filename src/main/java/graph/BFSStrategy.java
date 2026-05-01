package graph;

public class BFSStrategy implements SearchStrategy {

    @Override
    public Path search(Graph graph, String src, String dst) {
        return graph.GraphSearch(src, dst, Algorithm.BFS);
    }
}