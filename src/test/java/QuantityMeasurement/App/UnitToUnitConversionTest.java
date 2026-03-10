package QuantityMeasurement.App;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import QuantityMeasurement.App.UnitToUnitConversion.QuantityLength;

public class UnitToUnitConversionTest {

    private static final double EPSILON = 1e-6;

    @Test
    void testConvertFeetToInches() {

        UnitToUnitConversion.QuantityLength q =
                new UnitToUnitConversion.QuantityLength(1.0, LengthUnit.FEET);

        UnitToUnitConversion.QuantityLength result =
                q.convertTo(LengthUnit.INCHES);

        assertEquals(
                new UnitToUnitConversion.QuantityLength(12.0, LengthUnit.INCHES),
                result);
    }

    @Test
    void testConvertInchesToFeet() {

        UnitToUnitConversion.QuantityLength q =
                new UnitToUnitConversion.QuantityLength(12.0, LengthUnit.INCHES);

        UnitToUnitConversion.QuantityLength result =
                q.convertTo(LengthUnit.FEET);

        assertEquals(
                new UnitToUnitConversion.QuantityLength(1.0, LengthUnit.FEET),
                result);
    }

    @Test
    void testConvertYardsToFeet() {

        UnitToUnitConversion.QuantityLength q =
                new UnitToUnitConversion.QuantityLength(1.0, LengthUnit.YARDS);

        UnitToUnitConversion.QuantityLength result =
                q.convertTo(LengthUnit.FEET);

        assertEquals(
                new UnitToUnitConversion.QuantityLength(3.0, LengthUnit.FEET),
                result);
    }

    @Test
    void testConvertCentimetersToInches() {

    	UnitToUnitConversion.QuantityLength q =
                new UnitToUnitConversion.QuantityLength(2.54, LengthUnit.CENTIMETERS);

    	UnitToUnitConversion.QuantityLength result =
                q.convertTo(LengthUnit.INCHES);

        assertEquals(
                new UnitToUnitConversion.QuantityLength(1.0, LengthUnit.INCHES),
                result);
    }

    @Test
    void testAdditionSameUnit() {

    	UnitToUnitConversion.QuantityLength q1 =
                new UnitToUnitConversion.QuantityLength(1.0, LengthUnit.FEET);

    	UnitToUnitConversion.QuantityLength q2 =
                new UnitToUnitConversion.QuantityLength(1.0, LengthUnit.FEET);

    	UnitToUnitConversion.QuantityLength result = q1.add(q2);

        assertEquals(
                new UnitToUnitConversion.QuantityLength(2.0, LengthUnit.FEET),
                result);
    }

    @Test
    void testAdditionDifferentUnits() {

    	UnitToUnitConversion.QuantityLength q1 =
                new UnitToUnitConversion.QuantityLength(1.0, LengthUnit.FEET);

        UnitToUnitConversion.QuantityLength q2 =
                new UnitToUnitConversion.QuantityLength(12.0, LengthUnit.INCHES);

        UnitToUnitConversion.QuantityLength result = q1.add(q2);

        assertEquals(
                new UnitToUnitConversion.QuantityLength(2.0, LengthUnit.FEET),
                result);
    }

    @Test
    void testAdditionWithTargetUnit() {

        UnitToUnitConversion.QuantityLength q1 =
                new UnitToUnitConversion.QuantityLength(1.0, LengthUnit.FEET);

        UnitToUnitConversion.QuantityLength q2 =
                new UnitToUnitConversion.QuantityLength(12.0, LengthUnit.INCHES);

        UnitToUnitConversion.QuantityLength result =
                UnitToUnitConversion.QuantityLength.add(q1, q2, LengthUnit.INCHES);

        assertEquals(
                new UnitToUnitConversion.QuantityLength(24.0, LengthUnit.INCHES),
                result);
    }

    @Test
    void testEqualsForSameValues() {

        UnitToUnitConversion.QuantityLength q1 =
                new UnitToUnitConversion.QuantityLength(1.0, LengthUnit.FEET);

        UnitToUnitConversion.QuantityLength q2 =
                new UnitToUnitConversion.QuantityLength(12.0, LengthUnit.INCHES);

        assertTrue(q1.equals(q2));
    }

    @Test
    void testNotEquals() {

        UnitToUnitConversion.QuantityLength q1 =
                new UnitToUnitConversion.QuantityLength(1.0, LengthUnit.FEET);

        UnitToUnitConversion.QuantityLength q2 =
                new UnitToUnitConversion.QuantityLength(2.0, LengthUnit.FEET);

        assertFalse(q1.equals(q2));
    }

    @Test
    void testInvalidValue() {

        assertThrows(
                IllegalArgumentException.class,
                () -> new UnitToUnitConversion.QuantityLength(Double.NaN, LengthUnit.FEET)
        );
    }

    @Test
    void testNullUnit() {

        assertThrows(
                IllegalArgumentException.class,
                () -> new UnitToUnitConversion.QuantityLength(1.0, null)
        );
    }
}