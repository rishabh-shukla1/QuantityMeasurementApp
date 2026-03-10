package QuantityMeasurement.App;


import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

public class TemperatureUnitTest {

    private static final double EPSILON = 0.01;

    @Test
    void testTemperatureEquality_CelsiusToCelsius() {

        Quantity<TemperatureUnit> t1 =
                new Quantity<>(0.0, TemperatureUnit.CELSIUS);

        Quantity<TemperatureUnit> t2 =
                new Quantity<>(0.0, TemperatureUnit.CELSIUS);

        assertTrue(t1.equals(t2));
    }

    @Test
    void testTemperatureEquality_CelsiusToFahrenheit() {

        Quantity<TemperatureUnit> t1 =
                new Quantity<>(0.0, TemperatureUnit.CELSIUS);

        Quantity<TemperatureUnit> t2 =
                new Quantity<>(32.0, TemperatureUnit.FAHRENHEIT);

        assertTrue(t1.equals(t2));
    }

    @Test
    void testTemperatureEquality_CelsiusToKelvin() {

        Quantity<TemperatureUnit> t1 =
                new Quantity<>(0.0, TemperatureUnit.CELSIUS);

        Quantity<TemperatureUnit> t2 =
                new Quantity<>(273.15, TemperatureUnit.KELVIN);

        assertTrue(t1.equals(t2));
    }

    @Test
    void testTemperatureEquality_Negative40() {

        Quantity<TemperatureUnit> t1 =
                new Quantity<>(-40.0, TemperatureUnit.CELSIUS);

        Quantity<TemperatureUnit> t2 =
                new Quantity<>(-40.0, TemperatureUnit.FAHRENHEIT);

        assertTrue(t1.equals(t2));
    }

   
    // Conversion Tests
    

    @Test
    void testConversion_CelsiusToFahrenheit() {

        Quantity<TemperatureUnit> t =
                new Quantity<>(100.0, TemperatureUnit.CELSIUS);

        Quantity<TemperatureUnit> result =
                t.convertTo(TemperatureUnit.FAHRENHEIT);

        assertEquals(
                new Quantity<>(212.0, TemperatureUnit.FAHRENHEIT),
                result
        );
    }

    @Test
    void testConversion_FahrenheitToCelsius() {

        Quantity<TemperatureUnit> t =
                new Quantity<>(32.0, TemperatureUnit.FAHRENHEIT);

        Quantity<TemperatureUnit> result =
                t.convertTo(TemperatureUnit.CELSIUS);

        assertEquals(
                new Quantity<>(0.0, TemperatureUnit.CELSIUS),
                result
        );
    }

    @Test
    void testConversion_CelsiusToKelvin() {

        Quantity<TemperatureUnit> t =
                new Quantity<>(0.0, TemperatureUnit.CELSIUS);

        Quantity<TemperatureUnit> result =
                t.convertTo(TemperatureUnit.KELVIN);

        assertEquals(
                new Quantity<>(273.15, TemperatureUnit.KELVIN),
                result
        );
    }

    @Test
    void testConversion_KelvinToCelsius() {

        Quantity<TemperatureUnit> t =
                new Quantity<>(273.15, TemperatureUnit.KELVIN);

        Quantity<TemperatureUnit> result =
                t.convertTo(TemperatureUnit.CELSIUS);

        assertEquals(
                new Quantity<>(0.0, TemperatureUnit.CELSIUS),
                result
        );
    }

   
    // Unsupported Arithmetic
    

    @Test
    void testTemperatureUnsupported_Add() {

        Quantity<TemperatureUnit> t1 =
                new Quantity<>(100.0, TemperatureUnit.CELSIUS);

        Quantity<TemperatureUnit> t2 =
                new Quantity<>(50.0, TemperatureUnit.CELSIUS);

        assertThrows(
                UnsupportedOperationException.class,
                () -> t1.add(t2)
        );
    }

    @Test
    void testTemperatureUnsupported_Subtract() {

        Quantity<TemperatureUnit> t1 =
                new Quantity<>(100.0, TemperatureUnit.CELSIUS);

        Quantity<TemperatureUnit> t2 =
                new Quantity<>(50.0, TemperatureUnit.CELSIUS);

        assertThrows(
                UnsupportedOperationException.class,
                () -> t1.subtract(t2)
        );
    }

    @Test
    void testTemperatureUnsupported_Divide() {

        Quantity<TemperatureUnit> t1 =
                new Quantity<>(100.0, TemperatureUnit.CELSIUS);

        Quantity<TemperatureUnit> t2 =
                new Quantity<>(50.0, TemperatureUnit.CELSIUS);

        assertThrows(
                UnsupportedOperationException.class,
                () -> t1.divide(t2)
        );
    }

    // =========================
    // Cross Category Tests
    // =========================

    @Test
    void testTemperatureVsLengthComparison() {

        Quantity<TemperatureUnit> t =
                new Quantity<>(100.0, TemperatureUnit.CELSIUS);

        Quantity<LengthUnit> l =
                new Quantity<>(100.0, LengthUnit.FEET);

        assertFalse(t.equals(l));
    }

    @Test
    void testTemperatureVsWeightComparison() {

        Quantity<TemperatureUnit> t =
                new Quantity<>(50.0, TemperatureUnit.CELSIUS);

        Quantity<WeightUnit> w =
                new Quantity<>(50.0, WeightUnit.KILOGRAM);

        assertFalse(t.equals(w));
    }

    @Test
    void testTemperatureVsVolumeComparison() {

        Quantity<TemperatureUnit> t =
                new Quantity<>(25.0, TemperatureUnit.CELSIUS);

        Quantity<VolumeUnit> v =
                new Quantity<>(25.0, VolumeUnit.LITRE);

        assertFalse(t.equals(v));
    }

   
    // Edge Cases
   

    @Test
    void testAbsoluteZero() {

        Quantity<TemperatureUnit> t1 =
                new Quantity<>(-273.15, TemperatureUnit.CELSIUS);

        Quantity<TemperatureUnit> t2 =
                new Quantity<>(0.0, TemperatureUnit.KELVIN);

        assertTrue(t1.equals(t2));
    }

    @Test
    void testSameReference() {

        Quantity<TemperatureUnit> t =
                new Quantity<>(20.0, TemperatureUnit.CELSIUS);

        assertTrue(t.equals(t));
    }

    @Test
    void testNullComparison() {

        Quantity<TemperatureUnit> t =
                new Quantity<>(20.0, TemperatureUnit.CELSIUS);

        assertFalse(t.equals(null));
    }

}