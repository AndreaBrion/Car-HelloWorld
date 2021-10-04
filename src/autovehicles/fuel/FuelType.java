package autovehicles.fuel;
import autovehicles.Car;

public class FuelType {
    String type;
    private final double ltperKmh;
    private double costPerLiter;
    double FUEL_CONS;
    public FuelType(String t, double c, double l) {
        this.type = t;
        this.costPerLiter = c;
        this.ltperKmh = l;
    }
    FuelType(String t, double c) {
        this.ltperKmh = 0.0;
        type = t;
        costPerLiter = c;
    }
    public boolean isCompatible(FuelTank tank) {
        //qui c'è un problema concettuale, in refule abbiamo la macchina che prende fuleType della tanica e passa se stessa al fuelType
        //fuelType ha bisogno di vedere un dettagli implementativo interno della Macchina
        //Se passassi un parametro Car ciò diventa ambiguo : è la macchina che chiede se la tanica è compatibile oppure è la macchina
        //chiede se il tipo di carburante con cui fa rifornimento è compatibile con quello della tanica?
        return tank.getType().equals(this.type);
    }
    public String getType() {
        return this.type;
    }
    public double getLtperKmh() {
        return this.ltperKmh;
    }
    public double getCostPerLiter() {
        return this.costPerLiter;
    }
}







