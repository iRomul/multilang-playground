package io.github.iromul.playground.jcip.examples.ch01.e02;

import io.github.iromul.playground.jcip.annotations.GuardedBy;
import io.github.iromul.playground.jcip.annotations.ThreadSafe;

@ThreadSafe
public class Sequence {

    @GuardedBy("this")
    private int nextValue;

    public synchronized int getNext() {
        return nextValue++;
    }
}
