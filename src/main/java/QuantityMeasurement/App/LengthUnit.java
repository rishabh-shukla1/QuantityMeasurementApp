package QuantityMeasurement.App;

public enum LengthUnit implements IMeasurable{

    FEET(1.0),
    INCHES(1.0 / 12.0),
    YARDS(3.0),
    CENTIMETERS(1.0 / 30.48);

    private final double conversionFactorToFeet;

    LengthUnit(double factor) {
        this.conversionFactorToFeet = factor;
    }

    public double getConversionFactor() {
        return conversionFactorToFeet;
    }

    // convert this unit - base unit (feet)
    public double convertToBaseUnit(double value) {
        return value * conversionFactorToFeet;
    }

    // convert base unit (feet) - this unit
    public double convertFromBaseUnit(double baseValue) {
        return baseValue / conversionFactorToFeet;
    }

	@Override
	public String getUnitName() {
		return this.name();
	}
}