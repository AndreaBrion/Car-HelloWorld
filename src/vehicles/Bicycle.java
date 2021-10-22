package vehicles;

public class Bicycle extends Vechicle{
    private final double frontTire, rearTire;
    public double frontTirePressure () {
        return this.frontTire;
    }
    public double rearTirePressure () {
        return this.rearTire;
    }
    public Bicycle (int initialSpeed, int frontTire, int rearTire) {
        super(initialSpeed);
        this.rearTire = rearTire;
        this.frontTire = frontTire;
    }
    public Bicycle (int initialSpeed) {
        super(initialSpeed); //Se non lo mettessimo Java mette da solo (automaticamente il supercostruttore senza parametri) -> Importante esplicitarlo per non avere ambiguità semantiche del codice
        //Se avessimo un costruttore che ne richiama un altro il quale deve chiamare un super costruttore cosa pongo prima?
        //prima this o super?
        //Nessuno dei due, POSSO AVERNE SOLO UNO -> devo scegliere quale dei due
        //Nella seconda riga non può starci ne un super costruttore ne un altro costruttore!
        this.rearTire = 0;
        this.frontTire = 0;
    }
    /**
     *
     */
    public void accellerate (double a){
        if (a>0)
            this.setSpeed(a);
    }
    /**
     *
     */
    public void fullBreak() {
        this.setSpeed(0);
    }
}
