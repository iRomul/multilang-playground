package io.github.iromul.reduce.alg;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;
import java.util.function.Predicate;

/**
 * A utility class to collect elements from a tree-like structure recursively
 * based on a specified condition and transformation function up to a given depth.
 *
 * @param <T> the type of elements being collected
 */
public class RecursiveTreeCollector<T> {

    private final int maxDepth;

    public RecursiveTreeCollector() {
        this(Integer.MAX_VALUE);
    }

    public RecursiveTreeCollector(int maxDepth) {
        this.maxDepth = maxDepth;
    }

    public List<T> collect(
            T initial,
            Predicate<T> hasNext,
            Function<T, T> nextValueFunction
    ) {
        List<T> accumulator = new ArrayList<>();

        collectInner(initial, hasNext, nextValueFunction, accumulator, maxDepth);

        return accumulator;
    }

    private void collectInner(T current,
                              Predicate<T> hasNext,
                              Function<T, T> nextValueFunction,
                              List<T> accumulator,
                              Integer depthRemain) {
        if (depthRemain != 0 && hasNext.test(current)) {
            T next = nextValueFunction.apply(current);

            accumulator.add(next);

            collectInner(next, hasNext, nextValueFunction, accumulator, depthRemain - 1);
        }
    }
}
