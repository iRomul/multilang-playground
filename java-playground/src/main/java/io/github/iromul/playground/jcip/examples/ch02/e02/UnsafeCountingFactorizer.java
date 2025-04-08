package io.github.iromul.playground.jcip.examples.ch02.e02;

import io.github.iromul.playground.jcip.annotations.NotThreadSafe;

import javax.servlet.Servlet;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import java.math.BigInteger;

@NotThreadSafe
public class UnsafeCountingFactorizer implements Servlet {

    private long count = 0;

    public long getCount() {
        return count;
    }

    public void service(ServletRequest req, ServletResponse resp) {
        BigInteger i = extractFromRequest(req);

        BigInteger[] factors = factor(i);
        ++count;

        encodeIntoResponse(resp, factors);
    }
}
