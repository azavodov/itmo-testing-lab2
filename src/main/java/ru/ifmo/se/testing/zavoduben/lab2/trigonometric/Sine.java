package ru.ifmo.se.testing.zavoduben.lab2.trigonometric;

import javafx.util.Pair;
import ru.ifmo.se.testing.zavoduben.lab2.SolverModule;

import java.util.function.DoubleUnaryOperator;

public class Sine implements DoubleUnaryOperator, SolverModule {

    private int numTerms;

    public Sine(int numTerms) {
        this.numTerms = numTerms;
    }

    @Override
    public double applyAsDouble(double x) { return getValue(x); }

    public double getValue(double x) {
        if (Double.isInfinite(x)) {
            throw new IllegalArgumentException("Sine is not defined at infinity");
        }
        if (Double.isNaN(x)) {
            throw new IllegalArgumentException("Sine is not defined for NaN");
        }
        double y = 0.0;
        for (int i = 0; i < this.numTerms; i++) {
            y += Math.pow(-1, i) * Math.pow(x, 1 + 2 * i) / (this.factorial(1 + 2 * i));
        }
        return y;
    }

    private double factorial(int n) {
        if (n == 0) return 1;
        return n * factorial(n - 1);
    }

    @Override
    public Pair<String, Double> evaluate(Double x) {
        return new Pair<String, Double>("Sine", this.getValue(x));
    }

}
