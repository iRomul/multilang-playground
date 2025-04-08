package io.github.iromul.playground.jcip.examples.ch04.e10;

import java.util.concurrent.atomic.AtomicInteger;

public class NumberRange {

    // ИНВАРИАНТ: lower <= upper
    private final AtomicInteger lower = new AtomicInteger(0);
    private final AtomicInteger upper = new AtomicInteger(0);

    public void setLower(int i) {
        // Предупреждение – небезопасная операция "проверить и затем действовать"
        if (i > upper.get()) {
            throw new IllegalArgumentException("не могу установить lower равным " + i + " > upper");
        }

        lower.set(i);
    }

    public void setUpper(int i) {
        // Предупреждение – небезопасная операция "проверить и затем действовать"
        if (i < lower.get()) {
            throw new IllegalArgumentException("не могу установить upper равным " + i + " < lower");
        }

        upper.set(i);
    }

    public boolean isInRange(int i) {
        return (i >= lower.get() && i <= upper.get());
    }
}
