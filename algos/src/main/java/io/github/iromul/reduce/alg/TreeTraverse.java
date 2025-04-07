package io.github.iromul.reduce.alg;

import java.util.*;
import java.util.function.Consumer;

public class TreeTraverse {

    public static <T> DFSIntance1<T> dfsInstance(NodeTraverser<T> getNeighbors, int nodeLimit) {
        return new DFSIntance1<>(getNeighbors, nodeLimit);
    }

    public static class DFSIntance1<T> {

        private final NodeTraverser<T> getNeighbors;
        private final int nodeLimit;

        public DFSIntance1(NodeTraverser<T> getNeighbors) {
            this(getNeighbors, Integer.MAX_VALUE);
        }

        public DFSIntance1(NodeTraverser<T> getNeighbors, int nodeLimit) {
            this.getNeighbors = getNeighbors;
            this.nodeLimit = nodeLimit;
        }

        public void visitEvery(T start, Consumer<T> visitor) {
            TreeTraverse.depthFirstSearch(start, visitor, getNeighbors, nodeLimit);
        }

        public List<T> collectAll(T start) {
            List<T> result = new ArrayList<>();

            visitEvery(start, result::add);

            return result;
        }
    }

    public static <T> void depthFirstSearch(T start, Consumer<T> visit, NodeTraverser<T> getNeighbors, int limit) {
        Stack<T> stack = new Stack<>();
        Set<T> visited = new HashSet<>();

        stack.push(start);

        while (!stack.isEmpty() && isNotLimitReached(visited, limit)) {
            T current = stack.pop();

            if (!visited.contains(current)) {
                visit.accept(current); // Process the current node
                visited.add(current);   // Mark as visited

                for (T neighbor : getNeighbors.getAdjacentNodes(current)) {
                    if (!visited.contains(neighbor)) {
                        stack.push(neighbor);
                    }
                }
            }
        }
    }

    private static boolean isNotLimitReached(Collection<?> visited, int limit) {
        return limit == Integer.MAX_VALUE || visited.size() < limit;
    }
}
