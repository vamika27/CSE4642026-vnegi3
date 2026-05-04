package graph;

import java.util.Collection;
import java.util.Stack;

public class DFSSearch extends GraphSearchTemplate {

    @Override
    protected Collection<Path> createFrontier() {
        return new Stack<>();
    }

    @Override
    protected void addPath(Collection<Path> frontier, Path path) {
        ((Stack<Path>) frontier).push(path);
    }

    @Override
    protected Path getNextPath(Collection<Path> frontier) {
        return ((Stack<Path>) frontier).pop();
    }
}