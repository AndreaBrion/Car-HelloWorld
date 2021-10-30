package Unive.vehicles.animals;

import Unive.vehicles.Loadable;
import Unive.vehicles.Loadable2;
import Unive.vehicles.Vechicle;

public class Carretto extends Vechicle implements Loadable, Loadable2 {
    private double loadedCharge;
    public Carretto(int intialspeed, double load){
        super(intialspeed);
        this.loadedCharge = load;
    }
    public Carretto(int intialspeed){
        super(intialspeed);
    }
    public Carretto(){
        super(0);
    }
    public void setLoad (double amount) {
        this.loadedCharge += amount;
    }
    public double getLoad(){ return this.loadedCharge;}
    //Definiti i due metodi di Loadable2 notiamo che c'è un errore di compilazione
    //Carretto eredita 2 volte un metodo con stessa firma da Loadable e Loadable2
    //In java NON SI PUO' IMPLEMENTARE PIU' INTERFACCE CHE HANNO IMPLEMENTAZIONI DI DEFAULT PER METODI CON STESSA FIRMA
    public void setChargedLoad(double amount) {
        this.loadedCharge += amount;
    }
    public double getChargedLoad() {
        return loadedCharge;
    }
    /** Non ci servono visto che li abbiamo implementati su Loadable
    public double unload () {
            this.loadedCharge = 0.0;
            return 0;
    }
    */
    /**
     *
     *
     public void chargeLoad (double l) {
        if (l > 0 )
            this.loadedCharge += l;
    }
    */

}
