package Unive.vehicles;
import Unive.vehicles.autovehicles.Car;
import Unive.vehicles.autovehicles.FuelTypeCache;
import Unive.vehicles.autovehicles.Truck;
import Unive.vehicles.fuel.FuelTank;
import Unive.vehicles.fuel.FuelType;

import java.util.HashMap;
import java.util.HashSet;

public class VehicleHashSet<T extends Vechicle> extends HashSet<T> { //qui ho fissato il tipo -> cioè avrò insiemi di SOLI veicoli

    public Vechicle new_race(double length) throws NegativeSpeedException, InconsistentSpeedException {
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
    public static void main (String[] args) throws NegativeSpeedException, InconsistentSpeedException {
        VehicleHashSet set = new VehicleHashSet<Vechicle>();
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
        set.add(new Bicycle(10));
        set.add(C);
        set.add(Y);
        set.add(B);
        set.add(T);
        Vechicle winner = set.new_race(100.0);
    }
}
