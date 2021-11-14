package Unive.vehicles;
import Unive.vehicles.autovehicles.*;
import Unive.vehicles.fuel.*;

import java.util.HashSet;
import java.util.Set;

public class Race<T extends Vechicle> {
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
    private T v1, v2;
    public Race(T v1, T v2) {//no problem con i parametri in quanto T estende Vechicle!
        this.v1 = v1;
        this.v2 = v2;
            //infatti facendo :
            //this.v1.
            //abbiamo a disposizione i metodi di Vechicle!
        }
    //Creiamo un'implementazione di un metodo d'istanza della classe
    public T new_race(double length) {
            v1.fullBrake();
            v2.fullBrake();
            double distanceV1 = 0;
            double distanceV2 = 0;
            while (true) {
                distanceV1 += v1.getSpeed();
                distanceV2 += v2.getSpeed();
                if (distanceV1 >= length || distanceV2 >= length) {
                    if (distanceV1 > distanceV2)
                        return v1;
                    else
                        return v2;
                }
                v1.accellerate(Math.random() * 10.0);
                v2.accellerate(Math.random() * 10.0);
            }

        }
    public static void main(String[] args) {
        FuelTypeCache cache = new FuelTypeCache();
        FuelType Petrol = new FuelType("Petrol", 1.4, 0.01);
        FuelType Diesel = new FuelType("Diesel", 1.3, 0.015);
        FuelTank two_lt = new FuelTank(Diesel, 2);
        FuelTank two_lt2 = new FuelTank(Diesel, 2);
        FuelTank three_lt = new FuelTank(Petrol, 2);
        Car C = new Car(0, cache.getFuelTypeFromName("Diesel"));
        Car Y = new Car(0, cache.getFuelTypeFromName("Petrol"));
        Bicycle B = new Bicycle(10, 1, 1);
        Truck T = new Truck(Diesel);
        //Se faccio :
        Car E = new Race<>(C, Y).new_race(100);
        Set<FuelTank> f = new HashSet<FuelTank>(); //Qui per far si che interpreti Set dobbiamo importare java.util.set
       //
        f.add(two_lt);
        f.add(three_lt);
        f.add(two_lt);
       //Ho aggiunto 3 elementi, ma il size del Set è 2! (vedi debugger)
        System.out.println((f.size()));
        f.add(two_lt2); // Così facendo size dovrebbe essere 3, infatti se
        // usiamo il debugger nella prossima istruione :
        System.out.println(two_lt2.equals(two_lt));
        //otterremo false -> dato dall'implementazione di equals1 dove controlliamo l'id, volendo però possiamo
        //evitare quel controllo ed allora otterremmo true
        //Notiamo però che nel Set ho size 3, ma con 2 volte la stessa tanica!
        f.remove(two_lt2);
        //se eseguo nuovamente
        f.remove(two_lt2); // non fa nulla in quanto usa l'hashcode che deriva dalla classe object
        //Costrutto for each
        f.add(three_lt);
        f.add(two_lt);
        for(FuelTank f1 : f) // dopo i : ci va l'iterable
            System.out.println(f1.getAmount());
    }
}
