package io.github.iromul.playground.jcip.examples.ch02.e04;

import io.github.iromul.playground.jcip.annotations.ThreadSafe;

import javax.servlet.Servlet;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import java.math.BigInteger;
import java.util.concurrent.atomic.AtomicLong;

@ThreadSafe
public class CountingFactorizer implements Servlet {

    private final AtomicLong count = new AtomicLong(0);

    public long getCount() {
        return count.get();
    }

    public void service(ServletRequest req, ServletResponse resp) {
        BigInteger i = extractFromRequest(req);

        BigInteger[] factors = factor(i);
        count.incrementAndGet();

        encodeIntoResponse(resp, factors);
    }
}
