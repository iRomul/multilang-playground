package io.github.iromul.playground.jcip.examples.ch02.e06;

import io.github.iromul.playground.jcip.annotations.GuardedBy;
import io.github.iromul.playground.jcip.annotations.ThreadSafe;

import javax.servlet.Servlet;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import java.math.BigInteger;

@ThreadSafe
public class SynchronizedFactorizer implements Servlet {

    @GuardedBy("this")
    private BigInteger lastNumber;

    @GuardedBy("this")
    private BigInteger[] lastFactors;

    public synchronized void service(ServletRequest req, ServletResponse resp) {
        BigInteger i = extractFromRequest(req);

        if (i.equals(lastNumber))
            encodeIntoResponse(resp, lastFactors);
        else {
            BigInteger[] factors = factor(i);
            lastNumber = i;
            lastFactors = factors;

            encodeIntoResponse(resp, factors);
        }
    }
}
