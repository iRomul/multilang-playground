package io.github.iromul.playground.jcip.examples.ch02.e01;

import io.github.iromul.playground.jcip.annotations.ThreadSafe;

import javax.servlet.Servlet;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import java.math.BigInteger;

@ThreadSafe
public class StatelessFactorizer implements Servlet {

    public void service(ServletRequest req, ServletResponse resp) {
        BigInteger i = extractFromRequest(req);

        BigInteger[] factors = factor(i);

        encodeIntoResponse(resp, factors);
    }
}
