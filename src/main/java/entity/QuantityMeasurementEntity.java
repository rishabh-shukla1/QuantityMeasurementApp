package entity;

import java.io.Serializable;
import java.time.LocalDateTime;

public class QuantityMeasurementEntity implements Serializable {
    private static final long serialVersionUID = 1L;

    private Long id;

    private double operand1;
    private double operand2;
    private String unit1;
    private String unit2;
    private String operation;
    private double result;

    private String measurementType;
    private String resultUnit;
    private LocalDateTime createdAt;

    private boolean error;
    private String errorMessage;

    public QuantityMeasurementEntity() {
    }

    public QuantityMeasurementEntity(double operand1, double operand2,
                                     String unit1, String unit2,
                                     String operation, double result) {
        this.operand1 = operand1;
        this.operand2 = operand2;
        this.unit1 = unit1;
        this.unit2 = unit2;
        this.operation = operation;
        this.result = result;
        this.error = false;
        this.createdAt = LocalDateTime.now();
    }

    public QuantityMeasurementEntity(double operand1, double operand2,
                                     String unit1, String unit2,
                                     String operation, double result,
                                     String measurementType, String resultUnit,
                                     LocalDateTime createdAt) {
        this.operand1 = operand1;
        this.operand2 = operand2;
        this.unit1 = unit1;
        this.unit2 = unit2;
        this.operation = operation;
        this.result = result;
        this.measurementType = measurementType;
        this.resultUnit = resultUnit;
        this.createdAt = createdAt;
        this.error = false;
    }

    public QuantityMeasurementEntity(String errorMessage) {
        this.errorMessage = errorMessage;
        this.error = true;
        this.createdAt = LocalDateTime.now();
    }

    public Long getId() {
        return id;
    }

    public double getOperand1() {
        return operand1;
    }

    public double getOperand2() {
        return operand2;
    }

    public String getUnit1() {
        return unit1;
    }

    public String getUnit2() {
        return unit2;
    }

    public String getOperation() {
        return operation;
    }

    public double getResult() {
        return result;
    }

    public String getMeasurementType() {
        return measurementType;
    }

    public String getResultUnit() {
        return resultUnit;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public boolean hasError() {
        return error;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setOperand1(double operand1) {
        this.operand1 = operand1;
    }

    public void setOperand2(double operand2) {
        this.operand2 = operand2;
    }

    public void setUnit1(String unit1) {
        this.unit1 = unit1;
    }

    public void setUnit2(String unit2) {
        this.unit2 = unit2;
    }

    public void setOperation(String operation) {
        this.operation = operation;
    }

    public void setResult(double result) {
        this.result = result;
    }

    public void setMeasurementType(String measurementType) {
        this.measurementType = measurementType;
    }

    public void setResultUnit(String resultUnit) {
        this.resultUnit = resultUnit;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public void setError(boolean error) {
        this.error = error;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    @Override
    public String toString() {
        if (error) {
            return "Error: " + errorMessage;
        }

        return "QuantityMeasurementEntity{" +
                "id=" + id +
                ", operand1=" + operand1 +
                ", operand2=" + operand2 +
                ", unit1='" + unit1 + '\'' +
                ", unit2='" + unit2 + '\'' +
                ", operation='" + operation + '\'' +
                ", result=" + result +
                ", measurementType='" + measurementType + '\'' +
                ", resultUnit='" + resultUnit + '\'' +
                ", createdAt=" + createdAt +
                '}';
    }
}