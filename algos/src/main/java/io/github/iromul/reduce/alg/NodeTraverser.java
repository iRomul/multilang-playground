package io.github.iromul.reduce.alg;

@FunctionalInterface
public interface NodeTraverser<T> {

    Iterable<T> getAdjacentNodes(T node);
}
