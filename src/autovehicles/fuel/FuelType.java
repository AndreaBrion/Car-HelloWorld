package autovehicles.fuel;
public class FuelType {
    String type;
    final double ltperKmh;
    double costPerLiter;
    double FUEL_CONS;
    FuelType(String t, double c, double l) {
        this.type = t;
        this.costPerLiter = c;
        this.ltperKmh = l;
    }
    FuelType(String t, double c) {
        this.ltperKmh = 0.0;
        type = t;
        costPerLiter = c;
    }
    boolean isCompatible (Car car) {
        return car.fuelType.type.equals(this.type);
    }
}





