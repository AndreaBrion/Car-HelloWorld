package vehicles;
import vehicles.fuel.*;
import vehicles.fuel.FuelTank;
import vehicles.fuel.FuelType;

public class Truck {
    private double speed;
    private double loadedCharge = 0.0;
    private double fuel = 0;
    private final FuelType fuelType;
    public Truck (FuelType f){
        this.fuelType = f;
    }

    void refuel (FuelTank amount) {
        if (this.getFuelType().isCompatible(tank)) {
            fuel = fuel + tank.getAmount();
            tank.setAmount(0);
            // oppure possiamo usare tank.emptyTank();
        }
    }

    private FuelType getFuelType()  {
        return fuelType;
    }

    /**
     *
     */
    public void accellerate (double a){
        if (a>0)
            this.speed = a + this.speed;
    }
    /**
     *
     */
    public void fullBreak() {
        this.speed = 0;
    }
    void chargeLoad (double l) {
        if (l > 0 )
            this.loadedCharge += l;
    }
    double unload (double u) {
        if (u > 0) {
            double value = this.loadedCharge;
            this.loadedCharge = 0.0;
            return value;
        }
        return 0.0;
    }
    public void setSpeed(double speed) {
        if (this.speed >= 0)
            this.speed = speed;
    }
    /*Questo metodo diventa una PROPRIETA' DEL NOSTRO OGGETTO MACCHINA, proprietà
    * derivata da un oggetto aggregato all'interno della mia macchina.
    * Lo immagazzino all'interno dello stato della mia macchina come un campo in
    * modo da usarlo a piacere
    */
    double computeConsumedFuel (double speedIncrease, double litresPerKmh) {
        return speedIncrease*litresPerKmh;
    }

}
