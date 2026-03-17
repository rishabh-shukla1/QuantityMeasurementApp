package units;

public enum TemperatureUnit implements IMeasurable {

    CELSIUS("Celsius"),
    FAHRENHEIT("Fahrenheit"),
    KELVIN("Kelvin");

    private final String unitName;

    TemperatureUnit(String name) {
        this.unitName = name;
    }

    @Override
    public double getConversionFactor() {
        return 1; // not used for temperature
    }

    @Override
    public String getUnitName() {
        return unitName;
    }

    @Override
    public boolean supportsArithmetic() {
        return false;
    }

    // Convert everything to base unit (Celsius)
    @Override
    public double convertToBaseUnit(double value) {
        switch (this) {
            case CELSIUS:
                return value;

            case FAHRENHEIT:
                return (value - 32) * 5 / 9;

            case KELVIN:
                return value - 273.15;

            default:
                return value;
        }
    }

    // Convert from base unit (Celsius) to target
    @Override
    public double convertFromBaseUnit(double baseValue) {
        switch (this) {
            case CELSIUS:
                return baseValue;

            case FAHRENHEIT:
                return (baseValue * 9 / 5) + 32;

            case KELVIN:
                return baseValue + 273.15;

            default:
                return baseValue;
        }
    }
    @Override
    public String getMeasurementType() {
        return "TEMPERATURE";
    }
}