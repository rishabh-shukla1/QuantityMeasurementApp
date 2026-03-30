package com.app.quantitymeasurement;

import static org.hamcrest.CoreMatchers.any;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import com.app.quantitymeasurement.dto.QuantityDTO;
import com.app.quantitymeasurement.dto.QuantityInputDTO;
import com.app.quantitymeasurement.entity.QuantityMeasurementEntity;
import com.app.quantitymeasurement.repository.QuantityMeasurementRepository;
import com.app.quantitymeasurement.service.QuantityMeasurementServiceImpl;

@SpringBootTest
class QuantityMeasurementAppApplicationTests {

	 private QuantityMeasurementServiceImpl service;
	    private QuantityMeasurementRepository repository;

	    @BeforeEach
	    void setup() {
	        repository = mock(QuantityMeasurementRepository.class);
	        service = new QuantityMeasurementServiceImpl();

	        // manually inject mock repository
	        service.setRepository(repository);

	        // save method ko stub kar do
	        when(repository.save(any())).thenAnswer(i -> i.getArgument(0));
	    }
	    private QuantityInputDTO getLengthInput(double v1, String u1, double v2, String u2) {

	        QuantityDTO q1 = new QuantityDTO();
	        q1.setValue(v1);
	        q1.setUnit(u1);
	        q1.setMeasurementType("LengthUnit");

	        QuantityDTO q2 = new QuantityDTO();
	        q2.setValue(v2);
	        q2.setUnit(u2);
	        q2.setMeasurementType("LengthUnit");

	        QuantityInputDTO input = new QuantityInputDTO();
	        input.setThisQuantityDTO(q1);
	        input.setThatQuantityDTO(q2);

	        return input;
	    }

	    // add
	    @Test
	    void testAdd() {
	        QuantityMeasurementEntity result = service.add(
	                getLengthInput(1, "FEET", 12, "INCHES"));

	        assertFalse(result.isError());
	        assertEquals(2.0, result.getResultValue());
	    }

	    // subtract
	    @Test
	    void testSubtract() {
	        QuantityMeasurementEntity result = service.subtract(
	                getLengthInput(2, "FEET", 12, "INCHES"));

	        assertEquals(1.0, result.getResultValue());
	    }

	    // multiply
	    @Test
	    void testMultiply() {
	        QuantityMeasurementEntity result = service.multiply(
	                getLengthInput(2, "FEET", 2, "FEET"));

	        assertFalse(result.isError());
	    }

	    // divide
	    @Test
	    void testDivide() {
	        QuantityMeasurementEntity result = service.divide(
	                getLengthInput(24, "INCHES", 12, "INCHES"));

	        assertEquals(2.0, result.getResultValue());
	    }

	    // divide by zero
	    @Test
	    void testDivideByZero() {
	        QuantityMeasurementEntity result = service.divide(
	                getLengthInput(24, "INCHES", 0, "INCHES"));

	        assertTrue(result.isError());
	    }

	    // compare
	    @Test
	    void testCompareTrue() {
	        QuantityMeasurementEntity result = service.compare(
	                getLengthInput(1, "FEET", 12, "INCHES"));

	        assertEquals("true", result.getResultString());
	    }

	    // compare
	    @Test
	    void testConvert() {

	        QuantityDTO q1 = new QuantityDTO();
	        q1.setValue(1.0);
	        q1.setUnit("FEET");
	        q1.setMeasurementType("LengthUnit");

	        QuantityDTO q2 = new QuantityDTO();
	        q2.setValue(0.0);
	        q2.setUnit("INCHES");
	        q2.setMeasurementType("LengthUnit");

	        QuantityInputDTO input = new QuantityInputDTO();
	        input.setThisQuantityDTO(q1);
	        input.setThatQuantityDTO(q2);

	        QuantityMeasurementEntity result = service.convert(input);

	        assertEquals(12.0, result.getResultValue());
	    }

	    // test errors
	    @Test
	    void testDifferentTypeError() {

	        QuantityDTO q1 = new QuantityDTO();
	        q1.setValue(1.0);
	        q1.setUnit("FEET");
	        q1.setMeasurementType("LengthUnit");

	        QuantityDTO q2 = new QuantityDTO();
	        q2.setValue(1.0);
	        q2.setUnit("KILOGRAM");
	        q2.setMeasurementType("WeightUnit");

	        QuantityInputDTO input = new QuantityInputDTO();
	        input.setThisQuantityDTO(q1);
	        input.setThatQuantityDTO(q2);

	        QuantityMeasurementEntity result = service.add(input);

	        assertTrue(result.isError());
	    }

}
