public class FuelTank {
    FuelType type;
    double amount;
    int id;
    static int counter = 0;
    public FuelTank(FuelType type) {
        this.type = type;
        this.amount = 0.0;
        this.id = FuelTank.counter;
        FuelTank.counter++;
    }
    public FuelTank(FuelType type, double amount) {
        this.type = type;
        this.amount = amount;
        this.id = FuelTank.counter;
        FuelTank.counter++;
    }
    public FuelTank(double amount) {
        this.amount = amount;
        this.type = null;
        this.id = FuelTank.counter;
        FuelTank.counter++;
    }
    static void resetTanksCount() {
        FuelTank.counter = 0;
    }
}
