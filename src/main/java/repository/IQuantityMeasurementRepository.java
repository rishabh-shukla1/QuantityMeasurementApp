package repository;

import java.util.List;

import entity.QuantityMeasurementEntity;

public interface IQuantityMeasurementRepository {
    void save(QuantityMeasurementEntity entity);
    List<QuantityMeasurementEntity> findAll();
    List<QuantityMeasurementEntity> getMeasurementsByOperation(String operation);
    List<QuantityMeasurementEntity> getMeasurementsByType(String type);
    int getTotalCount();
    void deleteAll();
    String getPoolStatistics();
    void releaseResources();
}