package ru.ifmo.se.testing.zavoduben.lab2.trigonometric;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Timeout;
import ru.ifmo.se.testing.zavoduben.lab2.trigonometric.Trigonometric;

import java.util.concurrent.ThreadLocalRandom;

import static java.util.concurrent.TimeUnit.SECONDS;
import static org.assertj.core.api.Assertions.*;

@Timeout(value = 10, unit = SECONDS)
class SecantFunctionTest {

    private Trigonometric testSubject;

    @BeforeEach
    void setUp() {
        int numTerms = 50;
        this.testSubject = new Trigonometric(numTerms);
    }

    @Test
    void getValue_atNan_throwsException() {
        assertThatCode(() -> testSubject.sec(Double.NaN))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("Sine is not defined for NaN");
    }

    @Test
    void getValue_atMinusInf_throwsException() {
        assertThatCode(() -> testSubject.sec(Double.NEGATIVE_INFINITY))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("Sine is not defined at infinity");
    }

    @Test
    void getValue_atPlusInf_throwsException() {
        assertThatCode(() -> testSubject.sec(Double.POSITIVE_INFINITY))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("Sine is not defined at infinity");
    }

    @Test
    void getValue_atZero_isPlusOne() {
        assertThat(testSubject.sec(0.0D)).isCloseTo(+1.0D, within(0.1D));
    }

    @Test
    void getValue_atPi_isMinusOne() {
        assertThat(testSubject.sec(Math.PI)).isCloseTo(-1.0D, within(0.1D));
    }

    @Test
    void getValue_atMinusPi_isMinusOne() {
        assertThat(testSubject.sec(0 - Math.PI)).isCloseTo(-1.0D, within(0.1D));
    }

    @Test
    void getValue_atTwoPiN_isPlusOne() {
        double x = 2.0 * randomPiTimesN();
        System.out.println("x = " + x);
        assertThat(testSubject.sec(x)).isCloseTo(+1.0D, within(0.1D));
    }

    @Test
    void getValue_atPiPlusTwoPiN_isMinusOne() {
        double x = Math.PI + 2 * randomPiTimesN();
        System.out.println("x = " + x);
        int expected = -1;
        double actual = testSubject.sec(x);
        assertThat(actual)
                .isCloseTo(expected, withinPercentage(10L));
    }

    @Test
    void getValue_atHalfPiPlusPiN_isIndefinite() {
        double x = Math.PI / 2 + randomPiTimesN();
        System.out.println("x = " + x);
        double actual = testSubject.sec(x);
        assertThat(actual).isNaN();
    }

    @Test
    void getValue_fromZeroUptoPlusHalfPi_isPositive() {
        double x = Math.random() * Math.PI / 2;
        System.out.println("x = " + x);
        assertThat(testSubject.sec(x))
                .isCloseTo(1 / Math.cos(x), withinPercentage(10L));
    }

    @Test
    void getValue_fromZeroDowntoMinusHalfPi_isPositive() {
        double x = -(Math.random() * Math.PI / 2);
        System.out.println("x = " + x);
        double actualValue = testSubject.sec(x);
        double expectedValue = 1 / Math.cos(x);

        assertThat(actualValue).isPositive();
        assertThat(actualValue).isCloseTo(expectedValue, withinPercentage(10L));
    }

    @Test
    void getValue_fromMinusHalfPiToZero_plusTwoPiN_isPositiveFalling() {
        double x = -(Math.random() * Math.PI / 2) + 2 * randomPiTimesN();
        System.out.println("x = " + x);

        double actualValue = testSubject.sec(x);
        double expectedValue = 1 / Math.cos(x);

        assertThat(actualValue).isPositive();
        assertThat(actualValue).isCloseTo(expectedValue, withinPercentage(10L));
    }

    @Test
    void getValue_fromZeroToHalfPi_plusTwoPiN_isPositiveRising() {
        double x = (Math.random() * Math.PI / 2) + 2 * randomPiTimesN();
        System.out.println("x = " + x);

        double actualValue = testSubject.sec(x);
        double expectedValue = 1 / Math.cos(x);

        assertThat(actualValue).isPositive();
        assertThat(actualValue).isCloseTo(expectedValue, withinPercentage(10L));
    }

    @Test
    void getValue_fromHalfPiToPi_plusTwoPiN_isNegativeRising() {
        double x = (Math.random() * Math.PI / 2) + Math.PI / 2 + 2 * randomPiTimesN();
        System.out.println("x = " + x);

        double actualValue = testSubject.sec(x);
        double expectedValue = 1 / Math.cos(x);

        assertThat(actualValue).isNegative();
        assertThat(actualValue).isCloseTo(expectedValue, withinPercentage(10L));
    }


    @Test
    void getValue_fromPiToThreeHalfsPi_plusTwoPiN_isNegativeFalling() {
        double x = (Math.random() * Math.PI / 2) + Math.PI + 2 * randomPiTimesN();
        System.out.println("x = " + x);

        double actualValue = testSubject.sec(x);
        double expectedValue = 1 / Math.cos(x);

        assertThat(actualValue).isNegative();
        assertThat(actualValue).isCloseTo(expectedValue, withinPercentage(10L));
    }

    private double randomPiTimesN() {
        return Math.PI * ThreadLocalRandom.current().nextInt(-10, 10);
    }

}
