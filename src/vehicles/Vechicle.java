package vehicles;

public class Vechicle {
    private double speed = 0;
    /**
     * Accelerate the vehicle of the given amount of km/h
     *
     * @param a of speed in km/h. If there is not enough fuel, it accelerates as much as possible
     *
     * @requires amount >= 0
     * @ensures if (computeConsumedFuel(amount, fuelType.getLtperKmh());) < fuel -> speed = pre(speed) + amount
     * @ensures if (computeConsumedFuel(amount, fuelType.getLtperKmh());) >= fuel -> speed = pre(speed) + fuel/fuelType.getLitresPerKmh()
     */
    public void accellerate (double a){
        if (a>0)
            this.speed = a + this.speed;
    }
    public double getSpeed() {
        return speed;
    }
    public Vechicle(int initialSpeed) {
        this.speed = initialSpeed;
    }
    /**
     * Ferma il veicolo, non prende parametri e non ha tipo di ritorno
     * @author Andrea Brion
     * @since 1.0
     */
    public void fullBrake() {
        this.speed = 0;
    }
    /**
     * Questo metodo riduce la velocità di un veicolo di una quantità passata come parametro, non ritorna nulla
     * @param amount Sta per la quantità di velocità ridotta. Deve essere maggiore o uguale a 0 per creare effetto
     *               if and only if (amount >= 0)
     * @requires amount >= 0
     * @author Andrea Brion
     * @since 1.0
     */
    public void brake (double amount) {
        if (amount > speed)
            this.fullBrake();
        else
            speed = speed - amount;
    }


}
