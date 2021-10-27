package vehicles;
import vehicles.autovehicles.*;
import vehicles.fuel.*;

    public class Race {

        /**
         *
          * @param v1 first vehicle
         * @param v2 second vehicle
         * @param length
         * @return the id of the winner or "-1" if there is a draw
         */
    public static int race (Vechicle v1, Vechicle v2, double length) {
        //qui viene invocato la versione di accellerate corretta sulla base della
        //classe di v1 e v2, se v1 è una bici viene usato accellerate di Bicycle!
        v1.fullBrake();
        v2.fullBrake();
        double distanceV1 = 0, distanceV2 = 0;
        while ((distanceV1 < length) && (distanceV2 < length)) {
            v1.accellerate(Math.random() * 10);
            v2.accellerate(Math.random() * 10);
            distanceV1 += v1.getSpeed();
            distanceV2 += v2.getSpeed();
        }
        if (distanceV1 >= length){
            if (distanceV2 >= length)
                return -1;
            else
                return 1;
        }
        else
            return 2;
    }
    public static void main(String[] args) {
        FuelType Diesel = new FuelType("Diesel", 0.01, 1.4);
        FuelTank two_lt = new FuelTank(Diesel, 2);
        Car C = new Car (Diesel);
        Bicycle B = new Bicycle(10, 1 ,1);
        Truck T = new Truck(Diesel);
        C.refuel(two_lt);
        two_lt.setAmount(2);
        T.refuel(two_lt);
        race(B,C, 100);
        Vechicle v1;
        Vechicle v2;
        two_lt.setAmount(2);
        C.refuel(two_lt);
        two_lt.setAmount(2);
        T.refuel(two_lt);
        if (Math.random() >= 0.5)
            v1 = C;
        else
            v1 = T;
        if (Math.random() >= 0.5)
            v2 = C;
        else
            v2 = T;
        C.refuel(two_lt);
        T.refuel(two_lt);
        race(v1,v2,10);
        //Non è detto che eseguendo il codice so che tipo dinamico ho
        Vechicle yourCar = new Car(Diesel);
        Car yourRealCar = (Car) yourCar;
        //Così eseguo il casting, ho tipo statico Vehicle e dinamico (che sappiamo e quindi possiamo fare il casting) Car
        //Posso fare un Veicolo e fare il casting a Car
        Vechicle myV = new Car(Diesel);
        Car myRealV = (Car) myV;
        //senza (Car) non potrei fare tale assegnamento!
        //NON POSSIAMO PERO' FARE, in quanto non posso fare un casting di una macchina a furgone
        //Un oggetto di tipo A non può essere castato ad B!
        Vechicle yourV = new Car(Diesel);
        Truck myRealT = (Truck) yourV;
        //Car non piò essere castata a Truck, ma viceversa si
        //Non è necessario fare casting del tipo pià basso, è possibile fare il castingdi qualsiasi livello intermedio
        //che sia compatibile, infatti 
        Vechicle yourT = new Truck(Diesel);
        Truck yourRealT = (Truck) yourT;
        Car yourRealC = (Car) yourT;


    }
}
