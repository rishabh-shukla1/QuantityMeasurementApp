package service;

import java.time.LocalDateTime;

import dto.QuantityDTO;
import entity.QuantityMeasurementEntity;
import model.QuantityModel;
import repository.IQuantityMeasurementRepository;
import units.IMeasurable;

public class QuantityMeasurementServiceImpl implements IQuantityMeasurementService {
    private final IQuantityMeasurementRepository repository;

    public QuantityMeasurementServiceImpl(IQuantityMeasurementRepository repo) {
        this.repository = repo;
    }

    private <U extends IMeasurable> QuantityModel<U> toModel(QuantityDTO<U> dto) {
        return new QuantityModel<>(dto.getValue(), dto.getUnit());
    }

    private <U extends IMeasurable> QuantityMeasurementEntity buildEntity(
            QuantityDTO<U> q1,
            QuantityDTO<U> q2,
            String operation,
            double result,
            String resultUnit
    ) {
        QuantityMeasurementEntity entity = new QuantityMeasurementEntity();
        entity.setOperand1(q1.getValue());
        entity.setOperand2(q2 != null ? q2.getValue() : 0);
        entity.setUnit1(q1.getUnit().getUnitName());
        entity.setUnit2(q2 != null ? q2.getUnit().getUnitName() : "NA");
        entity.setOperation(operation);
        entity.setResult(result);
        entity.setMeasurementType(q1.getUnit().getMeasurementType());
        entity.setResultUnit(resultUnit);
        entity.setCreatedAt(LocalDateTime.now());
        entity.setError(false);
        return entity;
    }

    @Override
    public <U extends IMeasurable> QuantityDTO<U> compare(QuantityDTO<U> q1, QuantityDTO<U> q2) {
        double v1 = q1.getUnit().convertToBaseUnit(q1.getValue());
        double v2 = q2.getUnit().convertToBaseUnit(q2.getValue());
        double result = (v1 == v2) ? 1.0 : 0.0;

        repository.save(buildEntity(
                q1, q2, "COMPARE", result, "BOOLEAN"
        ));

        return new QuantityDTO<>(result, q1.getUnit());
    }

    @Override
    public <U extends IMeasurable> QuantityDTO<U> add(QuantityDTO<U> q1, QuantityDTO<U> q2) {
        q1.getUnit().validateOperationSupport("ADD");
        q2.getUnit().validateOperationSupport("ADD");

        double v1 = q1.getUnit().convertToBaseUnit(q1.getValue());
        double v2 = q2.getUnit().convertToBaseUnit(q2.getValue());
        double res = q1.getUnit().convertFromBaseUnit(v1 + v2);

        repository.save(buildEntity(
                q1, q2, "ADD", res, q1.getUnit().getUnitName()
        ));

        return new QuantityDTO<>(res, q1.getUnit());
    }

    @Override
    public <U extends IMeasurable> QuantityDTO<U> subtract(QuantityDTO<U> q1, QuantityDTO<U> q2) {
        q1.getUnit().validateOperationSupport("SUBTRACT");
        q2.getUnit().validateOperationSupport("SUBTRACT");

        double v1 = q1.getUnit().convertToBaseUnit(q1.getValue());
        double v2 = q2.getUnit().convertToBaseUnit(q2.getValue());
        double res = q1.getUnit().convertFromBaseUnit(v1 - v2);

        repository.save(buildEntity(
                q1, q2, "SUBTRACT", res, q1.getUnit().getUnitName()
        ));

        return new QuantityDTO<>(res, q1.getUnit());
    }

    @Override
    public <U extends IMeasurable> QuantityDTO<U> divide(QuantityDTO<U> q1, QuantityDTO<U> q2) {
        q1.getUnit().validateOperationSupport("DIVIDE");
        q2.getUnit().validateOperationSupport("DIVIDE");

        double v1 = q1.getUnit().convertToBaseUnit(q1.getValue());
        double v2 = q2.getUnit().convertToBaseUnit(q2.getValue());

        if (v2 == 0) {
            throw new ArithmeticException("Divide by zero");
        }

        double res = v1 / v2;

        repository.save(buildEntity(
                q1, q2, "DIVIDE", res, "RATIO"
        ));

        return new QuantityDTO<>(res, q1.getUnit());
    }

    @Override
    public <U extends IMeasurable> QuantityDTO<U> convert(QuantityDTO<U> q, U targetUnit) {
        double base = q.getUnit().convertToBaseUnit(q.getValue());
        double converted = targetUnit.convertFromBaseUnit(base);

        repository.save(buildEntity(
                q, null, "CONVERT", converted, targetUnit.getUnitName()
        ));

        return new QuantityDTO<>(converted, targetUnit);
    }
}