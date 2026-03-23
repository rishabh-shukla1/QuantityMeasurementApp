package com.app.quantitymeasurement.dto;

import jakarta.validation.Valid;
import jakarta.validation.constraints.*;

public class QuantityInputDTO {

    @Valid
    @NotNull
    private QuantityDTO thisQuantityDTO;

    @Valid
    @NotNull
    private QuantityDTO thatQuantityDTO;

    public QuantityDTO getThisQuantityDTO() { 
    	return thisQuantityDTO; 
    }
    public void setThisQuantityDTO(QuantityDTO thisQuantityDTO) {
    	this.thisQuantityDTO = thisQuantityDTO; 
    }

    public QuantityDTO getThatQuantityDTO() {
    	return thatQuantityDTO; 
    }
    public void setThatQuantityDTO(QuantityDTO thatQuantityDTO) {
    	this.thatQuantityDTO = thatQuantityDTO; 
    }
}
