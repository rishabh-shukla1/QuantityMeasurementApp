package units;

public enum VolumeUnit implements IMeasurable {
    LITRE(1.0),
    MILLILITRE(0.001),
    GALLON(3.78541);

    private final double conversionFactor;

    VolumeUnit(double factor) {
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
        return "VOLUME";
    }
}