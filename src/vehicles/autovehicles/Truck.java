package vehicles.autovehicles;
import vehicles.autovehicles.Car;
import vehicles.fuel.FuelType;

public class Truck extends Car {
    private double speed;
    private double loadedCharge = 0.0;
    private double fuel = 0;
    private final FuelType fuelType;
    public Truck (FuelType f){
        this.fuelType = f;
    }
    public void accellerate(double a) {
        // qui 1000 sono per dare una percentuale!
        double fuelConsumed = computeConsumedFuel(a, fuelType.getLtperKmh());
            super.accellerate(a*(1+loadedCharge/1000));
        // Se usassimo this.accellerate() otterrei un loop di ricorsioni! in quanto
        // viene invocato sempre lo stesso metodo sul truck! cioè viene invocato il metodo
        // della classe dell'oggetto chiamante!
        //Occhio che se ponessi solo accellerate() java sceglierebbe il metodo col this!
            super.setSpeed(super.getSpeed()-a*loadedCharge/1000);
    }
    public void chargeLoad (double l) {
        if (l > 0 )
            this.loadedCharge += l;
    }
    public double unload (double u) {
        if (u > 0) {
            double value = this.loadedCharge;
            this.loadedCharge = 0.0;
            return value;
        }
        return 0.0;
    }
    public void setSpeed(double speed) {
        if (this.speed >= 0)
            this.speed = speed;
    }
    /*Questo metodo diventa una PROPRIETA' DEL NOSTRO OGGETTO MACCHINA, proprietà
    * derivata da un oggetto aggregato all'interno della mia macchina.
    * Lo immagazzino all'interno dello stato della mia macchina come un campo in
    * modo da usarlo a piacere
    */
    /*Per il solito discorso della parola super, qui con super. posso accedere a tutti i metodi sopra (quelli di
    * car e quelli di vehicle!
    * */
    public boolean isFuelEmpty(){
        return super.isFuelEmpty();
    }

}
