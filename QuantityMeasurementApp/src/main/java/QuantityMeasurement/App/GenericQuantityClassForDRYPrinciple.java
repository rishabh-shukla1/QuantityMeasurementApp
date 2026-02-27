package QuantityMeasurement.App;

import java.util.Objects;

public class  GenericQuantityClassForDRYPrinciple {


    // Generic Length Class
    public static class Length {

       
        private final double value;
        private final LengthUnit unit;

      
        // Enum to represent different length units
        // Base Unit = FEET
        // Conversion factors are defined in terms of FEET
        public enum LengthUnit {

            FEET(1.0),        
            INCHES(1.0 / 12.0); 

            private final double conversionFactorToFeet;

            LengthUnit(double conversionFactorToFeet) {
                this.conversionFactorToFeet = conversionFactorToFeet;
            }

         
            public double toBaseUnit(double value) {
                return value * conversionFactorToFeet;
            }
        }
        public Length(double value, LengthUnit unit) {

            
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
       
        // ==========================================
        public boolean compare(Length thatLength) {
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

            Length thatLength = (Length) o;

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

       
        Length length1 = new Length(1.0, Length.LengthUnit.FEET);
        Length length2 = new Length(12.0, Length.LengthUnit.INCHES);

        System.out.println("Input: " + length1 + " and " + length2);
        System.out.println("Output: Equal (" + length1.equals(length2) + ")");

        Length length3 = new Length(1.0, Length.LengthUnit.INCHES);
        Length length4 = new Length(1.0, Length.LengthUnit.INCHES);

        System.out.println("Input: " + length3 + " and " + length4);
        System.out.println("Output: Equal (" + length3.equals(length4) + ")");
    }
}
