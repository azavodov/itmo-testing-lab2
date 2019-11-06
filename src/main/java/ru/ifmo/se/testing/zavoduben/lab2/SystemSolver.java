package ru.ifmo.se.testing.zavoduben.lab2;

import javafx.util.Pair;

public class SystemSolver implements SolverModule {

    private LogarithmExpression logarithmExpression;
    private TrigonometricExpression trigonometricExpression;

    public SystemSolver(int numTerms) {
        this.logarithmExpression = new LogarithmExpression(numTerms);
        this.trigonometricExpression = new TrigonometricExpression(numTerms);
    }

    public double solve(double x) {
        if (x <= 0) {
            return this.trigonometricExpression.solve(x);
        } else {
            return this.logarithmExpression.solve(x);
        }
    }

    @Override
    public Pair<String, Double> evaluate(Double x) {
        return new Pair<String, Double>("System", this.solve(x));
    }

}
