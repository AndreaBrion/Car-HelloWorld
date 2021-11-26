package Unive.vehicles;
import Unive.vehicles.autovehicles.*;
import Unive.vehicles.fuel.*;

import java.util.*;

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
        TreeSet<FuelTank> tanks = new TreeSet<>();//crea un insieme di elementi ordinati, qui
        tanks.add(two_lt);// però non indico il tipo di ordine!
        tanks.add(two_lt2);
        tanks.add(three_lt);

        //Vediamo come possiamo assegnare un tipo ad un altro
        //E' possibile farlo se si passa da un tipo a 64b ad uno a 32b
        int i = 100;
        long j = 1000000;
        j = i; //non da problemi
     // i = j da problemi! dobbiamo invece fare il cast :
        i = (int) j;
     // Oppure possiamo fare
        i = Long.valueOf(j).intValue();

        //Proviamo a fare una lista di interi
        //List<int> list = new List<int>(); Questo da errore in quanto i tipi generici non possono
        //essere primitivi
        //Se passiamo un generics questo deve essere per forza onject o sotto
        List<Integer> list = new ArrayList<Integer>();
        //Vediamo come la classe wrapper Integer ci permettono di usare i valori primitivi come Generics e nelle
        //varie collections
        list.add(1);
      //list.add(1.0); Errore!

        //Da notare che
        Integer wI = i; // che funziona!
        //Con i wrapper viene fatto boxing e unboxing, ,l'istruzione sopra si traduce in
        //Integer wI = Integer.valueOf(i);
        //Chiaramente otteniamo errore nei seguenti assegnamenti
        //wI = 1.0;
        //i = 1.0;
        //Dovremmo
        //wI = Double.ValueOf(1.0).intValue();
        //Un altro esempio
        //Double d = 1; da errore!
        //Mentre invece
        //double d = 1; funziona!
        //Questo perchè double ha una maggior precisione di int e quindi è permesso
        //Con i wrapper invece otteniamo errore perchè il compilatore fa
        //Double d = Double.valueOf(1);
        //In generale usando il wrapping di oggetti posso assegnare un valore numerico ad un tipo wrapper identico
        //al tipo di quel valore numerico
        //NON VALGONO LE CONVERSIONI IMPLICITE DEI TIPI PRIMITIVI
        //Se io eseguo
        int r = wI;
        //Così penso di assegnare ad r un puntatore ad un wrapper, ma qui Java non inserisce il puntatore
        //Qui Java esegue unboxing (prendo l'oggetto wrapper e ritorno il valore)
        //Boxing è prendo il valore e creo l'oggetto
        //Nell'istruzione sopra infatti il compilatore usa il metodo intValue() della classe Integer
        //vedi es sopra : //Integer wI = Integer.valueOf(i);

    }
}
