package Unive.vehicles;

public interface Loadable {
    double loadedCharge = 0;
    default public void chargeLoad (double amount) {
        if (amount > 0)
            this.setLoad(this.getLoad() + amount);
    }
    void setLoad(double amount);
    double getLoad();

    //IMPORTANTE : Se definisco un metodo qui dentro DEVE essere pubblico!
}
