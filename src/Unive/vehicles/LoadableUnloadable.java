package Unive.vehicles;

public interface LoadableUnloadable extends Loadable,Printable {double loadedCharge = 0;
     public default double unload() {
        double value = this.getLoad(); //vede getLoad e setLoad in quanto sottoclasse!
        this.setLoad(0);                //così come vede il metodo chargeLoad
        return value;
    }
}
