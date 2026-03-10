package QuantityMeasurement.App;

import java.util.function.DoubleBinaryOperator;

public class Quantity<U extends IMeasurable> {

    private final double value;
    private final U unit;

    public Quantity(double value, U unit) {

        if(unit == null) {
            throw new IllegalArgumentException("Unit cannot be null");
        }
        if(Double.isNaN(value) || Double.isInfinite(value)) {
            throw new IllegalArgumentException("Invalid value");
        }
        this.value = value;
        this.unit = unit;
    }

    // UC13 ENUM
    private enum ArithmeticOperation {

        ADD((a, b) -> a + b),

        SUBTRACT((a, b) -> a - b),

        DIVIDE((a, b) -> {
            if(b == 0) {
                throw new ArithmeticException("Division by zero");
            }
            return a / b;
        });

        private final DoubleBinaryOperator operator;
        ArithmeticOperation(DoubleBinaryOperator operator) {
            this.operator = operator;
        }

        public double compute(double a, double b) {
            return operator.applyAsDouble(a, b);
        }
    }

    // helper methods
    private void validateArithmeticOperands(Quantity<U> other, U targetUnit, boolean targetRequired) {
        if(other == null) {
            throw new IllegalArgumentException("Quantity cannot be null");
        }

        if(!unit.getClass().equals(other.unit.getClass())) {
            throw new IllegalArgumentException("Different measurement categories");
        }

        if(!Double.isFinite(this.value) || !Double.isFinite(other.value)) {
            throw new IllegalArgumentException("Values must be finite");
        }

        if(targetRequired && targetUnit == null) {
            throw new IllegalArgumentException("Target unit cannot be null");
        }
    }

    private double performBaseArithmetic(Quantity<U> other, ArithmeticOperation operation) {

        double base1 = unit.convertToBaseUnit(value);
        double base2 = other.unit.convertToBaseUnit(other.value);

        return operation.compute(base1, base2);
    }

    private double roundTwo(double val) {
        return Math.round(val * 100.0) / 100.0;
    }

    // convert
    public Quantity<U> convertTo(U targetUnit) {

        double base = unit.convertToBaseUnit(value);
        double converted = targetUnit.convertFromBaseUnit(base);

        return new Quantity<>(roundTwo(converted), targetUnit);
    }

    // add
    public Quantity<U> add(Quantity<U> other) {

        unit.validateOperationSupport("addition");

        validateArithmeticOperands(other, null, false);

        double resultBase = performBaseArithmetic(other, ArithmeticOperation.ADD);

        double result = unit.convertFromBaseUnit(resultBase);

        return new Quantity<>(roundTwo(result), unit);
    }
    
    public Quantity<U> add(Quantity<U> other, U targetUnit) {

        validateArithmeticOperands(other, targetUnit, true);

        double resultBase = performBaseArithmetic(other, ArithmeticOperation.ADD);

        double result = targetUnit.convertFromBaseUnit(resultBase);

        return new Quantity<>(roundTwo(result), targetUnit);
    }

    // Subtract  - UC12
    public Quantity<U> subtract(Quantity<U> other) {

        unit.validateOperationSupport("subtraction");

        validateArithmeticOperands(other, null, false);

        double resultBase = performBaseArithmetic(other, ArithmeticOperation.SUBTRACT);

        double result = unit.convertFromBaseUnit(resultBase);

        return new Quantity<>(roundTwo(result), unit);
    }

    public Quantity<U> subtract(Quantity<U> other, U targetUnit) {

        validateArithmeticOperands(other, targetUnit, true);

        double resultBase = performBaseArithmetic(other, ArithmeticOperation.SUBTRACT);

        double result = targetUnit.convertFromBaseUnit(resultBase);

        return new Quantity<>(roundTwo(result), targetUnit);
    }

