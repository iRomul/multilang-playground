package io.github.iromul.playground.jcip.examples.ch02.e03;

import io.github.iromul.playground.jcip.annotations.NotThreadSafe;

@NotThreadSafe
public class LazyInitRace {

    private ExpensiveObject instance = null;

    public ExpensiveObject getInstance() {
        if (instance == null) {
            instance = new ExpensiveObject();
        }

        return instance;
    }
}
