package graph;

public class Main {
    public static void main(String[] args) throws Exception {

        Graph graph = GraphParser.parseGraph("sample_inputs/input1.dot");

        System.out.println("===== BFS =====");
        Path bfsPath = graph.GraphSearch("a", "h", Algorithm.BFS);
        System.out.println(bfsPath);

        System.out.println("\n===== DFS =====");
        Path dfsPath = graph.GraphSearch("a", "h", Algorithm.DFS);
        System.out.println(dfsPath);

    }
}