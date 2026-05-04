package graph;

public class SearchStrategyFactory {

    public static SearchStrategy getStrategy(Algorithm algo) {
        if (algo == Algorithm.BFS) {
            return new BFSStrategy();
        }

        if (algo == Algorithm.DFS) {
            return new DFSStrategy();
        }

        throw new IllegalArgumentException("Unsupported algorithm: " + algo);
    }
}