public class Car {
        double speed = 0.0;
        double fuel = 0.0;
        double ltperKmh = 0.0;
//      speed e fuel sono i campi dell'oggetto
//      speed = speed + amount PUO' ESSERE FATTO  speed+=amount
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
//          Se scrivessimo
//          Car yourCar = new Car();
//          eseguendo "yourCar.accelerate(172);"
//          yourCar avrebbe una velocità di 172, infatti
//          "Car myCar = new Car();" crea un oggetto
//          Qui i due oggetti sono distinti e con velocità distinte.
//
//          Se invece scrivessimo
//          "Car yourCar = myCar;"
//          eseguendo "yourCar.accelerate(172);"
//          allora qui avremmo due variabili locali che puntano
//          allo stesso oggetto (o anche "sono lo stesso oggetto").
//          myCar  è una variabile locale puntatore che PUNTA all'oggetto.
//          Qui quando modificando la velocità di yourCar modifico la velocità di myCar.
//          Se cambiamo lo STATO di myCar allora lo cambiamo anche in yourCar.
            myCar.brake(50);
            myCar.accellerate(50);
            myCar.brake(100);

            System.out.println(myCar.fuel);
            System.out.println(myCar.speed);
        }
}

//Modifiche terminate - Fine lezione 1/2