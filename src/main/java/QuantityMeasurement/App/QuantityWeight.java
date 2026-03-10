package QuantityMeasurement.App;

import java.util.Objects;

public class QuantityWeight {

	// UC9
    private final double value;
    private final WeightUnit unit;

    
    private static final double EPSILON = 1e-6;

    
    
    public QuantityWeight(double value, WeightUnit unit) {

        if (!Double.isFinite(value))
            throw new IllegalArgumentException("Invalid numeric value");

        
        if (unit == null)
            throw new IllegalArgumentException("Unit cannot be null");

        this.value = value;
        this.unit = unit;
    }

    // Conversion
    public QuantityWeight convertTo(WeightUnit targetUnit) {

        double base = this.unit.convertToBaseUnit(this.value);
        double converted = targetUnit.convertFromBaseUnit(base);

        return new QuantityWeight(converted, targetUnit);
    }

    // Addition (result in first unit)
    public QuantityWeight add(QuantityWeight other) {

        if (other == null)
            throw new IllegalArgumentException("Second operand cannot be null");

        
        double base1 = unit.convertToBaseUnit(value);
        
        double base2 = other.unit.convertToBaseUnit(other.value);

        double sum = base1 + base2;

        double result = unit.convertFromBaseUnit(sum);

        return new QuantityWeight(result, unit);
    }

    
    
    // Addition with target unit
    public static QuantityWeight add(
            QuantityWeight a,
            QuantityWeight b,
            WeightUnit targetUnit) {

        if (a == null || b == null)
            throw new IllegalArgumentException("Weights cannot be null");

        if (targetUnit == null)
            throw new IllegalArgumentException("Target unit cannot be null");

        double base1 = a.unit.convertToBaseUnit(a.value);
        double base2 = b.unit.convertToBaseUnit(b.value);

        double sum = base1 + base2;

        double result = targetUnit.convertFromBaseUnit(sum);

        return new QuantityWeight(result, targetUnit);
    }

    @Override
    public boolean equals(Object obj) {

        if (this == obj)
            return true;

        if (obj == null || getClass() != obj.getClass())
            return false;

        QuantityWeight other = (QuantityWeight) obj;

        QuantityWeight thisInGram = this.convertTo(WeightUnit.GRAM);
        QuantityWeight otherInGram = other.convertTo(WeightUnit.GRAM);

        return Math.abs(thisInGram.value - otherInGram.value) < 0.01;
    }

    @Override
    public int hashCode()
    {
        return Objects.hash(unit.convertToBaseUnit(value));
    }

    @Override
    public String toString() 
    {
        return "Quantity(" + value + ", " + unit + ")";
    }

    public static void main(String[] args) {

        QuantityWeight w1 = new QuantityWeight(1.0, WeightUnit.KILOGRAM);
        
        QuantityWeight w2 = new QuantityWeight(1000.0, WeightUnit.GRAM);

        System.out.println("Input: " + w1 + ".equals(" + w2 + ")");
        
        System.out.println("Output: " + w1.equals(w2));

        System.out.println();

        System.out.println("Input: " + w1 + ".convertTo(GRAM)");
        System.out.println("Output: " + w1.convertTo(WeightUnit.GRAM));

        System.out.println();

        QuantityWeight w3 = new QuantityWeight(2.20462, WeightUnit.POUND);

        System.out.println("Input: " + w3 + ".convertTo(KILOGRAM)");
        System.out.println("Output: " + w3.convertTo(WeightUnit.KILOGRAM));

        System.out.println();

        System.out.println("Input: " + w1 + ".add(" + w2 + ")");
        System.out.println("Output: " + w1.add(w2));
        

        System.out.println();

        System.out.println("Input: add(" + w1 + ", " + w2 + ", GRAM)");
        System.out.println("Output: " +
                QuantityWeight.add(w1, w2, WeightUnit.GRAM));
    }
}