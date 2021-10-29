package vehicles;

public interface Loadable {
    double loadedCharge = 0;
    /**
  * se implementassimo così, otterremmo errore su this.loadedCharge
    in quanto i campi definiti in un'interfaccia sono implicitamente FINAL E STATIC
    tecnicamente sono variabili dell'interfaccia!
    default void chargeLoad (double amount) {
        this.loadedCharge += amount;
    }
    default double unload(){
        this.loadedCharge = 0.0;
        return 0;
    }
     Tecnicamente all'interno di un'interfaccia NON POSSO avere campi, posso però porci metodi
    */
    /**Quindi o lasciamo così -> da implementare nelle sottoclassi
    public void chargeLoad(double amount);
    public double unload();
     */
    //Oppure usiamo i setter e getter
    default public void chargeLoad (double amount) {
        if (amount > 0)
            this.setLoad(this.getLoad() + amount);
    }
    default public double unload () {
        double value = this.getLoad();
        this.setLoad(0);
        return value;
    }
    void setLoad(double amount);
    double getLoad();


    //IMPORTANTE : Se definisco un metodo qui dentro DEVE essere pubblico!
}
