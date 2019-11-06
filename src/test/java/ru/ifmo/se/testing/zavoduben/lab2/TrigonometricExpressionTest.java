package ru.ifmo.se.testing.zavoduben.lab2;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TrigonometricExpressionTest {

    TrigonometricExpression testSubject;

    @BeforeEach
    void setUp() {
        int numTerms = 50;
        testSubject = new TrigonometricExpression(50);
    }
}
