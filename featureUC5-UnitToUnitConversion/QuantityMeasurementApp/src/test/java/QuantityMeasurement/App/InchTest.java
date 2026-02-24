package QuantityMeasurement.App;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import QuantityMeasurement.App.FeetMeasurement.Feet;
import QuantityMeasurement.App.FeetMeasurement.Inches;

public class InchTest {
	
	 @Test
	    public void testInchesEquality_SameValue() {
	        Inches i1 = new Inches(5.0);
	        Inches i2 = new Inches(5.0);
	        assertTrue(i1.equals(i2));
	    }

	    @Test
	    public void testInchesEquality_DifferentValue() {
	        Inches i1 = new Inches(5.0);
	        Inches i2 = new Inches(6.0);
	        assertFalse(i1.equals(i2));
	    }

	    @Test
	    public void testInchesEquality_NullComparison() {
	        Inches i1 = new Inches(5.0);
	        assertFalse(i1.equals(null));
	    }

	    @Test
	    public void testInchesEquality_DifferentClass() {
	        Inches i1 = new Inches(5.0);
	        Feet f1 = new Feet(5.0);
	        assertFalse(i1.equals(f1));
	    }

	    @Test
	    public void testInchesEquality_SameReference() {
	        Inches i1 = new Inches(5.0);
	        assertTrue(i1.equals(i1));
	    }

}
