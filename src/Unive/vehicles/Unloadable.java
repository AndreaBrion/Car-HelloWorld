package Unive.vehicles;

public interface Unloadable {

    default public double unload() {
        double value = this.getLoad();
        this.setLoad(0.0);
        return value;
    }
    void setLoad(double amount);
    double getLoad();
}
