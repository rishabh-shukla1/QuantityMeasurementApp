package QuantityMeasurement.App;

import java.util.Objects;


public class UnitToUnitConversion {

   
    // Generic QuantityLength Class
    public static class QuantityLength {

        private final double value;
        private final LengthUnit unit;

        private static final double EPSILON = 1e-6;

       
        // Enum defining supported units
   
        public enum LengthUnit {

            FEET(1.0),
            INCHES(1.0 / 12.0),
            YARDS(3.0),
            CENTIMETERS(0.0328084);

            private final double conversionFactorToFeet;

            LengthUnit(double factor) {
                this.conversionFactorToFeet = factor;
            }

            public double getFactor() {
                return conversionFactorToFeet;
            }
        }

        // Constructor with validation
        public QuantityLength(double value, LengthUnit unit) {

            if (!Double.isFinite(value))
                throw new IllegalArgumentException("Invalid numeric value");

            if (unit == null)
                throw new IllegalArgumentException("Unit cannot be null");

            this.value = value;
            this.unit = unit;
        }

     
        // Static Conversion Method
        public static double convert(double value,LengthUnit source,LengthUnit target) {

            if (!Double.isFinite(value))
                throw new IllegalArgumentException("Invalid numeric value");

            if (source == null || target == null)
                throw new IllegalArgumentException("Unit cannot be null");

            // Convert to base unit (feet)
            double valueInFeet = value * source.getFactor();

            // Convert to target unit
            return valueInFeet / target.getFactor();
        }

        // Instance conversion method
        public QuantityLength convertTo(LengthUnit targetUnit) {
            double converted = convert(this.value, this.unit, targetUnit);
            return new QuantityLength(converted, targetUnit);
        }

        // Equality using epsilon comparison
        @Override
        public boolean equals(Object o) {

            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;

            QuantityLength other = (QuantityLength) o;

            double thisBase = this.value * this.unit.getFactor();
            double otherBase = other.value * other.unit.getFactor();

            return Math.abs(thisBase - otherBase) < EPSILON;
        }

        @Override
        public int hashCode() {
            return Objects.hash(value * unit.getFactor());
        }

        @Override
        public String toString() {
            return "Quantity(" + value + ", " + unit + ")";
        }
    }

   
    // Main Method for Testing
   
    public static void main(String[] args) {

        System.out.println("convert(1.0, FEET, INCHES) → " +
                QuantityLength.convert(1.0,
                        QuantityLength.LengthUnit.FEET,
                        QuantityLength.LengthUnit.INCHES));

        System.out.println("convert(3.0, YARDS, FEET) → " +
                QuantityLength.convert(3.0,
                        QuantityLength.LengthUnit.YARDS,
                        QuantityLength.LengthUnit.FEET));

        System.out.println("convert(36.0, INCHES, YARDS) → " +
                QuantityLength.convert(36.0,
                        QuantityLength.LengthUnit.INCHES,
                        QuantityLength.LengthUnit.YARDS));

        System.out.println("convert(1.0, CENTIMETERS, INCHES) → " +
                QuantityLength.convert(1.0,
                        QuantityLength.LengthUnit.CENTIMETERS,
                        QuantityLength.LengthUnit.INCHES));
    }
}
