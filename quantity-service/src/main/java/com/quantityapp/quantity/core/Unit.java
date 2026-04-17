package com.quantityapp.quantity.core;

/**
 * Supported unit types and their conversion factors to base units.
 * Length base: meter | Weight base: kg | Volume base: litre | Temperature: special handling
 */
public enum Unit {

    // LENGTH (base = meter)
    METER("meter", "Length", 1.0),
    KM("km", "Length", 1000.0),
    CM("cm", "Length", 0.01),
    FEET("feet", "Length", 0.3048),
    INCH("inch", "Length", 0.0254),
    YARD("yard", "Length", 0.9144),

    // WEIGHT (base = kg)
    KG("kg", "Weight", 1.0),
    GRAM("gram", "Weight", 0.001),
    TONNE("tonne", "Weight", 1000.0),

    // VOLUME (base = litre)
    LITRE("litre", "Volume", 1.0),
    ML("ml", "Volume", 0.001),
    GALLON("gallon", "Volume", 3.78541),

    // TEMPERATURE (special: no linear factor, handled separately)
    CELSIUS("celsius", "Temperature", 1.0),
    FAHRENHEIT("fahrenheit", "Temperature", 1.0);

    private final String unitName;
    private final String category;
    private final double toBaseFactor;

    Unit(String unitName, String category, double toBaseFactor) {
        this.unitName = unitName;
        this.category = category;
        this.toBaseFactor = toBaseFactor;
    }

    public String getUnitName() { return unitName; }
    public String getCategory() { return category; }
    public double getToBaseFactor() { return toBaseFactor; }

    public static Unit fromString(String type, String name) {
        for (Unit u : values()) {
            if (u.unitName.equalsIgnoreCase(name) && u.category.equalsIgnoreCase(type)) {
                return u;
            }
        }
        throw new IllegalArgumentException("Unknown unit: " + name + " for type: " + type);
    }
}
