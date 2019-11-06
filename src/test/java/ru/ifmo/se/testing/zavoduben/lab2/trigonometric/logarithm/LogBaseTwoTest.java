package ru.ifmo.se.testing.zavoduben.lab2.trigonometric.logarithm;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Timeout;
import ru.ifmo.se.testing.zavoduben.lab2.logarithm.Logarithm;

import static java.util.concurrent.TimeUnit.SECONDS;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.within;

@Timeout(value = 10, unit = SECONDS)
public class LogBaseTwoTest {

    private Logarithm testSubject;

    @BeforeEach
    void setup() {
        this.testSubject = new Logarithm(50);
    }

    @Test
    void getValue_atNan_isNan() {
        assertThat(testSubject.log2(1)).isCloseTo(0.0D, within(0.01));
    }

}
