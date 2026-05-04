package graph;

import java.util.Collection;
import java.util.LinkedList;
import java.util.Queue;

public class BFSSearch extends GraphSearchTemplate {

    @Override
    protected Collection<Path> createFrontier() {
        return new LinkedList<>();
    }

    @Override
    protected void addPath(Collection<Path> frontier, Path path) {
        ((Queue<Path>) frontier).add(path);
    }

    @Override
    protected Path getNextPath(Collection<Path> frontier) {
        return ((Queue<Path>) frontier).poll();
    }
}