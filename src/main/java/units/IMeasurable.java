package units;

public interface IMeasurable {

    double getConversionFactor();

    default double convertToBaseUnit(double value) {
        return value * getConversionFactor();
    }

    default double convertFromBaseUnit(double baseValue) {
        return baseValue / getConversionFactor();
    }

    String getUnitName();

    // NEW for UC16
    String getMeasurementType();

    default void validateOperationSupport(String operation) {
        // Default: all units support arithmetic
    }

    default boolean supportsArithmetic() {
        return true;
    }
}