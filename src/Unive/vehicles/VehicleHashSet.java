package Unive.vehicles;
import Unive.vehicles.animals.Carretto;
import Unive.vehicles.autovehicles.Car;
import Unive.vehicles.autovehicles.FuelTypeCache;
import Unive.vehicles.autovehicles.Truck;
import Unive.vehicles.fuel.FuelTank;
import Unive.vehicles.fuel.FuelType;

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

    public static void main (String[] args) throws ImpossibleAccellerateException {
        Collection<Class> allVehicles = getAllVechiclesClasses();
        for(Class c : allVehicles) {
            System.out.println(c.getName());
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
}
