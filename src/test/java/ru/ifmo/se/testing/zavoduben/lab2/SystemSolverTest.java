package ru.ifmo.se.testing.zavoduben.lab2;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.util.Random;

import static org.assertj.core.api.Assertions.assertThat;

class SystemSolverTest {

    private static final int MAX_ARGUMENT_VALUE_FOR_LOG = 1000;

    private SystemSolver testSubject;
    private LogarithmExpression logarithmicExpression;
    private TrigonometricExpression trigonometricExpression;

    @BeforeEach
    void setUp() {
        int numTerms = 50;
        this.logarithmicExpression = new LogarithmExpression(numTerms);
        this.trigonometricExpression = new TrigonometricExpression(numTerms);
        this.testSubject = new SystemSolver(logarithmicExpression, trigonometricExpression);
    }

    @Test
    void solve_atZero_takesTrigonometric() {
        double x = 0.0D;
        assertThat(testSubject.solve(x))
                .isEqualTo(trigonometricExpression.solve(x));
    }

    @Test
    void solve_atNegative_takesTrigonometric() {
        double x = -Math.abs(new Random().nextInt());
        assertThat(testSubject.solve(x))
                .isEqualTo(trigonometricExpression.solve(x));
    }

    @Test
    void solve_atPositive_takesLogarithmic() {
        double x = +Math.abs(new Random().nextInt(MAX_ARGUMENT_VALUE_FOR_LOG));
        assertThat(testSubject.solve(x))
                .isEqualTo(logarithmicExpression.solve(x));
    }
}
