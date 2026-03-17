package controller;
import dto.QuantityDTO;
import service.IQuantityMeasurementService;
import units.IMeasurable;

public class QuantityMeasurementController {
    private IQuantityMeasurementService service;

    public QuantityMeasurementController(IQuantityMeasurementService service) {
        this.service = service;
    }

    public <U extends IMeasurable> QuantityDTO<U> performCompare(QuantityDTO<U> q1, QuantityDTO<U> q2) {
        return (QuantityDTO<U>) service.compare(q1, q2);
    }

    public <U extends IMeasurable> QuantityDTO<U> performAdd(QuantityDTO<U> q1, QuantityDTO<U> q2) {
        return service.add(q1, q2);
    }

    public <U extends IMeasurable> QuantityDTO<U> performSubtract(QuantityDTO<U> q1, QuantityDTO<U> q2) {
        return service.subtract(q1, q2);
    }

    public <U extends IMeasurable> QuantityDTO<U> performDivide(QuantityDTO<U> q1, QuantityDTO<U> q2) {
        return (QuantityDTO<U>) service.divide(q1, q2);
    }

    public <U extends IMeasurable> QuantityDTO<U> performConvert(QuantityDTO<U> q, U targetUnit) {
        return service.convert(q, targetUnit);
    }
}
