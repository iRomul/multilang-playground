package io.github.iromul.playground.jcip.examples.ch04.e04;

import io.github.iromul.playground.jcip.annotations.NotThreadSafe;

@NotThreadSafe
public class MutablePoint {

    public int x, y;

    public MutablePoint() {
        x = 0;
        y = 0;
    }

    public MutablePoint(MutablePoint p) {
        this.x = p.x;
        this.y = p.y;
    }
}
