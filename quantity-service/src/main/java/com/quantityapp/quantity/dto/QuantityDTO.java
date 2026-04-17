package com.quantityapp.quantity.dto;

import jakarta.validation.constraints.NotBlank;

public class QuantityDTO {

    private double value;

    @NotBlank
    private String unit;

    @NotBlank
    private String type;

    public QuantityDTO() {}

    public QuantityDTO(double value, String unit, String type) {
        this.value = value;
        this.unit = unit;
        this.type = type;
    }

    public double getValue() { return value; }
    public String getUnit() { return unit; }
    public String getType() { return type; }

    public void setValue(double value) { this.value = value; }
    public void setUnit(String unit) { this.unit = unit; }
    public void setType(String type) { this.type = type; }
}
