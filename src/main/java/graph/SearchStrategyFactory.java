package graph;

public class SearchStrategyFactory {

    public static SearchStrategy getStrategy(Algorithm algo) {
        if (algo == Algorithm.BFS) {
            return new BFSStrategy();
        }

        if (algo == Algorithm.DFS) {
            return new DFSStrategy();
        }

        if (algo == Algorithm.RANDOM) {
            return new RandomWalkStrategy();
        }

        throw new IllegalArgumentException("Unsupported algorithm: " + algo);
    }
}