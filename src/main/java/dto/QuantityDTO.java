package dto;

import units.IMeasurable;

public class QuantityDTO<U extends IMeasurable> {
    private double value;
    private U unit;

    public QuantityDTO(double value, U unit) {
        this.value = value;
        this.unit = unit;
    }

    public double getValue() {
    	return value; 
    }
    public void setValue(double value) {
    	this.value = value; 
    }

    public U getUnit() {
    	return unit; 
    }
    public void setUnit(U unit) {
    	this.unit = unit; 
    }
}
