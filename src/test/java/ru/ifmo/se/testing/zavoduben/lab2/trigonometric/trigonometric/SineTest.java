package ru.ifmo.se.testing.zavoduben.lab2.trigonometric.trigonometric;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Timeout;
import ru.ifmo.se.testing.zavoduben.lab2.trigonometric.Sine;

import static java.util.concurrent.TimeUnit.SECONDS;
import static org.assertj.core.api.Assertions.*;
import static org.assertj.core.api.Assertions.assertThatCode;

@Timeout(value = 10, unit = SECONDS)
public class SineTest {

    private Sine testSubject;

    @BeforeEach
    void setup() {
        this.testSubject = new Sine(5);
    }

    @Test
    void getValue_atNan_throwsException() {
        assertThatCode(() -> testSubject.getValue(Double.NaN))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("Sine is not defined for NaN");
    }

    @Test
    void getValue_atMinusInf_throwsException() {
        assertThatCode(() -> testSubject.getValue(Double.NEGATIVE_INFINITY))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("Sine is not defined at infinity");
    }

    @Test
    void getValue_atZero_isZero() {
        assertThat(testSubject.applyAsDouble(0)).isEqualTo(0, within(0.01));
    }



}
