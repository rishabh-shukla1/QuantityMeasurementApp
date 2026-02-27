package QuantityMeasurement.App;

public class FeetMeasurementUC1{

    // UC1-Inner class to represent Feet measurement
    public static class Feet {

        private final double value;

        // Constructor
        public Feet(double value) {
            this.value = value;
        }

        public double getValue() {
            return value;
        }

        /**
         * Override equals() method
         * Implements full equality contract
         */
        @Override
        public boolean equals(Object obj) {

            //Reference check
            if (this == obj) {
                return true;
            }

            //Null check
            if (obj == null) {
                return false;
            }

            //Type check
            if (getClass() != obj.getClass()) {
                return false;
            }

            //Cast safely
            Feet other = (Feet) obj;

            //Compare values using Double.compare()
            return Double.compare(this.value, other.value) == 0;
        }

        // Recommended when overriding equals()
        @Override
        public int hashCode() {
            return Double.hashCode(value);
        }
    }

    public static void main(String[] args) {

        Feet value1 = new Feet(1.0);
        Feet value2 = new Feet(1.0);

        boolean result = value1.equals(value2);

        System.out.println("Input: 1.0 ft and 1.0 ft");
        System.out.println("Output: Equal (" + result + ")");
    }
}
