package QuantityMeasurement.App;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import QuantityMeasurement.App.GenericQuantityClassForDRYPrinciple.QuantityLength;

public class GenericQuantityClassForDRYPrincipleTest {

    @Test
    void testEquality_FeetToFeet_SameValue() {
        QuantityLength l1 = new QuantityLength(1.0, QuantityLength.LengthUnit.FEET);
        QuantityLength l2 = new QuantityLength(1.0, QuantityLength.LengthUnit.FEET);
        assertTrue(l1.equals(l2));
    }

    @Test
    void testEquality_InchToInch_SameValue() {
        QuantityLength l1 = new QuantityLength(1.0, QuantityLength.LengthUnit.INCHES);
        QuantityLength l2 = new QuantityLength(1.0, QuantityLength.LengthUnit.INCHES);
        assertTrue(l1.equals(l2));
    }

    @Test
    void testEquality_FeetToInch_EquivalentValue() {
        QuantityLength l1 = new QuantityLength(1.0, QuantityLength.LengthUnit.FEET);
        QuantityLength l2 = new QuantityLength(12.0, QuantityLength.LengthUnit.INCHES);
        assertTrue(l1.equals(l2));
    }

    @Test
    void testEquality_InchToFeet_EquivalentValue() {
        QuantityLength l1 = new QuantityLength(12.0, QuantityLength.LengthUnit.INCHES);
        QuantityLength l2 = new QuantityLength(1.0, QuantityLength.LengthUnit.FEET);
        assertTrue(l1.equals(l2));
    }

    @Test
    void testEquality_FeetToFeet_DifferentValue() {
        QuantityLength l1 = new QuantityLength(1.0, QuantityLength.LengthUnit.FEET);
        QuantityLength l2 = new QuantityLength(2.0, QuantityLength.LengthUnit.FEET);
        assertFalse(l1.equals(l2));
    }

    @Test
    void testEquality_InchToInch_DifferentValue() {
        QuantityLength l1 = new QuantityLength(1.0, QuantityLength.LengthUnit.INCHES);
        QuantityLength l2 = new QuantityLength(2.0, QuantityLength.LengthUnit.INCHES);
        assertFalse(l1.equals(l2));
    }

    @Test
    void testEquality_SameReference() {
        QuantityLength l1 = new QuantityLength(1.0, QuantityLength.LengthUnit.FEET);
        assertTrue(l1.equals(l1));
    }

    @Test
    void testEquality_NullComparison() {
        QuantityLength l1 = new QuantityLength(1.0, QuantityLength.LengthUnit.FEET);
        assertFalse(l1.equals(null));
    }

    @Test
    void testEquality_InvalidUnit() {
        assertThrows(IllegalArgumentException.class,
                () -> new QuantityLength(1.0, null));
    }

    @Test
    void testEquality_NonNumericInput() {
        assertThrows(IllegalArgumentException.class,
                () -> new QuantityLength(Double.NaN, QuantityLength.LengthUnit.FEET));
    }
}