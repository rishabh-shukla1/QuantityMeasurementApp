package QuantityMeasurement.App;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import QuantityMeasurement.App.UnitToUnitConversion.QuantityLength;

public class UnitToUnitConversionTest {
    private static final double EPSILON = 1e-6;

    @Test
    void testConversion_FeetToInches() {
        double result = QuantityLength.convert(1.0,
                QuantityLength.LengthUnit.FEET,
                QuantityLength.LengthUnit.INCHES);
        assertEquals(12.0, result, EPSILON);
    }

    @Test
    void testConversion_InchesToFeet() {
        double result = QuantityLength.convert(24.0,
                QuantityLength.LengthUnit.INCHES,
                QuantityLength.LengthUnit.FEET);
        assertEquals(2.0, result, EPSILON);
    }

    @Test
    void testConversion_YardsToInches() {
        double result = QuantityLength.convert(1.0,
                QuantityLength.LengthUnit.YARDS,
                QuantityLength.LengthUnit.INCHES);
        assertEquals(36.0, result, EPSILON);
    }

    @Test
    void testConversion_InchesToYards() {
        double result = QuantityLength.convert(72.0,
                QuantityLength.LengthUnit.INCHES,
                QuantityLength.LengthUnit.YARDS);
        assertEquals(2.0, result, EPSILON);
    }

    @Test
    void testConversion_CentimetersToInches() {
        double result = QuantityLength.convert(2.54,
                QuantityLength.LengthUnit.CENTIMETERS,
                QuantityLength.LengthUnit.INCHES);
        assertEquals(1.0, result, 1e-4);
    }

    @Test
    void testConversion_FeetToYards() {
        double result = QuantityLength.convert(6.0,
                QuantityLength.LengthUnit.FEET,
                QuantityLength.LengthUnit.YARDS);
        assertEquals(2.0, result, EPSILON);
    }

    @Test
    void testConversion_RoundTrip_PreservesValue() {
        double original = 5.0;
        double converted = QuantityLength.convert(original,
                QuantityLength.LengthUnit.FEET,
                QuantityLength.LengthUnit.INCHES);
        double back = QuantityLength.convert(converted,
                QuantityLength.LengthUnit.INCHES,
                QuantityLength.LengthUnit.FEET);
        assertEquals(original, back, EPSILON);
    }

    @Test
    void testConversion_ZeroValue() {
        double result = QuantityLength.convert(0.0,
                QuantityLength.LengthUnit.FEET,
                QuantityLength.LengthUnit.INCHES);
        assertEquals(0.0, result, EPSILON);
    }

    @Test
    void testConversion_NegativeValue() {
        double result = QuantityLength.convert(-1.0,
                QuantityLength.LengthUnit.FEET,
                QuantityLength.LengthUnit.INCHES);
        assertEquals(-12.0, result, EPSILON);
    }

    @Test
    void testConversion_SameUnit() {
        double result = QuantityLength.convert(5.0,
                QuantityLength.LengthUnit.FEET,
                QuantityLength.LengthUnit.FEET);
        assertEquals(5.0, result, EPSILON);
    }

    @Test
    void testConversion_InvalidUnit_Throws() {
        assertThrows(IllegalArgumentException.class,
                () -> QuantityLength.convert(1.0,
                        null,
                        QuantityLength.LengthUnit.FEET));
    }

    @Test
    void testConversion_NaNOrInfinite_Throws() {
        assertThrows(IllegalArgumentException.class,
                () -> QuantityLength.convert(Double.NaN,
                        QuantityLength.LengthUnit.FEET,
                        QuantityLength.LengthUnit.INCHES));

        assertThrows(IllegalArgumentException.class,
                () -> QuantityLength.convert(Double.POSITIVE_INFINITY,
                        QuantityLength.LengthUnit.FEET,
                        QuantityLength.LengthUnit.INCHES));
    }
    
    @Test
    void testAddition_SameUnit_FeetPlusFeet() {
        QuantityLength q1 = new QuantityLength(1.0, QuantityLength.LengthUnit.FEET);
        QuantityLength q2 = new QuantityLength(2.0, QuantityLength.LengthUnit.FEET);
        QuantityLength result = q1.add(q2);
        assertEquals(new QuantityLength(3.0, QuantityLength.LengthUnit.FEET), result);
    }

    @Test
    void testAddition_SameUnit_InchPlusInch() {
        QuantityLength q1 = new QuantityLength(6.0, QuantityLength.LengthUnit.INCHES);
        QuantityLength q2 = new QuantityLength(6.0, QuantityLength.LengthUnit.INCHES);
        QuantityLength result = q1.add(q2);
        assertEquals(new QuantityLength(12.0, QuantityLength.LengthUnit.INCHES), result);
    }

