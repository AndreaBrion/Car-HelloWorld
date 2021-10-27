package vehicles;

public class Carretto extends Vechicle{
    private double loadedCharge;
    public Carretto(int intialspeed){
        super(intialspeed);
        this.loadedCharge = 0.0;
    }
    public Carretto(){
        super(0);
        this.loadedCharge = 0.0;
    }
    public Carretto(int intialspeed, double load){
        super(intialspeed);
        this.loadedCharge = load;
    }
    public double unload (double u) {
        if (u > 0) {
            double value = this.loadedCharge;
            this.loadedCharge = 0.0;
            return value;
        }
        return 0.0;
    }
    public void chargeLoad (double l) {
        if (l > 0 )
            this.loadedCharge += l;
    }


}
