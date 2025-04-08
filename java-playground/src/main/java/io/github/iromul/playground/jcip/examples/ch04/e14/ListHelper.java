package io.github.iromul.playground.jcip.examples.ch04.e14;

import io.github.iromul.playground.jcip.annotations.NotThreadSafe;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@NotThreadSafe
public class ListHelper<E> {

    public List<E> list = Collections.synchronizedList(new ArrayList<>());

    public synchronized boolean putIfAbsent(E x) {
        boolean absent = !list.contains(x);

        if (absent) {
            list.add(x);
        }

        return absent;
    }
}
