package autovehicles.fuel;
import autovehicles.Car;

public class FuelType {
    String type;
    public final double ltperKmh;
    double costPerLiter;
    double FUEL_CONS;
    public FuelType(String t, double c, double l) {
        this.type = t;
        this.costPerLiter = c;
        this.ltperKmh = l;
    }
    FuelType(String t, double c) {
        this.ltperKmh = 0.0;
        type = t;
        costPerLiter = c;
    }
    public boolean isCompatible(Car car) {
        return car.fuelType.type.equals(this.type);
    }
}





