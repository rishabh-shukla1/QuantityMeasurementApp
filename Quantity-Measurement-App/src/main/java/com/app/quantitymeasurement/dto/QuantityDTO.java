package com.app.quantitymeasurement.dto;

import jakarta.validation.constraints.*;

public class QuantityDTO {

    @NotNull
    private Double value;

    @NotBlank
    private String unit;

    @NotBlank
    private String measurementType;

    public Double getValue() { 
    	return value;
    }
    public void setValue(Double value) { 
    	this.value = value; 
    }

    public String getUnit() { 
    	return unit; 
    }
    public void setUnit(String unit) { 
    	this.unit = unit; 
    }

    public String getMeasurementType() { 
    	return measurementType; 
    }
    public void setMeasurementType(String measurementType) { 
    	this.measurementType = measurementType; 
    }
}