    // Divide
    public double divide(Quantity<U> other) {

        unit.validateOperationSupport("division");

        validateArithmeticOperands(other, null, false);

        return performBaseArithmetic(other, ArithmeticOperation.DIVIDE);
    }
    // Equals
    @Override
    public boolean equals(Object obj) {

        if(this == obj) {
        	return true;
        }

        if(obj == null || getClass() != obj.getClass()) {
        	return false;
        }
        Quantity<?> other = (Quantity<?>) obj;

        if(!unit.getClass().equals(other.unit.getClass())) {
        	return false;
        }
        double base1 = unit.convertToBaseUnit(value);
        double base2 = ((IMeasurable) other.unit).convertToBaseUnit(other.value);

        return Math.abs(base1 - base2) < 0.01;
    }

    @Override
    public int hashCode() {
        return Double.valueOf(value).hashCode() + unit.hashCode();
    }

    @Override
    public String toString() {
        return "Quantity(" + value + ", " + unit.getUnitName() + ")";
    }

    public static void main(String[] args) {

        System.out.println("Length Operations:");

        Quantity<LengthUnit> q1 = new Quantity<>(1.0, LengthUnit.FEET);
        Quantity<LengthUnit> q2 = new Quantity<>(12.0, LengthUnit.INCHES);

        System.out.println("1 foot == 12 inches : " + q1.equals(q2));
        System.out.println("1 foot to inches : " + q1.convertTo(LengthUnit.INCHES));

        Quantity<LengthUnit> lengthSum = q1.add(q2, LengthUnit.FEET);
        System.out.println("1 foot + 12 inches in feet : " + lengthSum);

        System.out.println("\nWeight Operations:");

        Quantity<WeightUnit> w1 = new Quantity<>(1.0, WeightUnit.KILOGRAM);
        Quantity<WeightUnit> w2 = new Quantity<>(1000.0, WeightUnit.GRAM);

        System.out.println("1 kg == 1000 g : " + w1.equals(w2));
        System.out.println("1 kg to grams : " + w1.convertTo(WeightUnit.GRAM));

        Quantity<WeightUnit> weightSum = w1.add(w2, WeightUnit.KILOGRAM);
        System.out.println("1 kg + 1000 g in kg : " + weightSum);

        System.out.println("\nVolume Operations:");

        Quantity<VolumeUnit> v1 = new Quantity<>(1.0, VolumeUnit.LITRE);
        Quantity<VolumeUnit> v2 = new Quantity<>(1000.0, VolumeUnit.MILLILITRE);
        Quantity<VolumeUnit> v3 = new Quantity<>(1.0, VolumeUnit.GALLON);

        System.out.println("1 L == 1000 mL : " + v1.equals(v2));
        System.out.println("1 L to mL : " + v1.convertTo(VolumeUnit.MILLILITRE));
        System.out.println("1 Gallon to Litre : " + v3.convertTo(VolumeUnit.LITRE));

        System.out.println("1 L + 1000 mL in Litre : " + v1.add(v2, VolumeUnit.LITRE));
        System.out.println("1 L + 1 Gallon in mL : " + v1.add(v3, VolumeUnit.MILLILITRE));

        System.out.println("\nSubtraction & Division:");

        Quantity<LengthUnit> q3 = new Quantity<>(10.0, LengthUnit.FEET);
        Quantity<LengthUnit> q4 = new Quantity<>(6.0, LengthUnit.INCHES);

        System.out.println("Subtraction implicit : " + q3.subtract(q4));
        System.out.println("Subtraction explicit : " + q3.subtract(q4, LengthUnit.INCHES));

        Quantity<LengthUnit> q5 = new Quantity<>(2.0, LengthUnit.FEET);

        System.out.println("Division : " + q3.divide(q5));
        
        System.out.println("\nTemperature Operations:");

        Quantity<TemperatureUnit> t1 =
                new Quantity<>(0.0, TemperatureUnit.CELSIUS);

        Quantity<TemperatureUnit> t2 =
                new Quantity<>(32.0, TemperatureUnit.FAHRENHEIT);

        System.out.println("0C == 32F : " + t1.equals(t2));

        System.out.println("100C to F : " +
                new Quantity<>(100.0, TemperatureUnit.CELSIUS)
                        .convertTo(TemperatureUnit.FAHRENHEIT));
        
        
       
    }
}