package ru.ifmo.se.testing.zavoduben.lab2.logarithm;

import java.util.function.DoubleUnaryOperator;

public class Logarithm {

    private DoubleUnaryOperator naturalLogarithm;

    public Logarithm(int numTerms) {
        this.naturalLogarithm = new NaturalLogarithm(numTerms);
    }

    public Logarithm(DoubleUnaryOperator naturalLogarithm) {
        this.naturalLogarithm = naturalLogarithm;
    }

    public double ln(double x) {
        return this.naturalLogarithm.applyAsDouble(x);
    }

    public double log(double base, double x) {
        return this.ln(x) / this.ln(base);
    }

    public double log10(double x) {
        return this.log(10.0, x);
    }

    public double log2(double x) {
        return this.log(2.0, x);
    }

    public double log5(double x) {
        return this.log(5.0, x);
    }

}
