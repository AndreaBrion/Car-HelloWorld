package vehicles;
import vehicles.autovehicles.*;
import vehicles.fuel.FuelTank;
import vehicles.fuel.FuelType;

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
        FuelType diesel = new FuelType("Diesel", 0.01, 1.4);
        FuelTank two_lt = new FuelTank(diesel, 2);
        Car C = new Car (new FuelType("diesel", 1.4, 0.01));
        Bicycle B = new Bicycle(10, 1 ,1);
        Truck T = new Truck(diesel);
        C.refuel(two_lt);
        T.refuel(two_lt);
        race(B,C, 100);

        Vechicle v1 = C;
        Vechicle v2 = T;
        //Qui se facciamo
        // v1.refuel(two_lt)
        //Otteniamo errore
        //In quanto il compilatore obbliga a chiamare solo metodi di vehicle
        //Assegnando una sottoclasse/sottotipo ad un supertipo posso accedere SOLO all'interfaccia del supertipo!
    }
}
