package units;

public enum WeightUnit implements IMeasurable {
    GRAM(0.001),
    KILOGRAM(1.0),
    POUND(0.453592),
    OUNCE(0.0283495);

    private final double conversionFactor;

    WeightUnit(double factor) {
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
        return "WEIGHT";
    }
}