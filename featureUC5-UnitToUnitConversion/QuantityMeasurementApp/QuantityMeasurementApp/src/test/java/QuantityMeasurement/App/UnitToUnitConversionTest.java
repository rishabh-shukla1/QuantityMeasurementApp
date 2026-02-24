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
}
