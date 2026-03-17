package units;

import java.util.Objects;
import java.util.function.DoubleBinaryOperator;

public class Quantity<U extends IMeasurable> {

    private final double value;
    private final U unit;

    public Quantity(double value, U unit) {
        if (unit == null) {
            throw new IllegalArgumentException("Unit cannot be null");
        }
        if (!Double.isFinite(value)) {
            throw new IllegalArgumentException("Invalid value");
        }
        this.value = value;
        this.unit = unit;
    }

    public double getValue() {
        return value;
    }

    public U getUnit() {
        return unit;
    }

    private double toBaseValue() {
        return value * unit.getConversionFactor();
    }

    public Quantity<U> convertTo(U targetUnit) {
        if (targetUnit == null) {
            throw new IllegalArgumentException("Target unit cannot be null");
        }

        if (!unit.getMeasurementType().equalsIgnoreCase(targetUnit.getMeasurementType())) {
            throw new IllegalArgumentException("Units belong to different measurement types");
        }

        if (isTemperatureUnit(unit) && isTemperatureUnit(targetUnit)) {
            double celsiusValue = toCelsius(value, unit);
            double convertedValue = fromCelsius(celsiusValue, targetUnit);
            return new Quantity<>(convertedValue, targetUnit);
        }

        double baseValue = this.toBaseValue();
        double convertedValue = baseValue / targetUnit.getConversionFactor();
        return new Quantity<>(convertedValue, targetUnit);
    }

    public Quantity<U> add(Quantity<U> other) {
        validateQuantity(other);

        if (isTemperatureUnit(this.unit)) {
            throw new UnsupportedOperationException("Addition not supported for temperature");
        }

        double resultBase = this.toBaseValue() + other.toBaseValue();
        double resultValue = resultBase / unit.getConversionFactor();
        return new Quantity<>(resultValue, unit);
    }

    public Quantity<U> subtract(Quantity<U> other) {
        validateQuantity(other);

        if (isTemperatureUnit(this.unit)) {
            throw new UnsupportedOperationException("Subtraction not supported for temperature");
        }

        double resultBase = this.toBaseValue() - other.toBaseValue();
        double resultValue = resultBase / unit.getConversionFactor();
        return new Quantity<>(resultValue, unit);
    }

    public Quantity<U> divide(Quantity<U> other) {
        validateQuantity(other);

        if (isTemperatureUnit(this.unit)) {
            throw new UnsupportedOperationException("Division not supported for temperature");
        }

        double denominator = other.toBaseValue();
        if (Math.abs(denominator) < 0.0000001) {
            throw new ArithmeticException("Cannot divide by zero");
        }

        double resultBase = this.toBaseValue() / denominator;
        double resultValue = resultBase / unit.getConversionFactor();

        return new Quantity<>(resultValue, unit);
    }
    private void validateQuantity(Quantity<U> other) {
        if (other == null) {
            throw new IllegalArgumentException("Quantity cannot be null");
        }

        if (!this.unit.getMeasurementType().equalsIgnoreCase(other.unit.getMeasurementType())) {
            throw new IllegalArgumentException("Units belong to different measurement types");
        }
    }

    private boolean isTemperatureUnit(IMeasurable unit) {
        return unit.getMeasurementType().equalsIgnoreCase("TEMPERATURE");
    }

    private double toComparableValue() {
        if (isTemperatureUnit(unit)) {
            return toCelsius(value, unit);
        }
        return toBaseValue();
    }

    private double toCelsius(double value, IMeasurable unit) {
        String unitName = unit.toString().toUpperCase();

        switch (unitName) {
            case "CELSIUS":
                return value;
            case "FAHRENHEIT":
                return (value - 32) * 5 / 9;
            case "KELVIN":
                return value - 273.15;
            default:
                throw new IllegalArgumentException("Unknown temperature unit: " + unitName);
        }
    }

    private double fromCelsius(double celsiusValue, IMeasurable unit) {
        String unitName = unit.toString().toUpperCase();

        switch (unitName) {
            case "CELSIUS":
                return celsiusValue;
            case "FAHRENHEIT":
                return (celsiusValue * 9 / 5) + 32;
            case "KELVIN":
                return celsiusValue + 273.15;
            default:
                throw new IllegalArgumentException("Unknown temperature unit: " + unitName);
        }
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof Quantity<?> other)) return false;

        if (!this.unit.getMeasurementType().equalsIgnoreCase(other.getUnit().getMeasurementType())) {
            return false;
        }

        double thisValue = this.toComparableValue();
        double otherValue = other.toComparableValue();

        return Math.abs(thisValue - otherValue) < 0.0001;
    }

    @Override
    public int hashCode() {
        return Objects.hash(unit.getMeasurementType(), Math.round(toComparableValue() * 10000));
    }

    @Override
    public String toString() {
        return value + " " + unit;
    }
}