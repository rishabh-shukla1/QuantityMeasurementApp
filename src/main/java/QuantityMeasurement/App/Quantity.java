package QuantityMeasurement.App;

public class Quantity<U extends IMeasurable> {

    private final double value;
    private final U unit;

    public Quantity(double value, U unit) {

    	
        if (unit == null)
            throw new IllegalArgumentException("Unit cannot be null");

        if (Double.isNaN(value) || Double.isInfinite(value))
            throw new IllegalArgumentException("Invalid value");

        this.value = value;
        this.unit = unit;
    }

    public Quantity<U> convertTo(U targetUnit) {

        double base = unit.convertToBaseUnit(value);
        
        double converted = targetUnit.convertFromBaseUnit(base);

        return new Quantity<>(Math.round(converted * 100.0) / 100.0, targetUnit);
    }

    public Quantity<U> add(Quantity<U> other) {

        double base1 = unit.convertToBaseUnit(value);
        
        double base2 = other.unit.convertToBaseUnit(other.value);

        double sumBase = base1 + base2;

        double result = unit.convertFromBaseUnit(sumBase);

        return new Quantity<>(Math.round(result * 100.0) / 100.0, unit);
    }

    public Quantity<U> add(Quantity<U> other, U targetUnit) {

        double base1 = unit.convertToBaseUnit(value);
        double base2 = other.unit.convertToBaseUnit(other.value);

        double sumBase = base1 + base2;

        double result = targetUnit.convertFromBaseUnit(sumBase);

        return new Quantity<>(Math.round(result * 100.0) / 100.0, targetUnit);
    }
    
    public Quantity<U> subtract(Quantity<U> other) {

        if(other == null) {
            throw new IllegalArgumentException("Quantity cannot be null");
        }
        if(!unit.getClass().equals(other.unit.getClass())) {
            throw new IllegalArgumentException("Different measurement categories");
        }

        double base1 = unit.convertToBaseUnit(value);
        double base2 = other.unit.convertToBaseUnit(other.value);

        double resultBase = base1 - base2;

        double result = unit.convertFromBaseUnit(resultBase);

        result = Math.round(result * 100.0) / 100.0;

        return new Quantity<>(result, unit);
    }
    
    public Quantity<U> subtract(Quantity<U> other, U targetUnit) {

        if(other == null) {
            throw new IllegalArgumentException("Quantity cannot be null");
        }
        if(targetUnit == null) {
            throw new IllegalArgumentException("Target unit cannot be null");
        }
        if(!unit.getClass().equals(other.unit.getClass())) {
            throw new IllegalArgumentException("Different measurement categories");
        }

        double base1 = unit.convertToBaseUnit(value);
        double base2 = other.unit.convertToBaseUnit(other.value);

        double resultBase = base1 - base2;

        double result = targetUnit.convertFromBaseUnit(resultBase);

        result = Math.round(result * 100.0) / 100.0;

        return new Quantity<>(result, targetUnit);
    }
    
    public double divide(Quantity<U> other) {

        if(other == null) {
            throw new IllegalArgumentException("Quantity cannot be null");
        }
        if(!unit.getClass().equals(other.unit.getClass())) {
            throw new IllegalArgumentException("Different measurement categories");
        }

        double base1 = unit.convertToBaseUnit(value);
        double base2 = other.unit.convertToBaseUnit(other.value);

        if(base2 == 0) {
            throw new ArithmeticException("Division by zero");
        }

        return base1 / base2;
    }


    @Override
    public boolean equals(Object obj) {

        if (this == obj)
            return true;

        if (obj == null || getClass() != obj.getClass())
            return false;

        Quantity<?> other = (Quantity<?>) obj;

        if (!unit.getClass().equals(other.unit.getClass()))
            return false;

        double base1 = unit.convertToBaseUnit(value);
        
        
        double base2 = ((IMeasurable) other.unit).convertToBaseUnit(other.value);

        return Math.abs(base1 - base2) < 0.01;
    }

    @Override
    public int hashCode() 
    {
        return Double.valueOf(value).hashCode() + unit.hashCode();
    }

    @Override
    public String toString() 
    {
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
        
        Quantity<VolumeUnit> v1 = new Quantity<>(1.0, VolumeUnit.LITRE);
        Quantity<VolumeUnit> v2 = new Quantity<>(1000.0, VolumeUnit.MILLILITRE);
        Quantity<VolumeUnit> v3 = new Quantity<>(1.0, VolumeUnit.GALLON);

        System.out.println("Equality:");
        System.out.println(v1.equals(v2));

        System.out.println("Conversion:");
        System.out.println(v3.convertTo(VolumeUnit.LITRE));

        System.out.println("Addition:");
        System.out.println(v1.add(v2));

        System.out.println("Addition with target unit:");
        System.out.println(v1.add(v3, VolumeUnit.MILLILITRE));
        
        Quantity<LengthUnit> q3 =
                new Quantity<>(10.0, LengthUnit.FEET);

        Quantity<LengthUnit> q4 =
                new Quantity<>(6.0, LengthUnit.INCHES);

        System.out.println("Subtraction (implicit): "
                + q3.subtract(q4));

        System.out.println("Subtraction (explicit inches): "
                + q3.subtract(q4, LengthUnit.INCHES));

        Quantity<LengthUnit> q5 =
                new Quantity<>(2.0, LengthUnit.FEET);

        System.out.println("Division: "
                + q5.divide(q5));
    }
}
