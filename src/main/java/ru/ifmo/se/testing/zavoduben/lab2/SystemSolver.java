package ru.ifmo.se.testing.zavoduben.lab2;

public class SystemSolver implements SolverModule {

    private SolverModule logarithmExpression;
    private SolverModule trigonometricExpression;

    public SystemSolver(SolverModule logarithmExpression,
                        SolverModule trigonometricExpression) {
        this.logarithmExpression = logarithmExpression;
        this.trigonometricExpression = trigonometricExpression;
    }

    public SystemSolver(int numTerms) {
        this.logarithmExpression = new LogarithmExpression(numTerms);
        this.trigonometricExpression = new TrigonometricExpression(numTerms);
    }

    public double solve(double x) {
        if (x <= 0) {
            return this.trigonometricExpression.applyAsDouble(x);
        } else {
            return this.logarithmExpression.applyAsDouble(x);
        }
    }

    @Override
    public NamedValue evaluate(Double x) {
        return NamedValue.make("System", this.solve(x));
    }

}
