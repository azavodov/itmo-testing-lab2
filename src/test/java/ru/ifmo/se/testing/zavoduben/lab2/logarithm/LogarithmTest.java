package ru.ifmo.se.testing.zavoduben.lab2.logarithm;

import java.util.Random;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class LogarithmTest {

    private Logarithm testSubject;
    private Random random = new Random();
    private NaturalLogarithm naturalLogarithm;

    @BeforeEach
    void setUp() {
        int numTerms = 50;
        this.naturalLogarithm = new NaturalLogarithm(numTerms);
        this.testSubject = new Logarithm(naturalLogarithm);
    }

    @Test
    void ln() {
        double x = (double) random.nextInt();
        assertThat(testSubject.ln(x)).isEqualTo(naturalLogarithm.applyAsDouble(x));
    }

    @Test
    void log() {
        double base = (double) random.nextInt();
        double x = (double) random.nextInt();
        assertThat(testSubject.log(base, x)).isEqualTo(naturalLogarithm.applyAsDouble(x) / naturalLogarithm.applyAsDouble(base));
    }

    @Test
    void log10() {
    }

    @Test
    void log2() {
    }

    @Test
    void log5() {
    }
}
