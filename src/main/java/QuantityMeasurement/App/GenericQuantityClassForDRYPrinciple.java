package QuantityMeasurement.App;

import java.util.Objects;

public class  GenericQuantityClassForDRYPrinciple {


    // Generic Length Class
    public static class QuantityLength {

       
        private final double value;
        private final LengthUnit unit;

      
        // Enum to represent different length units
        // Base Unit = FEET
        // Conversion factors are defined in terms of FEET
        public enum LengthUnit {
            //UC-1
            FEET(1.0),                
            INCHES(1.0 / 12.0),  
            //UC-2
            YARDS(3.0),               
            CENTIMETERS(0.0328084);  

            private final double conversionFactorToFeet;

            LengthUnit(double conversionFactorToFeet) {
                this.conversionFactorToFeet = conversionFactorToFeet;
            }

            // Convert to base unit (feet)
            public double toBaseUnit(double value) {
                return value * conversionFactorToFeet;
            }
        }
        public QuantityLength(double value, LengthUnit unit) {

            
            if (Double.isNaN(value)) {
                throw new IllegalArgumentException("Value must be numeric");
            }

          
            if (unit == null) {
                throw new IllegalArgumentException("Unit cannot be null");
            }

            this.value = value;
            this.unit = unit;
        }

        
        // Convert the length value to base unit (feet)
        private double convertToBaseUnit() {
            return unit.toBaseUnit(value);
        }

       
        // Compare two Length objects for equality
        public boolean compare(QuantityLength thatLength) {
            return Double.compare(this.convertToBaseUnit(),thatLength.convertToBaseUnit()) == 0;
        }

      
        // Equals method override
      
        @Override
        public boolean equals(Object o) {

            // Reflexive property
            if (this == o)
                return true;

            // Null check
            if (o == null)
                return false;

            // Type safety check
            if (getClass() != o.getClass())
                return false;

            QuantityLength thatLength = (QuantityLength) o;

            // Compare using converted base values
            return this.compare(thatLength);
        }

        // HashCode consistent with equals
        @Override
        public int hashCode() {
            return Objects.hash(convertToBaseUnit());
        }

        @Override
        public String toString() {
            return "Quantity(" + value + ", " + unit.name().toLowerCase() + ")";
        }
    }
    public static void main(String[] args) {

        // Yard to Feet
        QuantityLength yard1 = new QuantityLength(1.0, QuantityLength.LengthUnit.YARDS);
        QuantityLength feet1 = new QuantityLength(3.0, QuantityLength.LengthUnit.FEET);

        System.out.println("Input: " + yard1 + " and " + feet1);
        System.out.println("Output: Equal (" + yard1.equals(feet1) + ")");

        // Yard to Inches
        QuantityLength inches1 = new QuantityLength(36.0, QuantityLength.LengthUnit.INCHES);
        System.out.println("Input: " + yard1 + " and " + inches1);
        System.out.println("Output: Equal (" + yard1.equals(inches1) + ")");

        // Yard to Yard
        QuantityLength yard2 = new QuantityLength(2.0, QuantityLength.LengthUnit.YARDS);
        QuantityLength yard3 = new QuantityLength(2.0, QuantityLength.LengthUnit.YARDS);

        System.out.println("Input: " + yard2 + " and " + yard3);
        System.out.println("Output: Equal (" + yard2.equals(yard3) + ")");

        // Centimeter to Centimeter
        QuantityLength cm1 = new QuantityLength(2.0, QuantityLength.LengthUnit.CENTIMETERS);
        QuantityLength cm2 = new QuantityLength(2.0, QuantityLength.LengthUnit.CENTIMETERS);

        System.out.println("Input: " + cm1 + " and " + cm2);
        System.out.println("Output: Equal (" + cm1.equals(cm2) + ")");

        // Centimeter to Inches
        QuantityLength cm3 = new QuantityLength(1.0, QuantityLength.LengthUnit.CENTIMETERS);
        QuantityLength inchEquivalent = new QuantityLength(0.393701, QuantityLength.LengthUnit.INCHES);

        System.out.println("Input: " + cm3 + " and " + inchEquivalent);
        System.out.println("Output: Equal (" + cm3.equals(inchEquivalent) + ")");
    }
}
