package QuantityMeasurement.App;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
public class QuantityWeightTest {

    private static final double EPSILON = 1e-6;

    @Test
    void testEquality_KilogramToKilogram_SameValue() {

        QuantityWeight w1 = new QuantityWeight(1.0, WeightUnit.KILOGRAM);
        QuantityWeight w2 = new QuantityWeight(1.0, WeightUnit.KILOGRAM);

        assertTrue(w1.equals(w2));
    }

    

	@Test
    void testEquality_KilogramToGram() {

        QuantityWeight w1 = new QuantityWeight(1.0, WeightUnit.KILOGRAM);
        QuantityWeight w2 = new QuantityWeight(1000.0, WeightUnit.GRAM);

        assertTrue(w1.equals(w2));
    }

    @Test
    void testEquality_GramToKilogram() {

        QuantityWeight w1 = new QuantityWeight(1000.0, WeightUnit.GRAM);
        QuantityWeight w2 = new QuantityWeight(1.0, WeightUnit.KILOGRAM);

        assertTrue(w1.equals(w2));
    }

    @Test
    void testEquality_KilogramToPound() {

        QuantityWeight w1 = new QuantityWeight(1.0, WeightUnit.KILOGRAM);
        QuantityWeight w2 = new QuantityWeight(2.20462, WeightUnit.POUND);

        assertTrue(w1.equals(w2));
    }

    @Test
    void testEquality_NullComparison() {

        QuantityWeight w = new QuantityWeight(1.0, WeightUnit.KILOGRAM);

        assertFalse(w.equals(null));
    }

    @Test
    void testConversion_KilogramToGram() {

        QuantityWeight w = new QuantityWeight(1.0, WeightUnit.KILOGRAM);

        QuantityWeight result = w.convertTo(WeightUnit.GRAM);

        assertEquals(
                new QuantityWeight(1000.0, WeightUnit.GRAM),
                result);
    }

    @Test
    void testConversion_GramToKilogram() {

        QuantityWeight w = new QuantityWeight(1000.0, WeightUnit.GRAM);

        QuantityWeight result = w.convertTo(WeightUnit.KILOGRAM);

        assertEquals(
                new QuantityWeight(1.0, WeightUnit.KILOGRAM),
                result);
    }

    @Test
    void testConversion_PoundToKilogram() {

        QuantityWeight w = new QuantityWeight(2.20462, WeightUnit.POUND);

        QuantityWeight result = w.convertTo(WeightUnit.KILOGRAM);

        assertEquals(
                new QuantityWeight(1.0, WeightUnit.KILOGRAM),
                result);
    }

    @Test
    void testInvalidValue_NaN() {

        assertThrows(IllegalArgumentException.class, () -> {
            new QuantityWeight(Double.NaN, WeightUnit.KILOGRAM);
        });
    }

    @Test
    void testNullUnit() {

        assertThrows(IllegalArgumentException.class, () -> {
            new QuantityWeight(5.0, null);
        });
    }
}