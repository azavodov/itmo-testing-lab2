package ru.ifmo.se.testing.zavoduben.lab2;

import org.junit.jupiter.api.*;
import ru.ifmo.se.testing.zavoduben.lab2.logarithm.Logarithm;
import ru.ifmo.se.testing.zavoduben.lab2.logarithm.NaturalLogarithm;
import ru.ifmo.se.testing.zavoduben.lab2.trigonometric.Sine;
import ru.ifmo.se.testing.zavoduben.lab2.trigonometric.Trigonometric;

import java.util.HashMap;
import java.util.Random;
import java.util.function.DoubleUnaryOperator;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.withPrecision;
import static org.junit.jupiter.api.DynamicTest.dynamicTest;

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

    @TestFactory
    Stream<? extends DynamicNode> integrationTests() {
        int numTerms = 50;

        DoubleUnaryOperator realSine = new Sine(numTerms);
        DoubleUnaryOperator mockSine = createMockSine();

        Logarithm realLogarithm = null; // todo
        Trigonometric realTrigonometric = null; // todo

        DoubleUnaryOperator realLn = new NaturalLogarithm(numTerms);
        DoubleUnaryOperator mockLn = createMockNaturalLogarithm();

        LogarithmExpression realLogarithmExpression = new LogarithmExpression(realLogarithmFunctions);
        LogarithmExpression logarithmExpressionWithMockedFunctions = new LogarithmExpression(mockLogarithmFunctions);
        LogarithmExpression logarithmExpressionWithMockedLn = new LogarithmExpression(logarithmFunctionsWithMockedLn);
        SolverModule mockLogarithmExpression = createMockLogarithmExpression();

        TrigonometricExpression realTrigonometricExpression = new TrigonometricExpression(realLn);
        TrigonometricExpression trigonometricExpressionWithMockedFunctions = new TrigonometricExpression(mockTrigonometricFunctions);
        TrigonometricExpression trigonometricExpressionWithMockedSine = new TrigonometricExpression(trigonometricFunctionsWithMockedSine);
        TrigonometricExpression mockTrigonometricExpression = createMockTrigonometricExpression();

        HashMap<String, SystemSolver> solvers = new HashMap<>();

        solvers.put("fully integrated", new SystemSolver(realLogarithmExpression, realTrigonometricExpression));

        solvers.put("withLogExprMocked", new SystemSolver(mockLogarithmExpression, realTrigonometricExpression));
        solvers.put("withNaturalLogarithmMocked", new SystemSolver(logarithmExpressionWithMockedLn, realTrigonometricExpression));

        solvers.put("withTrigExprMocked", new SystemSolver(realLogarithmExpression, mockTrigonometricExpression));
        solvers.put("withSineMocked", new SystemSolver(realLogarithmExpression, trigonometricExpressionWithMockedSine));

        solvers.put("fully mocked", new SystemSolver(mockLogarithmExpression, mockTrigonometricExpression));

        return solvers.entrySet().stream().map(entry -> {
            String testGroupName = entry.getKey();
            SystemSolver testSubject = entry.getValue();
            return DynamicContainer.dynamicContainer(testGroupName, testsForSubject(testSubject));
        });
    }

    private TrigonometricExpression createMockTrigonometricExpression() {
        return null;
    }

    private SolverModule createMockLogarithmExpression() {
        return new SolverModule() {
            @Override
            public NamedValue evaluate(Double x) {
                return NamedValue.make("mock LogarithmExpression", applyAsDouble(x));
            }

            @Override
            public double applyAsDouble(double x) {
                double log5 = Math.log(x) / Math.log(5);
                return (Math.pow(Math.pow(log5 - log5, 3), 2) / (Math.log(x) / Math.log(2))) +
                       (Math.log10(x) * (log5 - log5) - Math.log(x));
            }
        };
    }

    private DoubleUnaryOperator createMockNaturalLogarithm() {
        return Math::log;
    }

    private DoubleUnaryOperator createMockSine() {
        return Math::sin;
    }

    private Stream<? extends DynamicNode> testsForSubject(SystemSolver testSubject) {
        return Stream.of(

                dynamicTest("at 0 returns trigonometric expression", () -> {
                    double x = 0.0D;
                    assertThat(testSubject.solve(x))
                            .isEqualTo(trigonometricExpression.solve(x),
                                    withPrecision(10D));
                }),

                dynamicTest("at negative returns trigonometric expression", () -> {
                    double x = -Math.abs(new Random().nextInt());
                    assertThat(testSubject.solve(x))
                            .isEqualTo(trigonometricExpression.solve(x),
                                    withPrecision(10D));
                }),

                dynamicTest("at positive returns logarithmic expression", () -> {
                    double x = +Math.abs(new Random().nextInt(MAX_ARGUMENT_VALUE_FOR_LOG));
                    assertThat(testSubject.solve(x))
                            .isEqualTo(logarithmicExpression.solve(x),
                                    withPrecision(10D));
                })
        );
    }
}
