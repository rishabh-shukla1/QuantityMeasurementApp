package QuantityMeasurement.App;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class QuantityTest {

    @Test
    void testLengthEquality() {

        Quantity<LengthUnit> q1 = new Quantity<>(1.0, LengthUnit.FEET);
        
        Quantity<LengthUnit> q2 = new Quantity<>(12.0, LengthUnit.INCHES);

        assertTrue(q1.equals(q2));
    }

    @Test
    void testWeightEquality() {

        Quantity<WeightUnit> w1 = new Quantity<>(1.0, WeightUnit.KILOGRAM);
        
        Quantity<WeightUnit> w2 = new Quantity<>(1000.0, WeightUnit.GRAM);

        assertTrue(w1.equals(w2));
    }

    @Test
    void testLengthConversion() {

        Quantity<LengthUnit> q = new Quantity<>(1.0, LengthUnit.FEET);

        Quantity<LengthUnit> result = q.convertTo(LengthUnit.INCHES);

        assertEquals(new Quantity<>(12.0, LengthUnit.INCHES), result);
    }

    @Test
    void testWeightConversion() {

        Quantity<WeightUnit> w = new Quantity<>(1.0, WeightUnit.KILOGRAM);

        Quantity<WeightUnit> result = w.convertTo(WeightUnit.GRAM);

        assertEquals(new Quantity<>(1000.0, WeightUnit.GRAM), result);
    }

    @Test
    void testAddition_Length() {

        Quantity<LengthUnit> q1 = new Quantity<>(1.0, LengthUnit.FEET);
        
        Quantity<LengthUnit> q2 = new Quantity<>(12.0, LengthUnit.INCHES);

        Quantity<LengthUnit> result = q1.add(q2, LengthUnit.FEET);

        assertEquals(new Quantity<>(2.0, LengthUnit.FEET), result);
    }

    @Test
    void testAddition_Weight() {

        Quantity<WeightUnit> w1 = new Quantity<>(1.0, WeightUnit.KILOGRAM);
        
        Quantity<WeightUnit> w2 = new Quantity<>(1000.0, WeightUnit.GRAM);

        Quantity<WeightUnit> result = w1.add(w2, WeightUnit.KILOGRAM);

        assertEquals(new Quantity<>(2.0, WeightUnit.KILOGRAM), result);
    }

    @Test
    void testCrossCategoryComparison() {

        Quantity<LengthUnit> q = new Quantity<>(1.0, LengthUnit.FEET);
        
        Quantity<WeightUnit> w = new Quantity<>(1.0, WeightUnit.KILOGRAM);

        assertFalse(q.equals(w));
    }
    

    @Test
    void testEquality_LitreToLitre_SameValue() {

        Quantity<VolumeUnit> v1 =
                new Quantity<>(1.0, VolumeUnit.LITRE);

        Quantity<VolumeUnit> v2 =
                new Quantity<>(1.0, VolumeUnit.LITRE);

        assertTrue(v1.equals(v2));
    }

    @Test
    void testEquality_LitreToMillilitre() {

        Quantity<VolumeUnit> v1 =
                new Quantity<>(1.0, VolumeUnit.LITRE);

        Quantity<VolumeUnit> v2 =
                new Quantity<>(1000.0, VolumeUnit.MILLILITRE);

        assertTrue(v1.equals(v2));
    }

    @Test
    void testEquality_LitreToGallon() {

        Quantity<VolumeUnit> v1 =
                new Quantity<>(3.78541, VolumeUnit.LITRE);

        Quantity<VolumeUnit> v2 =
                new Quantity<>(1.0, VolumeUnit.GALLON);

        assertTrue(v1.equals(v2));
    }

    @Test
    void testConversion_LitreToMillilitre() {

        Quantity<VolumeUnit> v1 =
                new Quantity<>(1.0, VolumeUnit.LITRE);

        Quantity<VolumeUnit> result =
                v1.convertTo(VolumeUnit.MILLILITRE);

        assertEquals(
                new Quantity<>(1000.0, VolumeUnit.MILLILITRE),
                result
        );
    }

    @Test
    void testConversion_GallonToLitre() {

        Quantity<VolumeUnit> v1 =
                new Quantity<>(1.0, VolumeUnit.GALLON);

        Quantity<VolumeUnit> result =
                v1.convertTo(VolumeUnit.LITRE);

        assertEquals(
                new Quantity<>(3.78541, VolumeUnit.LITRE),
                result
        );
    }

    @Test
    void testAddition_SameUnit() {

        Quantity<VolumeUnit> v1 =
                new Quantity<>(1.0, VolumeUnit.LITRE);

        Quantity<VolumeUnit> v2 =
                new Quantity<>(2.0, VolumeUnit.LITRE);

        Quantity<VolumeUnit> result = v1.add(v2);

        assertEquals(
                new Quantity<>(3.0, VolumeUnit.LITRE),
                result
        );
    }

    @Test
    void testAddition_CrossUnit() {

        Quantity<VolumeUnit> v1 =
                new Quantity<>(1.0, VolumeUnit.LITRE);

        Quantity<VolumeUnit> v2 =
                new Quantity<>(1000.0, VolumeUnit.MILLILITRE);

        Quantity<VolumeUnit> result = v1.add(v2);

        assertEquals(
                new Quantity<>(2.0, VolumeUnit.LITRE),
                result
        );
    }

    @Test
    void testAddition_WithTargetUnit() {

        Quantity<VolumeUnit> v1 =
                new Quantity<>(1.0, VolumeUnit.LITRE);

        Quantity<VolumeUnit> v2 =
                new Quantity<>(1000.0, VolumeUnit.MILLILITRE);

        Quantity<VolumeUnit> result =
                v1.add(v2, VolumeUnit.MILLILITRE);

        assertEquals(
                new Quantity<>(2000.0, VolumeUnit.MILLILITRE),
                result
        );
    }

    @Test
    void testInvalidValue() {

        assertThrows(
                IllegalArgumentException.class,
                () -> new Quantity<>(Double.NaN, VolumeUnit.LITRE)
        );
    }

    @Test
    void testNullUnit() {

        assertThrows(
                IllegalArgumentException.class,
                () -> new Quantity<>(1.0, null)
        );
    }
}
