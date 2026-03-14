package entity;

import java.io.Serializable;

public class QuantityMeasurementEntity implements Serializable {
    private static final long serialVersionUID = 1L;

    private double operand1;
    private double operand2;
    private String unit1;
    private String unit2;
    private String operation;
    private double result;
    private boolean error;
    private String errorMessage;

    public QuantityMeasurementEntity(double operand1, double operand2, String unit1, String unit2, String operation, double result) {
        this.operand1 = operand1;
        this.operand2 = operand2;
        this.unit1 = unit1;
        this.unit2 = unit2;
        this.operation = operation;
        this.result = result;
        this.error = false;
    }

    public QuantityMeasurementEntity(String errorMessage) {
        this.errorMessage = errorMessage;
        this.error = true;
    }

    public boolean hasError() { 
    	return error; 
    }
    
    public String getErrorMessage() { 
    	return errorMessage; 
    }
    
    public double getResult() { 
    	return result; 
    }

    @Override
    public String toString() {
        if(error) {
        	return "Error: " + errorMessage;
        }
        return operand1 + " " + unit1 + " " + operation + " " + operand2 + " " + unit2 + " = " + result;
    }
}
