package Unive.vehicles.fuel;

import Unive.vehicles.ImpossibleAccellerateException;

public class FuelNotSufficientException extends ImpossibleAccellerateException {
    public FuelNotSufficientException(double fuelConsumed, double fuel) {
        super("Il veicolo non ha abbastanza benzina, ha " + fuel + " lt");
    }
}
