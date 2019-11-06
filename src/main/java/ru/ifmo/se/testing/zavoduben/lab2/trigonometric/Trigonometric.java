package ru.ifmo.se.testing.zavoduben.lab2.trigonometric;

import java.util.function.DoubleUnaryOperator;

public class Trigonometric {

    private DoubleUnaryOperator sine;

    public Trigonometric(int numTerms) {
        this.sine = new Sine(numTerms);
    }

    public Trigonometric(DoubleUnaryOperator sine) {
        this.sine = sine;
    }

    public double sin(double x) {
        return this.sine.applyAsDouble(x);
    }

    public double cos(double x) {
        return 1.0 - 2.0 * Math.pow(this.sin(x / 2.0), 2.0);
    }

    public double sec(double x) {
        return 1.0 / this.cos(x);
    }

    public double csc(double x) {
        return 1.0 / this.sin(x);
    }

    public double tan(double x) {
        return this.sin(x) / this.cos(x);
    }

    public double cot(double x) {
        return 1.0 / this.tan(x);
    }

}
