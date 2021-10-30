package Unive.vehicles;
import Unive.vehicles.animals.Carretto;
import Unive.vehicles.autovehicles.*;
import Unive.vehicles.fuel.*;

    public class Race {
    /**
     * @param v1 first vehicle
     * @param v2 second vehicle
     * @param length
     * @return the id of the winner or "-1" if there is a draw
     */
    public static int race (Vechicle v1, Vechicle v2, double length) {
        //qui viene invocato la versione di accellerate corretta sulla base della
        //classe di v1 e v2, se v1 è una bici viene usato accellerate di Bicycle!
        System.out.println("Race between vehicles");
        v1.fullBrake();
        v2.fullBrake();
        double distanceV1 = 0, distanceV2 = 0;
        if(v1 instanceof Loadable) //Senza interfaccia Loadable e implements, qui otterremmo degli errori -> non vede il tipo
            ((LoadableUnloadable) v1).unload();
        if(v2 instanceof Loadable) //Senza interfaccia Loadable e implements, qui otterremmo degli errori -> non vede il tipo
            ((LoadableUnloadable) v2).unload();
        if (v1 instanceof Printable) { //vediamo che qui stampa sse è Truck o Bicycle!
            ((Printable) v1).print();
        }
        if (v2 instanceof Printable) { //vediamo che qui stampa sse è Truck o Bicycle!
            ((Printable) v2).print();
        }
        if (v1 instanceof Car) { //ritorna vero se v1 è un istanza di Car o dei suoi sottotipi quindi anche Truck!
            // Se pero poniamo v1 instanceof Truck e v1 è Car allora
            // ritorna falso se v1 è Car!
            FuelTank three_lt = new FuelTank(((Car) v1).getFuelType(), 3);
            Car c1 = (Car) v1; //Questo funziona anche se Truck c1 = (Truck) v1
            c1.refuel(three_lt); // c1 potrebbe essere stesso tipo o sottotipo del tipo di v1
        }
        if (v2 instanceof Truck) {
            FuelTank three_lt = new FuelTank(((Truck) v2).getFuelType(), 3);
            Truck t2 = (Truck) v2;
            t2.refuel(three_lt);
        }
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
    public static int race (Car v1, Car v2, double length) {
        System.out.println("Race between cars");
        return -1;
    }
    public static int race (Car v1, Vechicle v2, double length) {
        System.out.println("Race between a car and a vehicles");
        return -1;
    }
    public static void main(String[] args) {
        FuelType Diesel = new FuelType("Diesel", 0.01, 1.4);
        FuelTank two_lt = new FuelTank(Diesel, 2);
        Car C = new Car (Diesel);
        Bicycle B = new Bicycle(10, 1 ,1);
        Truck T = new Truck(Diesel);
        Car myCar = new Car(Diesel);
        Car youCar = new Car(Diesel);
        Vechicle v1 = myCar;
        Vechicle v2 = youCar;
        race(v1,v2, 100); //qui i tipi dinamici di v1 e v2 è Car, però per scelta di java (guarda
                                //i tipi statici) usa il metodo race dove i parametri soon (Vechicle v1, Vechicle v1, ...)
        
    }


}
