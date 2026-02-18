package QuantityMeasurement.App;

import static org.junit.jupiter.api.Assertions.*;


import org.junit.jupiter.api.Test;

import QuantityMeasurement.App.FeetMeasurementUC1.Feet;

/**
 * Unit test for simple App.
 */
public class AppTest {

    /**
     * Rigorous Test :-)
     */
	 @Test
	    public void testEquality_SameValue() {

	        Feet value1 = new Feet(1.0);
	        Feet value2 = new Feet(1.0);

	        assertTrue(value1.equals(value2), "1.0 ft should equal 1.0 ft");
	    }

	    // Test: Different Value
	    @Test
	    public void testEquality_DifferentValue() {

	        Feet value1 = new Feet(1.0);
	        Feet value2 = new Feet(2.0);

	        assertFalse(value1.equals(value2), "1.0 ft should not equal 2.0 ft");
	    }

	    // Test: Null Comparison
	    @Test
	    public void testEquality_NullComparison() {

	        Feet value1 = new Feet(1.0);

	        assertFalse(value1.equals(null), "Feet should not be equal to null");
	    }

	    // Test: Different Class Comparison
	    @Test
	    public void testEquality_DifferentClass() {

	        Feet value1 = new Feet(1.0);
	        String other = "1.0";

	        assertFalse(value1.equals(other), "Feet should not equal different object type");
	    }

	    // Test: Same Reference (Reflexive)
	    @Test
	    public void testEquality_SameReference() {

	        Feet value1 = new Feet(1.0);

	        assertTrue(value1.equals(value1), "Object should be equal to itself");
	    }
}
