package Unive.vehicles.autovehicles;
import Unive.vehicles.*;
import Unive.vehicles.fuel.FuelNotSufficientException;
import Unive.vehicles.fuel.*;
import org.junit.Test;

import javax.xml.soap.Text;

/**
 * This class represents a car
 * @author Andrea Brion 860595
 * @since 1.0
 */
public class Car extends Vechicle { //Qui ho errore in quanto Unive.vehicles è astratta -> non posso istanziare un oggetto Car in quanto è una sottoclasse, posso :
       //Porre astratta anche Car
       //Implementare accellerate
    // Facendo car estensione posso levare i metodi in car che sono già presenti in Vehicle
       // Uso i metodi della classe dopo extends, basta vedere i suggerimenti facendo "myCar."
        private double fuel = 0.0;
        private FuelType fuelType = null;
        public Car(FuelType f) {
            super(0);
            fuelType = f;
    }
        public Car() {
            super(0);
    }
        public Car(double speed, FuelType f) {
        super(speed);
        fuelType = f;
    }
    /* Indicando così :
       public void accellerate(@Speed double a) throws ImpossibleAccellerateException {
       Otteniamo errore, in quanto forward non ha un valore, quindi
       public void accellerate(@Speed(forward = true) double a) throws ImpossibleAccellerateException {
       Forward l'ho definito, ma non ho definito il tipo! In quanto ciascun valore della mia annotazione
       può avere un valore di dafault (indicato nella classe Speed -> Km/h
       Infatti equivalre a scrivere
       public void accellerate(@Speed(forward = true, type = "Km/h") double a) throws ImpossibleAccellerateException {
       Tutti gli attributi vengono sempre definiti, se ho dato un valore di default posso fare a meno altrimenti
       se non glielo ho dato devo indicarlo (come per forward!)
     */
        public void accellerate(double a) throws ImpossibleAccellerateException {
        double fuelConsumed = computeConsumedFuel(a, fuelType.getLtperKmh());
        if (fuelConsumed < fuel) {
            super.accellerate(a);
            fuel = fuel - fuelConsumed;
        }
        else {
            /*
            double increaseSpeed = fuel / fuelType.getCostPerLiter();
            super.accellerate(increaseSpeed);
            fuel = 0;
            */
            throw new FuelNotSufficientException(fuelConsumed, fuel);
        }
    }
        public boolean isFuelEmpty() {
            if (fuel <= 0) {
                super.setSpeed(0); // Qui setSpeed è visibile SSE è un metodo protected o public (per
                                   // il ragionamento dei set private conviene porli in protected!
                //avremmo potuto fare anche
                //super.fullBrake();
                return true;
            }
            else
                return false;
        }
        @Deprecated
        public void refuel (int amount) {
                fuel = fuel + amount;
        }
        public void refuel (FuelTank tank) {
            if (this.getFuelType().isCompatible(this.fuelType)) {
                fuel = fuel + tank.getAmount();
                tank.setAmount(0);
                // oppure possiamo usare tank.emptyTank();
            }

        }
        double computeConsumedFuel (double speedIncrease, double litresPerKmh) {
            return speedIncrease*litresPerKmh;
        }
        public FuelType getFuelType() {
        return this.fuelType;
    }
        //Porre set e get in una classe rende praticamente le variabili interessate public
        //Possiamo però porre dei controlli/regole nei set
        //ad esempio rendiamo impossibile porre una velocità negativa!
        //I set modificano lo stato dell'oggetto
        public double getFuelCons () {
        return this.fuelType.getCostPerLiter();
    }
        public static void main(String[] args) throws ImpossibleAccellerateException {
            Car myCar = new Car();
            FuelType diesel = new FuelType("Diesel", 0.01, 1.4);
            myCar.fuelType = diesel;
            diesel.setCostPerLiter(1.35);
            myCar.refuel(new FuelTank(diesel, 99999));
            myCar.accellerate(100);
            myCar.brake(90);
            System.out.println(myCar.fuel);
            System.out.println(myCar.getSpeed());
            myCar.accellerate(50);
            System.out.println(myCar.getSpeed());
            myCar.brake(100);
            System.out.println(myCar.getSpeed());
        }
}
