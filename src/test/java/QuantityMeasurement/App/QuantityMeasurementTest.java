package QuantityMeasurement.App;

import org.junit.jupiter.api.Test;

import dto.QuantityDTO;
import repository.QuantityMeasurementCacheRepository;
import service.IQuantityMeasurementService;
import service.QuantityMeasurementServiceImpl;
import units.*;
import static org.junit.jupiter.api.Assertions.*;

public class QuantityMeasurementTest {

    IQuantityMeasurementService service =
            new QuantityMeasurementServiceImpl(new QuantityMeasurementCacheRepository());

    @Test
    void testLengthEquality_FeetAndInches() {

        QuantityDTO<LengthUnit> q1 = new QuantityDTO<>(1.0, LengthUnit.FEET);
        QuantityDTO<LengthUnit> q2 = new QuantityDTO<>(12.0, LengthUnit.INCHES);

        QuantityDTO<LengthUnit> result = service.compare(q1, q2);

        assertEquals(1.0, result.getValue());
    }

    @Test
    void testWeightEquality_KilogramAndGram() {

        QuantityDTO<WeightUnit> q1 = new QuantityDTO<>(1.0, WeightUnit.KILOGRAM);
        QuantityDTO<WeightUnit> q2 = new QuantityDTO<>(1000.0, WeightUnit.GRAM);

        QuantityDTO<WeightUnit> result = service.compare(q1, q2);

        assertEquals(1.0, result.getValue());
    }

    @Test
    void testConversion_Length() {

        QuantityDTO<LengthUnit> q1 = new QuantityDTO<>(2.0, LengthUnit.FEET);

        QuantityDTO<LengthUnit> result =
                service.convert(q1, LengthUnit.INCHES);

        assertEquals(24.0, result.getValue(), 0.0001);
    }

    @Test
    void testConversion_Volume() {

        QuantityDTO<VolumeUnit> q1 =
                new QuantityDTO<>(1.0, VolumeUnit.LITRE);

        QuantityDTO<VolumeUnit> result =
                service.convert(q1, VolumeUnit.MILLILITRE);

        assertEquals(1000.0, result.getValue(), 0.0001);
    }

    @Test
    void testAddition_SameUnit_Length() {

        QuantityDTO<LengthUnit> q1 = new QuantityDTO<>(3.0, LengthUnit.FEET);
        QuantityDTO<LengthUnit> q2 = new QuantityDTO<>(2.0, LengthUnit.FEET);

        QuantityDTO<LengthUnit> result = service.add(q1, q2);

        assertEquals(5.0, result.getValue(), 0.0001);
    }

    @Test
    void testAddition_CrossUnit_Length() {

        QuantityDTO<LengthUnit> q1 = new QuantityDTO<>(1.0, LengthUnit.FEET);
        QuantityDTO<LengthUnit> q2 = new QuantityDTO<>(12.0, LengthUnit.INCHES);

        QuantityDTO<LengthUnit> result = service.add(q1, q2);

        assertEquals(2.0, result.getValue(), 0.0001);
    }

    @Test
    void testSubtraction_Length() {

        QuantityDTO<LengthUnit> q1 = new QuantityDTO<>(3.0, LengthUnit.FEET);
        QuantityDTO<LengthUnit> q2 = new QuantityDTO<>(12.0, LengthUnit.INCHES);

        QuantityDTO<LengthUnit> result = service.subtract(q1, q2);

        assertEquals(2.0, result.getValue(), 0.0001);
    }

    @Test
    void testDivision_Length() {

        QuantityDTO<LengthUnit> q1 = new QuantityDTO<>(10.0, LengthUnit.FEET);
        QuantityDTO<LengthUnit> q2 = new QuantityDTO<>(2.0, LengthUnit.FEET);

        QuantityDTO<LengthUnit> result = service.divide(q1, q2);

        assertEquals(5.0, result.getValue(), 0.0001);
    }

    @Test
    void testDivision_ByZero() {

        QuantityDTO<LengthUnit> q1 = new QuantityDTO<>(10.0, LengthUnit.FEET);
        QuantityDTO<LengthUnit> q2 = new QuantityDTO<>(0.0, LengthUnit.FEET);

        assertThrows(ArithmeticException.class, () ->
                service.divide(q1, q2));
    }

    @Test
    void testTemperatureConversion() {

        QuantityDTO<TemperatureUnit> temp =
                new QuantityDTO<>(0.0, TemperatureUnit.CELSIUS);

        QuantityDTO<TemperatureUnit> result =
                service.convert(temp, TemperatureUnit.FAHRENHEIT);

        assertEquals(32.0, result.getValue(), 0.0001);
    }

    @Test
    void testTemperatureUnsupportedAdd() {

        QuantityDTO<TemperatureUnit> t1 =
                new QuantityDTO<>(100.0, TemperatureUnit.CELSIUS);
        QuantityDTO<TemperatureUnit> t2 =
                new QuantityDTO<>(10.0, TemperatureUnit.CELSIUS);

        assertThrows(UnsupportedOperationException.class,
                () -> service.add(t1, t2));
    }

}
