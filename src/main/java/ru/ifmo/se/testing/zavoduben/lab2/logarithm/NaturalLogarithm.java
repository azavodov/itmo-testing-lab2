package ru.ifmo.se.testing.zavoduben.lab2.logarithm;

import javafx.util.Pair;
import ru.ifmo.se.testing.zavoduben.lab2.SolverModule;

import java.util.function.DoubleUnaryOperator;

public class NaturalLogarithm implements DoubleUnaryOperator, SolverModule {

    private int numTerms;

    public NaturalLogarithm(int numTerms) {
        this.numTerms = numTerms;
    }

    @Override
    public double applyAsDouble(double x) {
        return x >= 1 ? -getValue(x) : getValue(x);
    }

    private double getValue(double x) {
        if (Double.isNaN(x) || x < 0.0) { return Double.NaN; }
        if (0.0 == x) { return Double.NEGATIVE_INFINITY; }
        if (Double.POSITIVE_INFINITY == x) { return Double.POSITIVE_INFINITY; }

        double result = 0.0;
        if (Math.abs(x - 1) < 1 && x != 0.0) {
            for (int i = 1; i < this.numTerms; i++) {
                result += Math.pow(-1.0, i) * Math.pow(x - 1.0, i) / i;
            }
            result *= -1.0;
        } else {
            for (int i = 1; i < this.numTerms; i++) {
                result -= Math.pow(-1.0, i) * Math.pow(x - 1.0, -i) / i;
            }
            result = getValue(x - 1) - result;
        }
        return result;
    }

    @Override
    public Pair<String, Double> result(Double x) {
        return new Pair<String, Double>("Natural Logarithm", this.getValue(x));
    }

}
