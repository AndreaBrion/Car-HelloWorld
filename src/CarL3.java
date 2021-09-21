public class CarL3 {
        double speed = 0.0;
        double fuel = 0.0;
        FuelType fuelType = null;
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
            double fuelCons = amount* fuelType.ltperKmh; //notiamo che ora per accedere al campo dobbiamo
            if (fuelCons < fuel) {                      //accedere allo stato della classe (dati) FuelType
                speed = speed + amount;                 //tramite l'oggetto ltperKmh
                fuel = fuel - amount* fuelType.ltperKmh;
            }
            else {
                double increaseSpeed = fuel / fuelType.ltperKmh;
                speed = speed + increaseSpeed;
                fuel = 0;
            }
        }

        public static void main(String[] args) {
            //Car myCar = new Car()
            //myCar.accellerate(100)
            //Così non si verificava l'errore dato dall'accesso ad un campo null in quanto
            //il costruttore era della classe Car, la quale non ha FuelType!
            CarL3 myCar = new CarL3();
            myCar.fuelType = new FuelType(); //Per evitare l'errore in compilazione dobbiamo creare un oggetto FuelType e inizializzarlo, qui lo creiamo
            myCar.fuelType.type = "Diesel";  //Qui accediamo al campo dell'oggetto fuelType (oggetto di classe FuelType) e ci scriviamo dentro
            myCar.fuelType.ltperKmh = 1.4;   // Sopra abbiamo fatto OggettoMyCar.OggettoFuelType(trovato come campo in OggettoCar).CampoType
            myCar.fuelType.costPerLiter = 0.01;
            //Se non inizializzassi ltperKmh e costPerLiter questi sarebbero tutti 0 o null 
            myCar.refuel(2);
            myCar.accellerate(100);
            myCar.brake(50);
            myCar.accellerate(50);
            myCar.brake(100);

            System.out.println(myCar.fuel);
            System.out.println(myCar.speed);
        }
}

//Volendo approfondire l'interazione fra oggetti : Supponendo di volere diversi tipi di carburante
//Serve una classe a sè : FuelType