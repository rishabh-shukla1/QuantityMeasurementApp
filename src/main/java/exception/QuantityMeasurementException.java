package exception;

public class QuantityMeasurementException extends RuntimeException {

    private final ExceptionType type;

    public enum ExceptionType {
        NULL_UNIT,
        NULL_QUANTITY,
        INVALID_VALUE,
        UNIT_MISMATCH,
        OPERATION_NOT_SUPPORTED,
        DIVIDE_BY_ZERO
    }

    public QuantityMeasurementException(ExceptionType type, String message) {
        super(message);
        this.type = type;
    }

    public ExceptionType getType() {
        return type;
    }
}
