package com.quantityapp.quantity.core;

import com.quantityapp.quantity.dto.QuantityDTO;
import com.quantityapp.quantity.exception.QuantityMeasurementException;
import org.springframework.stereotype.Component;

@Component
public class QuantityEngine {

    /**
     * Converts a value from its unit to the base unit of its category.
     */
    private double toBase(double value, Unit unit) {
        if (unit.getCategory().equals("Temperature")) {
            if (unit == Unit.FAHRENHEIT) {
                return (value - 32) * 5.0 / 9.0; // to Celsius (base)
            }
            return value; // Celsius is base
        }
        return value * unit.getToBaseFactor();
    }

    /**
     * Converts a base-unit value to the target unit.
     */
    private double fromBase(double baseValue, Unit target) {
        if (target.getCategory().equals("Temperature")) {
            if (target == Unit.FAHRENHEIT) {
                return baseValue * 9.0 / 5.0 + 32;
            }
            return baseValue;
        }
        return baseValue / target.getToBaseFactor();
    }

    public QuantityDTO convert(QuantityDTO q, String targetUnitName) {
        Unit from = Unit.fromString(q.getType(), q.getUnit());
        Unit to = Unit.fromString(q.getType(), targetUnitName);

        if (!from.getCategory().equals(to.getCategory())) {
            throw new QuantityMeasurementException("Cannot convert between different categories");
        }

        double base = toBase(q.getValue(), from);
        double result = fromBase(base, to);

        return new QuantityDTO(result, targetUnitName, q.getType());
    }

    public QuantityDTO add(QuantityDTO q1, QuantityDTO q2) {
        Unit u1 = Unit.fromString(q1.getType(), q1.getUnit());
        Unit u2 = Unit.fromString(q2.getType(), q2.getUnit());

        validateSameCategory(u1, u2);

        double base1 = toBase(q1.getValue(), u1);
        double base2 = toBase(q2.getValue(), u2);
        double resultBase = base1 + base2;
        double result = fromBase(resultBase, u1);

        return new QuantityDTO(result, u1.getUnitName(), q1.getType());
    }

    public QuantityDTO subtract(QuantityDTO q1, QuantityDTO q2) {
        Unit u1 = Unit.fromString(q1.getType(), q1.getUnit());
        Unit u2 = Unit.fromString(q2.getType(), q2.getUnit());

        validateSameCategory(u1, u2);

        double base1 = toBase(q1.getValue(), u1);
        double base2 = toBase(q2.getValue(), u2);
        double resultBase = base1 - base2;
        double result = fromBase(resultBase, u1);

        return new QuantityDTO(result, u1.getUnitName(), q1.getType());
    }

    public double divide(QuantityDTO q1, QuantityDTO q2) {
        Unit u1 = Unit.fromString(q1.getType(), q1.getUnit());
        Unit u2 = Unit.fromString(q2.getType(), q2.getUnit());

        validateSameCategory(u1, u2);

        double base2 = toBase(q2.getValue(), u2);
        if (base2 == 0) {
            throw new QuantityMeasurementException("Cannot divide by zero");
        }

        double base1 = toBase(q1.getValue(), u1);
        return base1 / base2;
    }

    public boolean compare(QuantityDTO q1, QuantityDTO q2) {
        Unit u1 = Unit.fromString(q1.getType(), q1.getUnit());
        Unit u2 = Unit.fromString(q2.getType(), q2.getUnit());

        validateSameCategory(u1, u2);

        double base1 = toBase(q1.getValue(), u1);
        double base2 = toBase(q2.getValue(), u2);

        return Math.abs(base1 - base2) < 1e-9;
    }

    private void validateSameCategory(Unit u1, Unit u2) {
        if (!u1.getCategory().equals(u2.getCategory())) {
            throw new QuantityMeasurementException(
                "Cannot operate on different categories: " + u1.getCategory() + " vs " + u2.getCategory()
            );
        }
    }
}
