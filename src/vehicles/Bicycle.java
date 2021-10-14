package vehicles;

public class Bicycle extends Vechicle{
    private double speed = 0;
    private double frontTire, rearTire;
    public double frontTirePressure () {
        return this.frontTire;
    }
    public double rearTirePressure () {
        return this.rearTire;
    }

    /**
     *
     */
    public void accellerate (double a){
        if (a>0)
            this.speed = a + this.speed;
    }

    /**
     *
     */
    public void fullBreak() {
        this.speed = 0;
    }
}
