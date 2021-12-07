package Unive.vehicles;
import Unive.vehicles.autovehicles.*;
import Unive.vehicles.fuel.FuelNotSufficientException;
import Unive.vehicles.fuel.*;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import static java.lang.reflect.Modifier.isFinal;
import static java.lang.reflect.Modifier.isPublic;

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

    public static void main(String[] args) throws ImpossibleAccellerateException, NoSuchFieldException, IllegalAccessException, NoSuchMethodException, InvocationTargetException {
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
        Field speedField = superclass.getDeclaredField("speed");
        Vechicle v1 = new Vechicle(0.0);
        Method accellerateMethod = superclass.getMethod("accellerate", double.class); //campi
        //con double.class è il tipo del parametro dato a accellerate -> utile per selezionare un metodo preciso
        //accellerateMethod.invoke(v1, 1, 2, 3); //prende un Object e un elenco (qui di numeri) invoke usa l'elenco come input del metodo
        //Qui compila, ma restituisce un'eccezione, come se facessimo
        //accellerateMethod.invoke("pippo", 1, 2, 3);
        accellerateMethod.invoke(v1, 1.0);
        /*Così funziona! Se ponessi invece :
         speedField.setAccessible(true);
         speedField.setDouble(v1,-100.0);
         accellerateMethod.invoke(v1, 1.0);
        In quanto pongo la speed negativa ed il metodo accellerate lancia un'eccezione!*/
        //Vediamo getModifiers
        int i = accellerateMethod.getModifiers();
        /*I modifiers sono una serie di costanti definite nella classe .java.lang.reflect.
        * Ciascuno che ci rappresenta un valore in base a che sia public, final, interface, abstract ecc
        il valore del public è 1!
        Per capirlo possiamo usare
         */
        System.out.println(isPublic(i));
        System.out.println(isFinal(i));
        Car v2= new Car(0.0, new FuelType("diesel", 0.015, 0.01));
        //accellerateMethod.invoke(v2, 1.0); //Questo lancerà eccezzione visto che l'auto non ha sufficiente
        //benzina! Se infatti
        v2.refuel(2);
        accellerateMethod.invoke(v2, 1.0); //funziona!
        System.out.println(v2);

    }

}
