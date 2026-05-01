package graph;

public class Main {
    public static void main(String[] args) throws Exception {

        Graph graph = GraphParser.parseGraph("sample_inputs/input1.dot");

        SearchStrategy bfs = new BFSStrategy();
        SearchStrategy dfs = new DFSStrategy();

        System.out.println("===== BFS =====");
        System.out.println(bfs.search(graph, "a", "h"));

        System.out.println("\n===== DFS =====");
        System.out.println(dfs.search(graph, "a", "h"));
    }
}