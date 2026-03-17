package units;

public enum LengthUnit implements IMeasurable {
    INCH(0.0254),
    FEET(0.3048),
    YARD(0.9144),
    CENTIMETER(0.01),
    METER(1.0);

    private final double conversionFactor;

    LengthUnit(double factor) {
        this.conversionFactor = factor;
    }

    @Override
    public double getConversionFactor() {
        return conversionFactor;
    }

    @Override
    public String getUnitName() {
        return name().toLowerCase();
    }
    @Override
    public String getMeasurementType() {
        return "LENGTH";
    }
}