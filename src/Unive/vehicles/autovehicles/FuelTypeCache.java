package Unive.vehicles.autovehicles;

import java.util.HashMap;
import Unive.vehicles.fuel.*;
    /*
    Vedi hashMap su javadoc (google) 
     */
public class FuelTypeCache {
    HashMap<String, FuelType> map = new HashMap<String, FuelType>();
    FuelType getFuelTypeFromName(String n) {
        return map.get(n);
    }
    // qui i generics sono <String, Fueltype>, la funzione andava anche senza indicarli
    // così però non sappiamo su cosa possiamo cercare e quali sono le chiavi e valori
    //Il compilatore in esecuzione non vede i generics e con casting espliciti
    /* verrà quindi vista come
    public class FuelTypeCache {
    HashMap map = new HashMap();
    FuelType getFuelTypeFromName(String n) {
        return (FuelType) map.get(n);
    }
    *
    */
}
