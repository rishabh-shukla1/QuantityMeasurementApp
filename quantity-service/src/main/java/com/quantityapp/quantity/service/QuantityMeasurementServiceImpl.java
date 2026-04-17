package com.quantityapp.quantity.service;

import com.quantityapp.quantity.core.QuantityEngine;
import com.quantityapp.quantity.dto.QuantityDTO;
import com.quantityapp.quantity.exception.QuantityMeasurementException;
import com.quantityapp.quantity.model.QuantityMeasurementEntity;
import com.quantityapp.quantity.repository.QuantityMeasurementRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class QuantityMeasurementServiceImpl implements IQuantityMeasurementService {

    @Autowired private QuantityMeasurementRepository repo;
    @Autowired private QuantityEngine engine;

    @Override
    public boolean compare(QuantityDTO q1, QuantityDTO q2) {
        return engine.compare(q1, q2);
    }

    @Override
    public QuantityDTO add(QuantityDTO q1, QuantityDTO q2, String username) {
        QuantityDTO result = engine.add(q1, q2);
        repo.save(new QuantityMeasurementEntity(null, "ADD",
                q1.getValue() + " " + q1.getUnit() + " + " + q2.getValue() + " " + q2.getUnit() + " = " + result.getValue() + " " + result.getUnit(),
                username));
        return result;
    }

    @Override
    public QuantityDTO subtract(QuantityDTO q1, QuantityDTO q2, String username) {
        QuantityDTO result = engine.subtract(q1, q2);
        repo.save(new QuantityMeasurementEntity(null, "SUBTRACT",
                q1.getValue() + " " + q1.getUnit() + " - " + q2.getValue() + " " + q2.getUnit() + " = " + result.getValue() + " " + result.getUnit(),
                username));
        return result;
    }

    @Override
    public QuantityDTO divide(QuantityDTO q1, QuantityDTO q2, String username) {
        double result = engine.divide(q1, q2);
        repo.save(new QuantityMeasurementEntity(null, "DIVIDE",
                q1.getValue() + " " + q1.getUnit() + " / " + q2.getValue() + " " + q2.getUnit() + " = " + result,
                username));
        return new QuantityDTO(result, "ratio", q1.getType());
    }

    @Override
    public QuantityDTO convert(QuantityDTO q, String targetUnit, String username) {
        QuantityDTO result = engine.convert(q, targetUnit);
        repo.save(new QuantityMeasurementEntity(null, "CONVERT",
                q.getValue() + " " + q.getUnit() + " → " + result.getValue() + " " + targetUnit,
                username));
        return result;
    }

    @Override
    public List<QuantityMeasurementEntity> getAll(String username) {
        // Only return this user's records
        return repo.findByUsername(username);
    }

    @Override
    public QuantityMeasurementEntity getById(Long id) {
        return repo.findById(id)
                .orElseThrow(() -> new QuantityMeasurementException("Record not found with id: " + id));
    }

    @Override
    public void delete(Long id) {
        if (!repo.existsById(id)) {
            throw new QuantityMeasurementException("Record not found with id: " + id);
        }
        repo.deleteById(id);
    }

    @Override
    public QuantityMeasurementEntity update(Long id, QuantityMeasurementEntity updated) {
        QuantityMeasurementEntity existing = repo.findById(id)
                .orElseThrow(() -> new QuantityMeasurementException("Record not found with id: " + id));
        existing.setOperation(updated.getOperation());
        existing.setResult(updated.getResult());
        return repo.save(existing);
    }
}
