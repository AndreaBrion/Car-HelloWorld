package Unive.vehicles;
import Unive.vehicles.autovehicles.*;
import Unive.vehicles.fuel.FuelNotSufficientException;
import Unive.vehicles.fuel.*;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

public class Race<T extends Vechicle> {
        /**
         * @param v1 first vehicle
         * @param v2 second vehicle
         * @param length
         * @return the id of the winner or "-1" if there is a draw
         */
    public static int race (Vechicle v1, Vechicle v2, double length) throws ImpossibleAccellerateException {
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
    protected T v1, v2;
    public Race(T v1, T v2) {//no problem con i parametri in quanto T estende Vechicle!
        this.v1 = v1;
        this.v2 = v2;
            //infatti facendo :
            //this.v1.
            //abbiamo a disposizione i metodi di Vechicle!
        }
    //Creiamo un'implementazione di un metodo d'istanza della classe
    public static <T extends Vechicle> T new_race(T v1, T v2, double length) {
        try {
            v1.fullBrake();
            v2.fullBrake();
            double distanceV1 = 0;
            double distanceV2 = 0;
            while (!(distanceV1 >= length || distanceV2 >= length)) {
                distanceV1 += v1.getSpeed();
                distanceV2 += v2.getSpeed();
                v1.accellerate(Math.random() * 10.0);
                v2.accellerate(Math.random() * 10.0);
            }
            if (distanceV1 > distanceV2)
                return v1;
            else
                return v2;
        }
                catch (OutOfMemoryError e) {
                    System.err.println("This is quite unexpected"); //non System.out
                    return null;
                }
                 catch (ImpossibleAccellerateException e) {
                    throw new IllegalArgumentException("Random should never return a negative value", e);
                    /*All'interno dei Throwable possiamo anche indicare quale eccezione si è verificata e
                    abbiamo 2 possibilità, quella sopra e :
                    IllegalArgumentException r = new IllegalArgumentException("Random should never return a negative value");
                    r.initCause();
                    throw r;
                    Il metodo initCause può essere invocato SOLO una volta in quanto la causa di un'eccezione può
                    essere settata solo una volta!
                     */
                }
        finally {
            v1.fullBrake();
            v2.fullBrake();
        }
    }

    public static void main(String[] args) throws ImpossibleAccellerateException, NoSuchFieldException, IllegalAccessException {
       /* FuelTypeCache cache = new FuelTypeCache();
        FuelType Petrol = new FuelType("Petrol", 1.4, 0.01);
        FuelType Diesel = new FuelType("Diesel", 1.3, 0.015);
        FuelTank two_lt = new FuelTank(Diesel, 2);
        FuelTank two_lt2 = new FuelTank(Diesel, 2);
        FuelTank three_lt = new FuelTank(Petrol, 2);
        Car C = new Car(0, cache.getFuelTypeFromName("Diesel"));
        Car Y = new Car(0, cache.getFuelTypeFromName("Petrol"));
        Bicycle B = new Bicycle(0   , 1, 1);
        Bicycle b = new Bicycle(0,1,1);
        Truck T = new Truck(Diesel);
        Race.new_race(B, b, 100);
        */
        //Vediamo come ispezionare il contenuto della classe tramite Reflection
        Class v = Car.class; //v è un'stanza di classe che rappresenta Car
        Class superclass = v.getSuperclass();
        //così vedo speed e stampo
        for(Field f : superclass.getDeclaredFields())
            System.out.println(f);
        //Vogliamo prendere l'oggetto fields che ci rappresenta la speed
        //Field speedField = superclass.getField("speed");
        /*Questo ridà un'eccezione in quanto NON VEDE speed perchè privata in Vechicle!
        speed NON è pubblico, getField vede solo i pubblici
        Cambiare il modifier per vedene gli effetti
        Scrivendo
        speedField.get
        Notiamo che abbiamo a disposizione un sacco di getter, perchè un campo può essere di qualsiasi
        tipo
        Posso anche eseguire
        speedField.get()
        Il quale prende un Object e ritorna un Object, qui notiamo i primi problemi :
        Anche se per ogni tipo primitivo o un get : getBoolean, getChar ecc...
        *
        speedField.getDouble(v1);
        Qui di nuovo troviamo dei problemi
        Se ho un campo non è detto che vi abbia accesso (potrebbe essere private ad esempio), nel momento in
        cui ci provo -> illegalAccessException!
        Tutti questi controlli non li abbiamo se usando il sistema di tipi statico! Usando Reflection sono possibili
        tutte queste eccezioni
        */
        //Se cambiamo la visibilità di speed in Vechicle
        Field speedField = superclass.getField("speed");
        Vechicle v1 = new Vechicle(0.0);
        double d = speedField.getDouble(v1);
        System.out.println(d);
        /*
        boolean d = speedField.getBoolean(v1);
        System.out.println(d);
        Otterrei IllegalArgumentException in quanto stiamo provando ad accedere al campo speed dando
        un booleano*/
    }

}
