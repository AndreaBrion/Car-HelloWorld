package Unive.vehicles;

public interface Loadable {

    default public void chargeLoad (double amount) {
        if (amount > 0)
            this.setLoad(this.getLoad() + amount);
    }
    void setLoad(double amount);
    double getLoad();
}
