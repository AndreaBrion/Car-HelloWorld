public class CarL3 {
        double speed = 0.0;
        double fuel = 0.0;
        FuelType fuelType = null;
        CarL3(FuelType f) {            //Volendo evitare che il tipo di carburante venga inizializzato sempre a null creiamo un costruttore che lo inizializzi correttamente
            fuelType = f;
        }
        CarL3() {         //fatto altrimenti il costruttore nel main dava errore
        }
        //E' possibile avere più costruttori, basta che non si sovrascrivano fra loro (qui la discriminante è se hanno gli stessi parametri -> stessa FIRMA)
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
                //stealfuel(this)              //Qui this è un puntatore ad un oggetto -> ciò rende possibile passare quel puntatore ad un altro metodo, come fosse una variabile locale! Il metodo viene eseguito sull'istanza dell'oggetto corrente
                double increaseSpeed = fuel / fuelType.ltperKmh;
                speed = speed + increaseSpeed;
                fuel = 0;
            }
        }
        //void stealFuel (Car c) {  //solo per capire sopra vedi "stealfuel(this)"
        //   this.fuel = c.fuel+this.fuel;
        // }

        public static void main(String[] args) {
            //Car myCar = new Car()
            //myCar.accellerate(100)
            //Così non si verificava l'errore dato dall'accesso ad un campo null in quanto
            //il costruttore era della classe Car, la quale non ha FuelType!
            CarL3 myCar = new CarL3();  // Stessa cosa, se chiamassi new CarL3() avrei errore, mancano il parametro!
            myCar.fuelType = new FuelType("Diesel", 0.01, 1.4); //Qui vediamo l'uso del costruttore, se chiamassi come prima new FuelType() avrei errore, in quanto il costruttore per mia definizione prende 3 parametri!
//Volendo usare il costruttore  CarL3(FuelType f) faremmo
//          CarL3 myCar = new CarL3(new FuelType("Diesel", 0.01, 1.4)); Qui viene eseguito prima il costruttore FuelType (poi ritorna il puntatore all'oggetto fueltype) e poi quello CarL3
            FuelType diesel = new FuelType("Diesel", 0.01, 1.4);
            CarL3 yourCar = new CarL3(diesel);
            myCar.refuel(2);
            myCar.accellerate(100);
            myCar.brake(50);
            myCar.accellerate(50);
            myCar.brake(100);
            yourCar.refuel(50);
            yourCar.accellerate(80);
            yourCar.brake(50);

            System.out.println(myCar.fuel);
            System.out.println(myCar.speed);
            System.out.println(yourCar.fuel);
            System.out.println(yourCar.speed);
        }
}

