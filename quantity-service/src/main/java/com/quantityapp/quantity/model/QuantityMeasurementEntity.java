package com.quantityapp.quantity.model;

import jakarta.persistence.*;

@Entity
@Table(name = "quantity_measurements")
public class QuantityMeasurementEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String operation;
    private String result;
    private String username; // which user did this calculation

    public QuantityMeasurementEntity() {}

    public QuantityMeasurementEntity(Long id, String operation, String result, String username) {
        this.id = id;
        this.operation = operation;
        this.result = result;
        this.username = username;
    }

    public Long getId() { return id; }
    public String getOperation() { return operation; }
    public String getResult() { return result; }
    public String getUsername() { return username; }

    public void setOperation(String operation) { this.operation = operation; }
    public void setResult(String result) { this.result = result; }
    public void setUsername(String username) { this.username = username; }
}
