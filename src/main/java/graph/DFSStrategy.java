package graph;

public class DFSStrategy implements SearchStrategy {

    @Override
    public Path search(Graph graph, String src, String dst) {
        return graph.GraphSearch(src, dst, Algorithm.DFS);
    }
}