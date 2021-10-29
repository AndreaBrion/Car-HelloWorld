package vehicles.animals;

import vehicles.Loadable;
import vehicles.Vechicle;

public class Carretto extends Vechicle implements Loadable {
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
