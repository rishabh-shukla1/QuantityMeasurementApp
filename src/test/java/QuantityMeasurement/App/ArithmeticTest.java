package QuantityMeasurement.App;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
public class ArithmeticTest {
	
	 @Test
	    void testSubtraction_SameUnit() {

	        Quantity<LengthUnit> q1 =
	                new Quantity<>(10.0, LengthUnit.FEET);

	        Quantity<LengthUnit> q2 =
	                new Quantity<>(5.0, LengthUnit.FEET);

	        assertEquals(
	                new Quantity<>(5.0, LengthUnit.FEET),
	                q1.subtract(q2)
	        );
	    }

	 
	    @Test
	    void testSubtraction_CrossUnit() {

	        Quantity<LengthUnit> q1 =
	                new Quantity<>(10.0, LengthUnit.FEET);

	        Quantity<LengthUnit> q2 =
	                new Quantity<>(6.0, LengthUnit.INCHES);

	        assertEquals(
	                new Quantity<>(9.5, LengthUnit.FEET),
	                q1.subtract(q2)
	        );
	    }

	    
	    @Test
	    void testDivision_SameUnit() {

	        Quantity<LengthUnit> q1 =
	                new Quantity<>(10.0, LengthUnit.FEET);

	        Quantity<LengthUnit> q2 =
	                new Quantity<>(2.0, LengthUnit.FEET);

	        assertEquals(5.0, q1.divide(q2));
	    }

	    
	    @Test
	    void testDivision_CrossUnit() {

	        Quantity<LengthUnit> q1 =
	                new Quantity<>(24.0, LengthUnit.INCHES);

	        Quantity<LengthUnit> q2 =
	                new Quantity<>(2.0, LengthUnit.FEET);

	        assertEquals(1.0, q1.divide(q2));
	    }

	    
	    @Test
	    void testDivision_ByZero() {

	        Quantity<LengthUnit> q1 =
	                new Quantity<>(10.0, LengthUnit.FEET);

	        Quantity<LengthUnit> q2 =
	                new Quantity<>(0.0, LengthUnit.FEET);

	        assertThrows(ArithmeticException.class, () -> {
	            q1.divide(q2);
	        });
	    }

}
