package repository;

import java.util.*;

import entity.QuantityMeasurementEntity;

public class QuantityMeasurementCacheRepository implements IQuantityMeasurementRepository {

    private final List<QuantityMeasurementEntity> records = new ArrayList<>();

    @Override
    public void save(QuantityMeasurementEntity entity) {
        records.add(entity);
    }

    @Override
    public List<QuantityMeasurementEntity> findAll() {
        return new ArrayList<>(records);
    }

    @Override
    public List<QuantityMeasurementEntity> getMeasurementsByOperation(String operation) {
        List<QuantityMeasurementEntity> result = new ArrayList<>();

        for (QuantityMeasurementEntity entity : records) {
            if (entity.getOperation() != null &&
                entity.getOperation().equalsIgnoreCase(operation)) {
                result.add(entity);
            }
        }

        return result;
    }

    @Override
    public List<QuantityMeasurementEntity> getMeasurementsByType(String type) {
        List<QuantityMeasurementEntity> result = new ArrayList<>();

        for (QuantityMeasurementEntity entity : records) {
            if (entity.getMeasurementType() != null &&
                entity.getMeasurementType().equalsIgnoreCase(type)) {
                result.add(entity);
            }
        }

        return result;
    }

    @Override
    public int getTotalCount() {
        return records.size();
    }

    @Override
    public void deleteAll() {
        records.clear();
    }

    @Override
    public String getPoolStatistics() {
        return "Cache repository - no connection pool";
    }

    @Override
    public void releaseResources() {
        System.out.println("No resources to release in cache repository");
    }
}