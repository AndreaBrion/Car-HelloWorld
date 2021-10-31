package Unive.vehicles;

public interface LoadableUnloadable extends Loadable,Unloadable { //Questa interfaccia estende entrambe le interfacce
                                                                // ciò significa che eredito unload e chargeLoad
    /*ciò fa si che eredito
    default public double unload() {
      double value = this.getLoad(); //vede getLoad e setLoad in quanto sottoclasse!
      this.setLoad(0);
      return value;
    }
    void setLoad(double amount);
    double getLoad();

    default public void chargeLoad (double amount) {
        if (amount > 0)
            this.setLoad(this.getLoad() + amount);
    }
    void setLoad(double amount);
    double getLoad();
    */
    //IMPORTANTE se una classe eredita 2 volte una default implementation un metodo da due interfacce diverse -> errore compilazione
    //Facendo così però non otterrei errore!!
    //Per non averlo quindi basta estendere due interfacce con una!
}
