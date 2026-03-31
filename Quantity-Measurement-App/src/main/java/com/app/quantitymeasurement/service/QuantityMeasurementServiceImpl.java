package com.app.quantitymeasurement.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.app.quantitymeasurement.dto.QuantityDTO;
import com.app.quantitymeasurement.dto.QuantityInputDTO;
import com.app.quantitymeasurement.entity.QuantityMeasurementEntity;
import com.app.quantitymeasurement.repository.QuantityMeasurementRepository;

@Service
public class QuantityMeasurementServiceImpl implements IQuantityMeasurementService {

    @Autowired
    private QuantityMeasurementRepository repository;

    // Convert TO base
    private double convertToBaseUnit(QuantityDTO dto) {

        String unit = dto.getUnit().toUpperCase();
        String type = dto.getMeasurementType();
        double value = dto.getValue();

        if (type.equals("LengthUnit")) {
            if (unit.equals("FEET")) return value * 12;
            if (unit.equals("INCHES")) return value;
        }

        if (type.equals("WeightUnit")) {
            if (unit.equals("KILOGRAM")) return value * 1000;
            if (unit.equals("GRAM")) return value;
        }

        if (type.equals("VolumeUnit")) {
            if (unit.equals("LITRE")) return value * 1000;
            if (unit.equals("MILLILITRE")) return value;
        }

        if (type.equals("TemperatureUnit")) {
            if (unit.equals("CELSIUS")) return value;
            if (unit.equals("FAHRENHEIT")) return (value - 32) * 5 / 9;
        }

        throw new RuntimeException("Invalid Unit");
    }
    // convert from base 
    private double convertFromBaseUnit(double value, String unit, String type) {

        if (type.equals("LengthUnit")) {
            if (unit.equalsIgnoreCase("FEET")) return value / 12;
            if (unit.equalsIgnoreCase("INCHES")) return value;
        }

        if (type.equals("WeightUnit")) {
            if (unit.equalsIgnoreCase("KILOGRAM")) return value / 1000;
            if (unit.equalsIgnoreCase("GRAM")) return value;
        }

        if (type.equals("VolumeUnit")) {
            if (unit.equalsIgnoreCase("LITRE")) return value / 1000;
            if (unit.equalsIgnoreCase("MILLILITRE")) return value;
        }

        if (type.equals("TemperatureUnit")) {
            if (unit.equalsIgnoreCase("CELSIUS")) return value;
            if (unit.equalsIgnoreCase("FAHRENHEIT")) return (value * 9 / 5) + 32;
        }

        throw new RuntimeException("Invalid Unit");
    }

    // Check same type
    private boolean isSameType(QuantityInputDTO input) {
        return input.getThisQuantityDTO().getMeasurementType()
                .equals(input.getThatQuantityDTO().getMeasurementType());
    }

    // add
    @Override
    public QuantityMeasurementEntity add(QuantityInputDTO input) {

        QuantityMeasurementEntity entity = new QuantityMeasurementEntity();

        if (!isSameType(input)) {
            entity.setError(true);
            entity.setErrorMessage("Different measurement types");
            return repository.save(entity);
        }

        double val1 = convertToBaseUnit(input.getThisQuantityDTO());
        double val2 = convertToBaseUnit(input.getThatQuantityDTO());

        double resultBase = val1 + val2;

        String unit = input.getThisQuantityDTO().getUnit();
        String type = input.getThisQuantityDTO().getMeasurementType();

        double finalResult = convertFromBaseUnit(resultBase, unit, type);

        entity.setResultValue(finalResult);
        entity.setResultUnit(unit);
        entity.setOperation("ADD");
        entity.setError(false);

        return repository.save(entity);
    }

