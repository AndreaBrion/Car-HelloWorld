package autovehicles;
import autovehicles.fuel.*;

public class Car {
        double speed = 0.0;
        double fuel = 0.0;
        public FuelType fuelType = null;
        Car(FuelType f) {
            fuelType = f;
        }
        Car() {
        }
        void refuel (FuelTank tank) {
            if (tank.type.isCompatible(this)) {
                fuel = fuel + tank.amount;
                tank.amount = 0;
            }

        }
        void brake (double amount) {
            if (amount > speed)
                this.fullBrake();
            else
                speed = speed - amount;
        }
        void fullBrake() {
            this.speed = 0;
        }
        void accellerate (double amount) {
            double fuelCons = amount* fuelType.ltperKmh;
            if (fuelCons < fuel) {
                speed = speed + amount;
                fuel = fuel - amount* fuelType.ltperKmh;
            }
            else {
               double increaseSpeed = fuel / fuelType.ltperKmh;
                speed = speed + increaseSpeed;
                fuel = 0;
            }
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

