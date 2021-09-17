public class Car {
        double speed = 0.0;
        double fuel = 0.0;
        double ltperKmh = 0.0;

        void refuel (double amount) {
            fuel = fuel + amount;
        }
        void brake (double amount) {
            if (amount > speed)
                speed = 0;
            else
                speed = speed - amount;
        }
        void accellerate (double amount) {
            double fuelCons = amount*ltperKmh;
            if (fuelCons < fuel) {
                speed = speed + amount;
                fuel = fuel - amount*ltperKmh;
            }
            else {
                double increaseSpeed = fuel / ltperKmh;
                speed = speed + increaseSpeed;
                fuel = 0;
            }
        }
        public static void main(String[] args) {
            Car myCar = new Car();
            myCar.accellerate(100);
            Car yourCar = myCar;
            myCar.brake(30);
            myCar.accellerate(50);
            myCar.brake(200);

            System.out.println(myCar.fuel);
            System.out.println(myCar.speed);
        }

}

// speed e fuel sono i campi dell'oggetto
// speed = speed + amount PUO' ESSERE FATTO  speed+=amount
// Non è possibile modificare la struttura della classe (dal main?)
// myCar contiene un riferimento all'oggetto myCar
// infatti Car yourCar = myCar allora yourCar punta all'oggetto myCar
// qui se cambiamo lo stato di myCar allora lo cambiamo anche in yourCar
