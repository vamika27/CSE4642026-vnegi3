package graph;

public class Main {
    public static void main(String[] args) throws Exception {

        Graph graph = GraphParser.parseGraph("sample_inputs/input1.dot");

        SearchStrategy bfs = new BFSStrategy();
        SearchStrategy dfs = new DFSStrategy();
        SearchStrategy random = new RandomWalkStrategy();

        System.out.println("===== BFS =====");
        System.out.println(bfs.search(graph, "a", "h"));

        System.out.println("\n===== DFS =====");
        System.out.println(dfs.search(graph, "a", "h"));

        System.out.println("\n===== RANDOM WALK RUN 1 =====");
        System.out.println(random.search(graph, "a", "c"));

        System.out.println("\n===== RANDOM WALK RUN 2 =====");
        System.out.println(random.search(graph, "a", "c"));

        System.out.println("\n===== RANDOM WALK RUN 3 =====");
        System.out.println(random.search(graph, "a", "c"));
    }
}