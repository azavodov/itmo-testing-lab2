package ru.ifmo.se.testing.zavoduben.lab2;

import javafx.util.Pair;
import ru.ifmo.se.testing.zavoduben.lab2.trigonometric.Trigonometric;

public class TrigonometricExpression implements SolverModule{

    private Trigonometric t;

    public TrigonometricExpression(int numTerms) {
        this.t = new Trigonometric(numTerms);
    }

    public double solve(double x) {
        return  (Math.pow(Math.pow(t.cot(x) - t.tan(x), 2), 3) - (t.tan(x) + t.sec(x))) -
                Math.pow((t.csc(x) * t.csc(Math.pow(x, 2)) / (t.sec(x) * (t.tan(x) / t.cos(x)))), 3);
    }

    @Override
    public Pair<String, Double> result(Double x) {
        return new Pair<String, Double>("Trigonometric expression", this.solve(x));
    }
}
