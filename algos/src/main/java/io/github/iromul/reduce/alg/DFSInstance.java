package io.github.iromul.reduce.alg;

import java.util.*;
import java.util.function.Consumer;

public class DFSInstance<T> {

    private final NodeTraverser<T> getNeighbors;
    private final int nodeLimit;

    public DFSInstance(NodeTraverser<T> getNeighbors) {
        this(getNeighbors, Integer.MAX_VALUE);
    }

    public DFSInstance(NodeTraverser<T> getNeighbors, int nodeLimit) {
        this.getNeighbors = getNeighbors;
        this.nodeLimit = nodeLimit;
    }

    // Core DFS algorithm without recursion
    public static <T> void depthFirstSearch(T start, Consumer<T> visit, NodeTraverser<T> getNeighbors) {
        Stack<T> stack = new Stack<>();
        Set<T> visited = new HashSet<>();

        stack.push(start);

        while (!stack.isEmpty()) {
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

    public static <T> List<T> depthFirstSearch(T start, NodeTraverser<T> getNeighbors) {
        Stack<T> stack = new Stack<>();
        Set<T> visited = new HashSet<>();
        List<T> result = new ArrayList<>();

        stack.push(start);

        while (!stack.isEmpty()) {
            T current = stack.pop();

            if (!visited.contains(current)) {
                result.add(current);  // Process the current node
                visited.add(current);   // Mark as visited

                for (T neighbor : getNeighbors.getAdjacentNodes(current)) {
                    if (!visited.contains(neighbor)) {
                        stack.push(neighbor);
                    }
                }
            }
        }

        result.remove(start);

        return result; // Return the resulting list of visited nodes
    }
}
