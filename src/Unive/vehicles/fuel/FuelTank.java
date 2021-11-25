package Unive.vehicles.fuel;

import java.util.HashSet;
import java.util.Set;

/**
 * This class represents a type of tank
 * @author Andrea Brion 860595
 * @since 1.0
 */
public class FuelTank implements Comparable<FuelTank> {
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
    @Override
    public boolean equals(Object obj) {
        if (obj instanceof FuelTank) {
            FuelTank o = (FuelTank) obj;
            return ((this.amount == o.amount) //&& (this.id == o.id)
                    && (this.getType().equals(o.getType())));
        }
        else
            return false;
    }

    @Override
    public int compareTo(FuelTank o) {
        if (this.equals(o)) //se i 2 oggetti sono uguali deve ritornare 0
            return 0;
        else if (this.amount != o.amount) //Se i 2 oggetti sono uguali avranno stesso amount e type
            return (int) (this.amount-o.amount);
        else //se abbiamo stesso amount di carburante
            return this.type.compareTo(o.type); //questo non funziona se prima non lo definiamo su FuelType

    }
}
