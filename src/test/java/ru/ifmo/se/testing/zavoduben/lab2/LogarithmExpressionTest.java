package ru.ifmo.se.testing.zavoduben.lab2;

import org.junit.jupiter.api.DynamicContainer;
import org.junit.jupiter.api.DynamicTest;
import org.junit.jupiter.api.TestFactory;

import java.util.Collection;
import java.util.Collections;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

class LogarithmExpressionTest {

    @TestFactory
    Collection<DynamicContainer> dynamicContainerCollection() {
        return Collections.singleton(DynamicContainer.dynamicContainer(
                "parent", dynamicTestCollection()
        ));
    }

    @TestFactory
    Stream<DynamicTest> dynamicTestCollection() {
        return Stream.of(DynamicTest.dynamicTest("kek", () -> {
            fail();
        }));
    }
}
