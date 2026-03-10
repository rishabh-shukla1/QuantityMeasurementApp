package QuantityMeasurement.App;

import java.util.Objects;

public class UnitToUnitConversion {

    public static class QuantityLength {

        private final double value;
        private final LengthUnit unit;

        private static final double EPSILON = 1e-6;

        public QuantityLength(double value, LengthUnit unit) {

            if (!Double.isFinite(value))
                throw new IllegalArgumentException("Invalid numeric value");

            if (unit == null)
                throw new IllegalArgumentException("Unit cannot be null");

            this.value = value;
            this.unit = unit;
        }

        // Conversion method
        public QuantityLength convertTo(LengthUnit targetUnit) {

            double baseValue = unit.convertToBaseUnit(value);
            double converted = targetUnit.convertFromBaseUnit(baseValue);

            return new QuantityLength(converted, targetUnit);
        }

        // UC6 addition
        public QuantityLength add(QuantityLength other) {

            if (other == null)
                throw new IllegalArgumentException("Second operand cannot be null");

            double base1 = unit.convertToBaseUnit(value);
            double base2 = other.unit.convertToBaseUnit(other.value);

            double sumBase = base1 + base2;

            double result = unit.convertFromBaseUnit(sumBase);

            return new QuantityLength(result, unit);
        }

        // UC7 addition with target unit
        public static QuantityLength add(
                QuantityLength a,
                QuantityLength b,
                LengthUnit targetUnit) {

            if (a == null || b == null)
                throw new IllegalArgumentException("Quantities cannot be null");

            if (targetUnit == null)
                throw new IllegalArgumentException("Target unit cannot be null");

            double base1 = a.unit.convertToBaseUnit(a.value);
            double base2 = b.unit.convertToBaseUnit(b.value);

            double sumBase = base1 + base2;

            double result = targetUnit.convertFromBaseUnit(sumBase);

            return new QuantityLength(result, targetUnit);
        }

        @Override
        public boolean equals(Object o) {

            if (this == o)
                return true;

            if (!(o instanceof QuantityLength))
                return false;

            QuantityLength other = (QuantityLength) o;

            double base1 = unit.convertToBaseUnit(value);
            double base2 = other.unit.convertToBaseUnit(other.value);

            return Math.abs(base1 - base2) < EPSILON;
        }

        @Override
        public int hashCode() {
            return Objects.hash(unit.convertToBaseUnit(value));
        }

        @Override
        public String toString() {
            return "Quantity(" + value + ", " + unit + ")";
        }
    }

    // ---------------- Main Method ----------------

    public static void main(String[] args) {

        QuantityLength q1 = new QuantityLength(1.0, LengthUnit.FEET);
        QuantityLength q2 = new QuantityLength(12.0, LengthUnit.INCHES);

        System.out.println("Input: " + q1 + ".convertTo(INCHES)");
        System.out.println("Output: " + q1.convertTo(LengthUnit.INCHES));

        System.out.println();

        System.out.println("Input: add(" + q1 + ", " + q2 + ", FEET)");
        System.out.println("Output: " +
                QuantityLength.add(q1, q2, LengthUnit.FEET));

        System.out.println();

        QuantityLength q3 = new QuantityLength(36.0, LengthUnit.INCHES);
        QuantityLength q4 = new QuantityLength(1.0, LengthUnit.YARDS);

        System.out.println("Input: " + q3 + ".equals(" + q4 + ")");
        System.out.println("Output: " + q3.equals(q4));

        System.out.println();

        System.out.println("Input: LengthUnit.INCHES.convertToBaseUnit(12)");
        System.out.println("Output: " +
                LengthUnit.INCHES.convertToBaseUnit(12));
    }
}