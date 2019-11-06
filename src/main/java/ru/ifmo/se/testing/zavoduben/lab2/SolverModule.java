package ru.ifmo.se.testing.zavoduben.lab2;

import java.util.function.DoubleUnaryOperator;

public interface SolverModule extends DoubleUnaryOperator {

    public NamedValue evaluate(Double x);

    @Override
    default double applyAsDouble(double operand) {
        return evaluate(operand).value;
    }
}
