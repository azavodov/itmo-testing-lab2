package ru.ifmo.se.testing.zavoduben.lab2;

public class NamedValue {
    public final String name;
    public final double value;

    private NamedValue(String name, Double value) {
        this.name = name;
        this.value = value;
    }

    public static NamedValue make(String name, Double value) {
        return new NamedValue(name, value);
    }
}
