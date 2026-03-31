package com.app.quantitymeasurement.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.app.quantitymeasurement.dto.QuantityInputDTO;
import com.app.quantitymeasurement.entity.QuantityMeasurementEntity;
import com.app.quantitymeasurement.service.IQuantityMeasurementService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/v1/quantities")
public class QuantityMeasurementController {

    @Autowired
    private IQuantityMeasurementService service;

    @PostMapping("/compare")
    public QuantityMeasurementEntity compare(@Valid @RequestBody QuantityInputDTO input) {
        return service.compare(input);
    }

    @PostMapping("/convert")
    public QuantityMeasurementEntity convert(@Valid @RequestBody QuantityInputDTO input) {
        return service.convert(input);
    }

    @PostMapping("/add")
    public QuantityMeasurementEntity add(@Valid @RequestBody QuantityInputDTO input) {
        return service.add(input);
    }

    @PostMapping("/divide")
    public QuantityMeasurementEntity divideQuantities(@Valid @RequestBody QuantityInputDTO input) {
        return service.divide(input);
    }
    @PostMapping("/subtract")
    public QuantityMeasurementEntity subtract(@Valid @RequestBody QuantityInputDTO input) {
        return service.subtract(input);
    }

    @PostMapping("/multiply")
    public QuantityMeasurementEntity multiply(@Valid @RequestBody QuantityInputDTO input) {
        return service.multiply(input);
    }

    @GetMapping("/history/type/{type}")
    public List<QuantityMeasurementEntity> getHistoryByType(@PathVariable String type) {
        return service.getHistoryByType(type);
    }

    @GetMapping("/history/operation/{operation}")
    public List<QuantityMeasurementEntity> getHistory(@PathVariable String operation) {
        return service.getHistoryByOperation(operation);
    }

    @GetMapping("/count/{operation}")
    public long getCount(@PathVariable String operation) {
        return service.getOperationCount(operation);
    }
}
