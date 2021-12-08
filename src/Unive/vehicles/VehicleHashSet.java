package Unive.vehicles;
import Unive.vehicles.animals.Carretto;
import Unive.vehicles.autovehicles.Car;
import Unive.vehicles.autovehicles.FuelTypeCache;
import Unive.vehicles.autovehicles.Truck;
import Unive.vehicles.fuel.FuelTank;
import Unive.vehicles.fuel.FuelType;

import javax.naming.event.ObjectChangeListener;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Parameter;
import java.nio.channels.ByteChannel;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;

public class VehicleHashSet<T extends Vechicle> extends HashSet<T> { //qui ho fissato il tipo -> cioè avrò insiemi di SOLI veicoli

    public Vechicle new_race(double length) throws ImpossibleAccellerateException {
        for (Vechicle v : this) {//per ogni Vechicle presente in this (nell'istanza corrente)
            v.fullBrake();
        }
        HashMap<Vechicle, Double> distance = new HashMap<>();//iniziamo a tracciare che dato un
        // Vechicle mi ridà la distanza percorsa da ciascun Vechicle fin qui
        for (Vechicle v : this) {//per ogni Vechicle presente in this (nell'istanza corrente)
            distance.put(v, 0.0);
        }
        //qui tutti i Vechicle sono sulla linea di partenza
        while (true) {
            for (Vechicle v : this) {
                distance.put(v, distance.get(v) + v.getSpeed());
            }//Questo For each esegue : per ogni Vechicle prendi la vecchia distanza e aggiungici la nuova
            for (Vechicle v : this) {
                if (distance.get(v) >= length)
                    return v;
            }//Questo verifica se qualcuno ha vinto
            for (Vechicle v : this) {
                v.accellerate(Math.random() * 10.0);
            }//Questo accellera ogni Vechicle
        }
    }

    static private Collection<Class> getAllVechiclesClasses() {
        HashSet<Class> result = new HashSet<>();
        result.add(Bicycle.class);
        result.add(Car.class);
        result.add(Truck.class);
        result.add(Carretto.class);
        return result;
    }

    private static Vechicle createInstanceOf(Class c) throws IllegalAccessException, InvocationTargetException, InstantiationException {
external:for (Constructor constructor : c.getConstructors()) {
            /*constructor.setAccessible(true);
            Così posso creare l'istanza. Qui non so che veicoli stiamo creando!
            indico : dammi i costruttori, quando trovo quello adatto lo uso!
            */
            Parameter[] parameters = constructor.getParameters(); //Con getParameters prendiamo tutti i parametri che richiede e magari costruirceli
            Object[] parametersValues = new Object[parameters.length];  // Se ho n parametri devo avere n valori da passare al costruttore
            for (int i = 0; i < parameters.length; i++) {
                Object o = getConcreteValue(parameters[i].getType()); //nell'oggetto o pongo un'istanza della classe object che
                /*rappresenti un valore possibile per quel parametro dell'iesimo parametro
                NON CI INTERESSANO I VALORI DEI PARAMETRI, CI INTERESSA COSTRUIRE UN'ISTANZA DI QUELLA CLASSE PER POI CREARE LA GARA
                */
                if (o != null)
                    parametersValues[i] = o;
                else
                    continue external; //indico di continuare il for più esterno
            }
            return (Vechicle) constructor.newInstance(parametersValues);
        }
        return null; // non sono  stato in grado di istanziare il veicolo
    }

    private static Object getConcreteValue(Class<?> type) {
        if (type.equals(FuelType.class))
            return new FuelType("diesel", 0.015, 0.01);
        if (type.equals(double.class) || type.equals(Double.class))
            return 0.0; //esplicitamente viene eseguiti return Double.valueOf(0.0);
        if (type.equals(int.class) || type.equals(Integer.class))
            return Integer.valueOf(0);

        return null;

    }

    private static void refuel(VehicleHashSet<Vechicle> allInstatiatedVehicles) {
        for (Vechicle v : allInstatiatedVehicles) {
            if (v instanceof Car)
                ((Car) v).refuel(500);
        }
    }

    public static void main(String[] args) throws ImpossibleAccellerateException, IllegalAccessException,InstantiationException, InvocationTargetException {
        Collection<Class> allVehicles = getAllVechiclesClasses();
        for (Class c : allVehicles) {
            System.out.println(c.getName());
        }
        VehicleHashSet<Vechicle> allInstatiatedVehicles = new VehicleHashSet();
        for (Class c : allVehicles) {
            Vechicle v = createInstanceOf(c);
            if (v != null)
                allInstatiatedVehicles.add(createInstanceOf(c));
            else
                System.err.println("Unable to instantiate Vehicle " + c.getName());
        }
        for (Vechicle v : allInstatiatedVehicles) {
            System.out.println(v);
        }
        refuel(allInstatiatedVehicles);
        System.out.println("The winner is "+allInstatiatedVehicles.new_race(100.0)); //dobbiamo fare benzina!
    }


}



