package com.quantityapp.quantity.service;

import com.quantityapp.quantity.dto.QuantityDTO;
import com.quantityapp.quantity.model.QuantityMeasurementEntity;

import java.util.List;

public interface IQuantityMeasurementService {
    boolean compare(QuantityDTO q1, QuantityDTO q2);
    QuantityDTO add(QuantityDTO q1, QuantityDTO q2, String username);
    QuantityDTO subtract(QuantityDTO q1, QuantityDTO q2, String username);
    QuantityDTO divide(QuantityDTO q1, QuantityDTO q2, String username);
    QuantityDTO convert(QuantityDTO q, String targetUnit, String username);

    List<QuantityMeasurementEntity> getAll(String username);
    QuantityMeasurementEntity getById(Long id);
    void delete(Long id);
    QuantityMeasurementEntity update(Long id, QuantityMeasurementEntity entity);
}
