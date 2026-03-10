package QuantityMeasurement.App;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

public class ArithmeticTest {
	
	// UC13

		    @Test
		    void testAddition_SameUnit() {
		
		    Quantity<LengthUnit> q1 =
		            new Quantity<>(5.0, LengthUnit.FEET);
		
		    Quantity<LengthUnit> q2 =
		            new Quantity<>(5.0, LengthUnit.FEET);
		
		    assertEquals(
		            new Quantity<>(10.0, LengthUnit.FEET),
		            q1.add(q2)
		            );
		     }
		
		     @Test
		     void testAddition_CrossUnit() {
		
		     Quantity<LengthUnit> q1 =
		            new Quantity<>(1.0, LengthUnit.FEET);
		
		     Quantity<LengthUnit> q2 =
		            new Quantity<>(12.0, LengthUnit.INCHES);
		
		     assertEquals(
		            new Quantity<>(2.0, LengthUnit.FEET),
		            q1.add(q2)
		           );
		}
		
		// -------- SUBTRACTION TESTS  --------
		
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
		
		// -------- DIVISION TESTS  --------
		
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
		// -------- VALIDATION TESTS (UC13) --------
		
		@Test
		void testAddition_NullOperand() {
		    Quantity<LengthUnit> q1 =
		            new Quantity<>(10.0, LengthUnit.FEET);
		
		    assertThrows(IllegalArgumentException.class, () -> {
		        q1.add(null);
		    });
		}
		
		@Test
		void testCrossCategory_Addition() {
		
		    Quantity<LengthUnit> length =
		            new Quantity<>(10.0, LengthUnit.FEET);
		
		    Quantity<WeightUnit> weight =
		            new Quantity<>(5.0, WeightUnit.KILOGRAM);
		
		    assertThrows(IllegalArgumentException.class, () -> {
		        length.add((Quantity) weight);
		    });
		}


}