package ru.ifmo.se.testing.zavoduben.lab2.trigonometric.logarithm;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Timeout;
import ru.ifmo.se.testing.zavoduben.lab2.logarithm.NaturalLogarithm;

import static java.util.concurrent.TimeUnit.SECONDS;
import static org.assertj.core.api.Assertions.*;


@Timeout(value = 10, unit = SECONDS)
public class NaturalLogarithmTest {

    private NaturalLogarithm testSubject;

    @BeforeEach
    void setup() {
        this.testSubject = new NaturalLogarithm(50);
    }

    @Test
    void getValue_atNan_isNan() {
        assertThat(testSubject.getValue(Double.NaN)).isEqualTo(Double.NaN);
    }

    @Test
    void getValue_atNegative_isNan() {
        assertThat(testSubject.getValue(-Math.random())).isEqualTo(Double.NaN);
    }

    @Test
    void getValue_atZero_isMinusInfinity() {
        assertThat(testSubject.getValue(0.0D)).isEqualTo(Double.NEGATIVE_INFINITY);
    }

}
