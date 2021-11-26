package Unive.vehicles.fuel;

public class FuelNotSufficientException extends Throwable {
    public FuelNotSufficientException(double fuelConsumed, double fuel) {
        super("Il veicolo non ha abbastanza benzina, ha " + fuel + " lt");
    }
}
