package com.quantityapp.quantity.repository;

import com.quantityapp.quantity.model.QuantityMeasurementEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface QuantityMeasurementRepository extends JpaRepository<QuantityMeasurementEntity, Long> {
    // fetch only records belonging to this username
    List<QuantityMeasurementEntity> findByUsername(String username);
}
