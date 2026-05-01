package graph;

public interface SearchStrategy {
    Path search(Graph graph, String src, String dst);
}