package autovehicles.fuel;
/**
 * This class represents a type of tank
 * @author Andrea Brion 860595
 * @since 1.0
 */
public class FuelTank {
    private FuelType type;
    private double amount;
    private int id;
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
    public FuelType getType() {
        return this.type;
    }
    public double getAmount() {
        return this.amount;
    }
    public int getId() {
        return this.id;
    }
    public void setAmount(double amount) {
        this.amount = amount;
    }
    public void emptyTank() {
        this.amount = 0;
    }
}