    @Test
    void testAddition_CrossUnit_FeetPlusInches() {
        QuantityLength q1 = new QuantityLength(1.0, QuantityLength.LengthUnit.FEET);
        QuantityLength q2 = new QuantityLength(12.0, QuantityLength.LengthUnit.INCHES);
        QuantityLength result = q1.add(q2);
        assertEquals(new QuantityLength(2.0, QuantityLength.LengthUnit.FEET), result);
    }

    @Test
    void testAddition_CrossUnit_InchPlusFeet() {
        QuantityLength q1 = new QuantityLength(12.0, QuantityLength.LengthUnit.INCHES);
        QuantityLength q2 = new QuantityLength(1.0, QuantityLength.LengthUnit.FEET);
        QuantityLength result = q1.add(q2);
        assertEquals(new QuantityLength(24.0, QuantityLength.LengthUnit.INCHES), result);
    }

    @Test
    void testAddition_CrossUnit_YardPlusFeet() {
        QuantityLength q1 = new QuantityLength(1.0, QuantityLength.LengthUnit.YARDS);
        QuantityLength q2 = new QuantityLength(3.0, QuantityLength.LengthUnit.FEET);
        QuantityLength result = q1.add(q2);
        assertEquals(new QuantityLength(2.0, QuantityLength.LengthUnit.YARDS), result);
    }

    @Test
    void testAddition_CrossUnit_CentimeterPlusInch() {
        QuantityLength q1 = new QuantityLength(2.54, QuantityLength.LengthUnit.CENTIMETERS);
        QuantityLength q2 = new QuantityLength(1.0, QuantityLength.LengthUnit.INCHES);
        QuantityLength result = q1.add(q2);
        QuantityLength expected = new QuantityLength(5.08, QuantityLength.LengthUnit.CENTIMETERS);
        assertEquals(expected, result);
    }

    @Test
    void testAddition_Commutativity() {
        QuantityLength q1 = new QuantityLength(1.0, QuantityLength.LengthUnit.FEET);
        QuantityLength q2 = new QuantityLength(12.0, QuantityLength.LengthUnit.INCHES);
        QuantityLength result1 = q1.add(q2);
        QuantityLength result2 = q2.add(q1);
        assertEquals(result1.convertTo(QuantityLength.LengthUnit.FEET),
                     result2.convertTo(QuantityLength.LengthUnit.FEET));
    }

    @Test
    void testAddition_WithZero() {
        QuantityLength q1 = new QuantityLength(5.0, QuantityLength.LengthUnit.FEET);
        QuantityLength q2 = new QuantityLength(0.0, QuantityLength.LengthUnit.INCHES);
        QuantityLength result = q1.add(q2);
        assertEquals(new QuantityLength(5.0, QuantityLength.LengthUnit.FEET), result);
    }

    @Test
    void testAddition_NegativeValues() {
        QuantityLength q1 = new QuantityLength(5.0, QuantityLength.LengthUnit.FEET);
        QuantityLength q2 = new QuantityLength(-2.0, QuantityLength.LengthUnit.FEET);
        QuantityLength result = q1.add(q2);
        assertEquals(new QuantityLength(3.0, QuantityLength.LengthUnit.FEET), result);
    }

    @Test
    void testAddition_NullSecondOperand() {
        QuantityLength q1 = new QuantityLength(1.0, QuantityLength.LengthUnit.FEET);
        assertThrows(IllegalArgumentException.class, () -> q1.add(null));
    }

    @Test
    void testAddition_LargeValues() {
        QuantityLength q1 = new QuantityLength(1e6, QuantityLength.LengthUnit.FEET);
        QuantityLength q2 = new QuantityLength(1e6, QuantityLength.LengthUnit.FEET);
        QuantityLength result = q1.add(q2);
        assertEquals(new QuantityLength(2e6, QuantityLength.LengthUnit.FEET), result);
    }

    @Test
    void testAddition_SmallValues() {
        QuantityLength q1 = new QuantityLength(0.001, QuantityLength.LengthUnit.FEET);
        QuantityLength q2 = new QuantityLength(0.002, QuantityLength.LengthUnit.FEET);
        QuantityLength result = q1.add(q2);
        QuantityLength expected = new QuantityLength(0.003, QuantityLength.LengthUnit.FEET);
        assertEquals(expected, result);
    }
}