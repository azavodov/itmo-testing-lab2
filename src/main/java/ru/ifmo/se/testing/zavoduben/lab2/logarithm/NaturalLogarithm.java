package ru.ifmo.se.testing.zavoduben.lab2.logarithm;

import ru.ifmo.se.testing.zavoduben.lab2.NamedValue;
import ru.ifmo.se.testing.zavoduben.lab2.SolverModule;

import java.util.function.DoubleUnaryOperator;

public class NaturalLogarithm implements DoubleUnaryOperator, SolverModule {

    private int numTerms;

    public NaturalLogarithm(int numTerms) {
        this.numTerms = numTerms;
    }

    @Override
    public double applyAsDouble(double x) {
        return getValue(x);
    }

    private double getValue(double x) {
        if (Double.isNaN(x) || x < 0.0) { return Double.NaN; }
        if (0.0 == x) { return Double.NEGATIVE_INFINITY; }
        if (Double.POSITIVE_INFINITY == x) { return Double.POSITIVE_INFINITY; }

        double _x = (x - 1) / (x + 1);
        double series = 0;
        double n = 1;
        for (int i = 1; i < this.numTerms; i++) {
            double term = Math.pow(_x, n) / n;
            series += term;
            n += 2;
        }
        return 2 * series;
    }

    @Override
    public NamedValue evaluate(Double x) {
        return NamedValue.make("Natural Logarithm", this.applyAsDouble(x));
    }

}
