package ru.ifmo.se.testing.zavoduben.lab2;

import ru.ifmo.se.testing.zavoduben.lab2.logarithm.Logarithm;

public class LogarithmExpression implements SolverModule {

    private Logarithm l;

    public LogarithmExpression(int numTerms) {
        this.l = new Logarithm(numTerms);
    }

    public LogarithmExpression(Logarithm logarithm) {
        this.l = logarithm;
    }

    public double solve(double x) {
        return (Math.pow(Math.pow(l.log5(x) - l.log5(x), 3), 2) / l.log2(x)) +
                (l.log10(x) * (l.log5(x) - l.log5(x)) - l.ln(x));
    }

    @Override
    public NamedValue evaluate(Double x) {
        return NamedValue.make("Logarithm expression", this.solve(x));
    }
}
