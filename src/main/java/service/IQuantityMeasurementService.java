package service;

import dto.QuantityDTO;
import units.IMeasurable;

public interface IQuantityMeasurementService {

    <U extends IMeasurable> QuantityDTO<U> compare(QuantityDTO<U> q1, QuantityDTO<U> q2);

    <U extends IMeasurable> QuantityDTO<U> add(QuantityDTO<U> q1, QuantityDTO<U> q2);

    <U extends IMeasurable> QuantityDTO<U> subtract(QuantityDTO<U> q1, QuantityDTO<U> q2);

    <U extends IMeasurable> QuantityDTO<U> divide(QuantityDTO<U> q1, QuantityDTO<U> q2);

    <U extends IMeasurable> QuantityDTO<U> convert(QuantityDTO<U> q, U targetUnit);
}
