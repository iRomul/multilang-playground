package io.github.iromul.playground.jcip.examples.ch01.e01;

import io.github.iromul.playground.jcip.annotations.NotThreadSafe;

@NotThreadSafe
public class UnsafeSequence {

    private int value;

    /**
     * Возвращает уникальное значение.
     */
    public int getNext() {
        return value++;
    }
}
