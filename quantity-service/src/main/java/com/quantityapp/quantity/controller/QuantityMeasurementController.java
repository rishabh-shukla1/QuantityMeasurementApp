package com.quantityapp.quantity.controller;

import com.quantityapp.quantity.dto.QuantityDTO;
import com.quantityapp.quantity.model.QuantityMeasurementEntity;
import com.quantityapp.quantity.service.IQuantityMeasurementService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/quantities")
public class QuantityMeasurementController {

    @Autowired
    private IQuantityMeasurementService service;

    // Gateway sends logged-in username in this header after JWT validation
    // If user is not logged in, header will be null → we use "guest"
    private String getUsername(String header) {
        return (header != null && !header.isBlank()) ? header : "guest";
    }

    @PostMapping("/add")
    public ResponseEntity<QuantityDTO> add(
            @RequestBody List<@Valid QuantityDTO> list,
            @RequestHeader(value = "X-Auth-User", required = false) String username) {
        return ResponseEntity.ok(service.add(list.get(0), list.get(1), getUsername(username)));
    }

    @PostMapping("/subtract")
    public ResponseEntity<QuantityDTO> subtract(
            @RequestBody List<@Valid QuantityDTO> list,
            @RequestHeader(value = "X-Auth-User", required = false) String username) {
        return ResponseEntity.ok(service.subtract(list.get(0), list.get(1), getUsername(username)));
    }

    @PostMapping("/divide")
    public ResponseEntity<QuantityDTO> divide(
            @RequestBody List<@Valid QuantityDTO> list,
            @RequestHeader(value = "X-Auth-User", required = false) String username) {
        return ResponseEntity.ok(service.divide(list.get(0), list.get(1), getUsername(username)));
    }

    @PostMapping("/compare")
    public ResponseEntity<Map<String, Boolean>> compare(
            @RequestBody List<@Valid QuantityDTO> list) {
        boolean result = service.compare(list.get(0), list.get(1));
        return ResponseEntity.ok(Map.of("equal", result));
    }

    @PostMapping("/convert")
    public ResponseEntity<QuantityDTO> convert(
            @RequestBody @Valid QuantityDTO dto,
            @RequestParam String target,
            @RequestHeader(value = "X-Auth-User", required = false) String username) {
        return ResponseEntity.ok(service.convert(dto, target, getUsername(username)));
    }

    // GET ALL - only returns records for the logged-in user
    @GetMapping
    public ResponseEntity<List<QuantityMeasurementEntity>> getAll(
            @RequestHeader(value = "X-Auth-User", required = false) String username) {
        return ResponseEntity.ok(service.getAll(getUsername(username)));
    }

    @GetMapping("/{id}")
    public ResponseEntity<QuantityMeasurementEntity> getById(@PathVariable Long id) {
        return ResponseEntity.ok(service.getById(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<QuantityMeasurementEntity> update(
            @PathVariable Long id,
            @RequestBody QuantityMeasurementEntity entity) {
        return ResponseEntity.ok(service.update(id, entity));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Map<String, String>> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.ok(Map.of("message", "Deleted successfully"));
    }

    @GetMapping("/health")
    public ResponseEntity<String> health() {
        return ResponseEntity.ok("Quantity Service is running");
    }
}