    // subtract
    @Override
    public QuantityMeasurementEntity subtract(QuantityInputDTO input) {

        QuantityMeasurementEntity entity = new QuantityMeasurementEntity();

        if (!isSameType(input)) {
            entity.setError(true);
            return repository.save(entity);
        }

        double val1 = convertToBaseUnit(input.getThisQuantityDTO());
        double val2 = convertToBaseUnit(input.getThatQuantityDTO());

        double resultBase = val1 - val2;

        String unit = input.getThisQuantityDTO().getUnit();
        String type = input.getThisQuantityDTO().getMeasurementType();

        double finalResult = convertFromBaseUnit(resultBase, unit, type);

        entity.setResultValue(finalResult);
        entity.setResultUnit(unit);
        entity.setOperation("SUBTRACT");
        entity.setError(false);

        return repository.save(entity);
    }

    // multiply
    @Override
    public QuantityMeasurementEntity multiply(QuantityInputDTO input) {

        QuantityMeasurementEntity entity = new QuantityMeasurementEntity();

        if (!isSameType(input)) {
            entity.setError(true);
            return repository.save(entity);
        }

        double val1 = convertToBaseUnit(input.getThisQuantityDTO());
        double val2 = convertToBaseUnit(input.getThatQuantityDTO());

        double resultBase = val1 * val2;

        String unit = input.getThisQuantityDTO().getUnit();
        String type = input.getThisQuantityDTO().getMeasurementType();

        double finalResult = convertFromBaseUnit(resultBase, unit, type);

        entity.setResultValue(finalResult);
        entity.setResultUnit(unit);
        entity.setOperation("MULTIPLY");
        entity.setError(false);

        return repository.save(entity);
    }

    //divide
    @Override
    public QuantityMeasurementEntity divide(QuantityInputDTO input) {

        QuantityMeasurementEntity entity = new QuantityMeasurementEntity();

        if (!isSameType(input)) {
            entity.setError(true);
            return repository.save(entity);
        }

        double val1 = convertToBaseUnit(input.getThisQuantityDTO());
        double val2 = convertToBaseUnit(input.getThatQuantityDTO());

        if (val2 == 0) {
            entity.setError(true);
            entity.setErrorMessage("Divide by zero");
            return repository.save(entity);
        }

        double result = val1 / val2;

        entity.setResultValue(result);
        entity.setOperation("DIVIDE");
        entity.setError(false);

        return repository.save(entity);
    }

    // compare
    @Override
    public QuantityMeasurementEntity compare(QuantityInputDTO input) {

        QuantityMeasurementEntity entity = new QuantityMeasurementEntity();

        if (!isSameType(input)) {
            entity.setError(true);
            return repository.save(entity);
        }

        double val1 = convertToBaseUnit(input.getThisQuantityDTO());
        double val2 = convertToBaseUnit(input.getThatQuantityDTO());

        entity.setResultString(String.valueOf(val1 == val2));
        entity.setOperation("COMPARE");
        entity.setError(false);

        return repository.save(entity);
    }

    // convert
    @Override
    public QuantityMeasurementEntity convert(QuantityInputDTO input) {

        QuantityMeasurementEntity entity = new QuantityMeasurementEntity();

        QuantityDTO source = input.getThisQuantityDTO();
        QuantityDTO target = input.getThatQuantityDTO();

        double base = convertToBaseUnit(source);
        double result = convertFromBaseUnit(base, target.getUnit(), source.getMeasurementType());

        entity.setResultValue(result);
        entity.setResultUnit(target.getUnit());
        entity.setOperation("CONVERT");
        entity.setError(false);

        return repository.save(entity);
    }

    // history
    @Override
    public List<QuantityMeasurementEntity> getHistoryByOperation(String operation) {
        return repository.findByOperation(operation);
    }

    @Override
    public List<QuantityMeasurementEntity> getHistoryByType(String type) {
        return repository.findByThisMeasurementType(type);
    }

    @Override
    public long getOperationCount(String operation) {
        return repository.countByOperationAndErrorFalse(operation);
    }
    public void setRepository(QuantityMeasurementRepository repository) {
        this.repository = repository;
    }
}
