public class FuelTank {
    FuelType type;
    double amount;
    public FuelTank(FuelType type) {
        this.type = type;
        this.amount = 0.0;
    }
    public FuelTank(FuelType type, double amount) {
        this.type = type;
        this.amount = amount;
    }
    public FuelTank(double amount) {
        this.amount = amount;
        this.type = null;
    }
}
