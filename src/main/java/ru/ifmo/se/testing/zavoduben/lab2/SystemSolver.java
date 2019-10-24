package ru.ifmo.se.testing.zavoduben.lab2;

public class SystemSolver {

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

}
