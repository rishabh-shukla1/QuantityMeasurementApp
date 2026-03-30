package com.app.quantitymeasurement.unit;

import java.util.function.DoubleBinaryOperator;

public enum OperationType {

    ADD((a, b) -> a + b),
    SUBTRACT((a, b) -> a - b),
    DIVIDE((a, b) -> a / b);

    private final DoubleBinaryOperator operator;

    OperationType(DoubleBinaryOperator operator) {
        this.operator = operator;
    }

    public double apply(double a, double b) {
        return operator.applyAsDouble(a, b);
    }
}
