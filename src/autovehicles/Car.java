package autovehicles;
import autovehicles.fuel.*;

public class Car {
       //I campi di Car sono  variabili che servono solo alla classe Car -> information hiding
       //abbiamo lascaito solo il fueltype visibile fuori dalla classe Car
       //I metodi di Car però servono -> information hiding non significa porre tutto private
        private double speed = 0.0;
        private double fuel = 0.0;
        private FuelType fuelType = null;
        Car(FuelType f) {
            fuelType = f;
        }
        Car() {
        }
        void refuel (FuelTank tank) {
            if (this.getFuelType().isCompatible(tank)) {
                fuel = fuel + tank.getAmount();
                tank.setAmount(0);
                // oppure possiamo usare tank.emptyTank();
            }

        }
        public void brake (double amount) {
            if (amount > speed)
                this.fullBrake();
            else
                speed = speed - amount;
        }
        public void fullBrake() {
            this.speed = 0;
        }
        double computeConsumedFuel (double speedIncrease, double litresPerKmh) {
            return speedIncrease*litresPerKmh;
        }
        public void accellerate (double amount) {
            double fuelCons = computeConsumedFuel(amount, fuelType.getLtperKmh());
            if (fuelCons < fuel) {
                speed = speed + amount;
                fuel = fuel - amount* fuelType.getCostPerLiter();
            }
            else {
               double increaseSpeed = fuel / fuelType.getCostPerLiter();
                speed = speed + increaseSpeed;
                fuel = 0;
            }
        }
    public FuelType getFuelType() {
        return fuelType;
    }

    public static void main(String[] args) {
            Car myCar = new Car();
            FuelType diesel = new FuelType("Diesel", 0.01, 1.4);
            myCar.fuelType = diesel;
            myCar.refuel(new FuelTank(diesel, 99999));
            myCar.accellerate(100);
            myCar.brake(90);
            System.out.println(myCar.fuel);
            System.out.println(myCar.speed);
            myCar.accellerate(50);
            System.out.println(myCar.speed);
            myCar.brake(100);
            System.out.println(myCar.speed);
        }
}

