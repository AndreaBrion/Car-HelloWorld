package Unive.vehicles;

public interface Loadable2 {double loadedCharge = 0;
    default public void chargeLoad (double amount) {
        if (amount > 0)
            this.setChargedLoad(this.getChargedLoad() + amount);
    }
    default public double unload () {
        double value = this.getChargedLoad();
        this.setChargedLoad(0);
        return value;
    }
    //aggiungo "Charged" in modo da avere metodi identici nelle due interfacce (stessa firma) ma con implementazioni diverse
    void setChargedLoad(double amount);
    double getChargedLoad();
}
