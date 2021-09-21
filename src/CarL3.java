public class CarL3 {
        double speed = 0.0;
        double fuel = 0.0;
        FuelType fuelType = null;
        CarL3(FuelType f) {            //Volendo evitare che il tipo di carburante venga inizializzato sempre a null creiamo un costruttore che lo inizializzi correttamente
            fuelType = f;
        }
        CarL3() {         //fatto altrimenti il costruttore nel main dava errore
        }
        void refuel (FuelTank tank) {
            if (this.fuelType.equals(tank.type)) {
                fuel = fuel + tank.amount;    //se invoco refuel come posso sapere se è stato fatto carburante o meno?
                tank.amount = 0;              //svuoto i litri della tanica
            }

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


        public static void main(String[] args) {
            CarL3 myCar = new CarL3();
            myCar.fuelType = new FuelType("Diesel", 0.01, 1.4);
            FuelType diesel = new FuelType("Diesel", 0.01, 1.4);
            CarL3 yourCar = new CarL3(diesel);
            myCar.refuel(new FuelTank(5));
            myCar.accellerate(100);
            myCar.brake(50);
            myCar.accellerate(50);
            myCar.brake(100);

            System.out.println(myCar.fuel);
            System.out.println(myCar.speed);
            System.out.println(yourCar.fuel);
            System.out.println(yourCar.speed);
        }
}

