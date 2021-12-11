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
/*
    void assignSpeed(Vechicle v1, Vechicle v2) throws IllegalAccessException {
        //prendiamo il campo speed di v1, dove il tipo statico è Vechicle, però potrebbe essere Truck o altro,
        //quindi dovrò avere
        Field speedField = getSpeedField(v1.getClass());
        speedField.setAccessible(true);
        Speed fromSpeedAnnotation = speedField.getDeclaredAnnotation(Speed.class);
        String from = fromSpeedAnnotation.type();
        //Questo lo possiamo rifare per v2
        Field speedField_2 = getSpeedField(v2.getClass());
        speedField_2.setAccessible(true);
        Speed fromSpeedAnnotation_2 = speedField_2.getDeclaredAnnotation(Speed.class);
        String from_2 = fromSpeedAnnotation_2.type();

        speedField_2.setDouble(v2, convert(speedField.getDouble(v1), from, from_2));
    }

    private double convert(double aDouble, String from, String from_2) {
        return 1.0;
    }

    private static Field getSpeedField(Class aClass) {
        try {
            Field f = aClass.getDeclaredField("speed");
        } catch (NoSuchFieldException e) {
            return getSpeedField(aClass.getSuperclass());
        }
    }

    double getSpeed(Vechicle v) {
        return 0.0;
    }
*/
    public static void main(String[] args) throws ImpossibleAccellerateException, NoSuchFieldException, IllegalAccessException, NoSuchMethodException, InvocationTargetException, InstantiationException {
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

        //Vediamo come ispezionare il contenuto della classe tramite Reflection
        Class v = Car.class; //v è un'stanza di classe che rappresenta Car
        Class superclass = v.getSuperclass();
        Method accellerateMethod = superclass.getMethod("accellerate", double.class); //campi
        //Constructor VeConstructor = superclass.getConstructor(double.class);
        //Vechicle v1 = VeConstructor.newInstance(10.0); così otteniamo errore, dobbiamo indicare il tipo
        //su cui è parametrizzato
        Constructor<Vechicle> VeConstructor = superclass.getConstructor(double.class);
        Vechicle v1 = VeConstructor.newInstance(10.0);
        System.out.println(v1.getSpeed());
        */
        Class classVehicle = Vechicle.class;
        Field s = classVehicle.getDeclaredField("speed"); //prima devo tirare fuori il campo
        boolean annotated = s.isAnnotationPresent(Speed.class); //preso il campo posso controllare se è presente l'annotazione @Speed
        Speed speedAnnotation = (Speed) s.getAnnotation(Speed.class);
        /*Questo da errore in quanto speedAnnotations è null in quanto annotated risulta false!
        * Nonostante il campo speed sia annotato con @Speed, ricordiamo che in Speed.java
        * abbiamo posto "@Retention(RetentionPolicy.SOURCE)" con la quale indico che è visibile solo
        * nel codice sorgente
        * Non è visibile durante compilazione o esecuzione!
        * dobbiamo porre @Retention(RetentionPolicy.RUNTIME)
        * Ciò che ottniamo con getAnnotations(Speed.class) è un qualcosa di tipo Speed
        * Speed è la nostra definizione di annotazione, quando diciamo al compilatore :
        * public @interface Speed...
        * @interface viene compilato come una classe che estende annotations -> abbiamo a tuttu gli effetti un tipo
        * Infatti speedAnnotations ha i 2 attributi definiti in Speed, con valori concreti!
        * Possiamo così accedere ai valori delle annotazioni
        * */
        String type = speedAnnotation.type();
        boolean forward = speedAnnotation.forward();
    }

}
