package QuantityMeasurement.App;
import java.util.List;

import controller.QuantityMeasurementController;
import dto.QuantityDTO;
import entity.QuantityMeasurementEntity;
import repository.IQuantityMeasurementRepository;
import repository.QuantityMeasurementDatabaseRepository;
import service.IQuantityMeasurementService;
import service.QuantityMeasurementServiceImpl;
import units.LengthUnit;
import units.TemperatureUnit;
import units.VolumeUnit;
import units.WeightUnit;

public class QuantityMeasurementApp {

    public static void main(String[] args) {

        IQuantityMeasurementRepository repository = new QuantityMeasurementDatabaseRepository();
        IQuantityMeasurementService service = new QuantityMeasurementServiceImpl(repository);
        QuantityMeasurementController controller = new QuantityMeasurementController(service);

        System.out.println("=== Quantity Measurement Application ===");
        System.out.println("Using DATABASE repository");

        // optional: har run par old data clear karna ho to uncomment karo
        // repository.deleteAll();

        // UC4 / UC8 style compare
        QuantityDTO<LengthUnit> length1 = new QuantityDTO<>(5.0, LengthUnit.FEET);
        QuantityDTO<LengthUnit> length2 = new QuantityDTO<>(60.0, LengthUnit.INCH);
        QuantityDTO<LengthUnit> compareResult = controller.performCompare(length1, length2);
        System.out.println("Compare Result: " + compareResult.getValue());

        // UC7 style add for weight
        QuantityDTO<WeightUnit> weight1 = new QuantityDTO<>(2.0, WeightUnit.KILOGRAM);
        QuantityDTO<WeightUnit> weight2 = new QuantityDTO<>(500.0, WeightUnit.GRAM);
        QuantityDTO<WeightUnit> addResult = controller.performAdd(weight1, weight2);
        System.out.println("Addition Result: " + addResult.getValue() + " " + addResult.getUnit().getUnitName());

        // UC5 convert
        QuantityDTO<LengthUnit> convertResult = controller.performConvert(length1, LengthUnit.METER);
        System.out.println("Conversion Result: " + convertResult.getValue() + " " + convertResult.getUnit().getUnitName());

        // UC10 subtract
        QuantityDTO<LengthUnit> subtractResult = controller.performSubtract(length1, length2);
        System.out.println("Subtraction Result: " + subtractResult.getValue() + " " + subtractResult.getUnit().getUnitName());

        // UC11 divide
        QuantityDTO<LengthUnit> divideResult = controller.performDivide(length1, length2);
        System.out.println("Division Result: " + divideResult.getValue());

        // volume add
        QuantityDTO<VolumeUnit> volume1 = new QuantityDTO<>(1.0, VolumeUnit.LITRE);
        QuantityDTO<VolumeUnit> volume2 = new QuantityDTO<>(500.0, VolumeUnit.MILLILITRE);
        QuantityDTO<VolumeUnit> volumeAddResult = controller.performAdd(volume1, volume2);
        System.out.println("Volume Addition Result: " + volumeAddResult.getValue() + " " + volumeAddResult.getUnit().getUnitName());

        // temperature equality
        QuantityDTO<TemperatureUnit> temp1 = new QuantityDTO<>(0.0, TemperatureUnit.CELSIUS);
        QuantityDTO<TemperatureUnit> temp2 = new QuantityDTO<>(32.0, TemperatureUnit.FAHRENHEIT);
        QuantityDTO<TemperatureUnit> tempCompareResult = controller.performCompare(temp1, temp2);
        System.out.println("Temperature Compare Result: " + tempCompareResult.getValue());

        // saved records
        System.out.println("\n=== Saved Records In Database ===");
        List<QuantityMeasurementEntity> records = repository.findAll();
        for (QuantityMeasurementEntity entity : records) {
            System.out.println(entity);
        }

        System.out.println("\nTotal Records: " + repository.getTotalCount());

        System.out.println("\n=== Compare Operations ===");
        List<QuantityMeasurementEntity> compareRecords = repository.getMeasurementsByOperation("COMPARE");
        for (QuantityMeasurementEntity entity : compareRecords) {
            System.out.println(entity);
        }

        System.out.println("\n=== Length Type Records ===");
        List<QuantityMeasurementEntity> lengthRecords = repository.getMeasurementsByType("LENGTH");
        for (QuantityMeasurementEntity entity : lengthRecords) {
            System.out.println(entity);
        }

        System.out.println("\nDone.");
    }
}