package Unive.vehicles.animals;

import Unive.vehicles.Loadable;
import Unive.vehicles.LoadableUnloadable;
import Unive.vehicles.Vechicle;

public class Carretto extends Vechicle implements LoadableUnloadable {
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
    public void print() {
        System.out.println("I'm a carretto going at " +this.getSpeed()+" km/h");
    }
}
