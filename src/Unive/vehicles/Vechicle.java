package Unive.vehicles;

public class Vechicle { //astratta visto il metodo accelerate
    private double speed = 0;
    /**
     * Accelerate the vehicle of the given amount of km/h
     *
     * @param a of speed in km/h. If there is not enough fuel, it accelerates as much as possible
     *
     * @requires amount >= 0
     * @throws NegativeSpeedException se la quantità di accellerazione data è negativa
     * @ensures if (computeConsumedFuel(amount, fuelType.getLtperKmh());) < fuel -> speed = pre(speed) + amount
     * @ensures if (computeConsumedFuel(amount, fuelType.getLtperKmh());) >= fuel -> speed = pre(speed) + fuel/fuelType.getLitresPerKmh()
     */
    //Per far sì che possa lanciare eccezioni dobbiamo indicarlo
    //Dobbiamo indicare la stessa cosa per OGNI CLASSE CHE POTREBBE LANCIARE UN ECCEZIONE USANDO ACCELLERATE!
    //Quindi : Race, Car, Truck, VechicleHashSet, ExtendedRace
    public void accellerate (double a) throws ImpossibleAccellerateException {
        if (this.speed < 0)
            throw new InconsistentSpeedException();
        if (a >= 0)
            this.speed += a;
        else
            throw new NegativeSpeedException(a);
        // potevamo usare anche this.setSpeed(a);
        // Però super.speed è più corretta!
    }

    /*
    * Avremo :
    * @Speed(forward = true, type = "Kp/h")
    * public double getSpeed() {return speed;}
    * così fa da commento strutturato
    */
    public double getSpeed() {
        return speed;
    }
    protected void setSpeed(double speed) {
        if (speed >= 0)
            this.speed = speed;
    }
    public Vechicle(@Speed(forward = true) double initialSpeed) {
        this.speed = initialSpeed;
    } // questo costruttore non viene invocato dalla classe Unive.vehicles
      // ma vengono invocate dalle sottoclassi!

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

    /*
    Qui avremo :
    public void brake (@Speed(forward = false) double amount) {
    forward = false per dare l'idea di che la velocità è negativa -> stiamo frenando
    * */
    public void brake (double amount) {
        if (amount > speed)
            this.fullBrake();
        else
            speed = speed - amount;
    }


}
