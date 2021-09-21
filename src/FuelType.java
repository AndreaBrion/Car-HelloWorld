public class FuelType {
    String type;
    //Possiamo Indicare che un campo sia non modificabile attraverso final, però lo posso assegnare sempre e solo una volta nel costruttore
    final double ltperKmh;
    double costPerLiter;
    double FUEL_CONS;
    //NB costruttore : E' il Primo metodo invocato in una classe e viene eseguito SOLO una volta
    //quindi se voglio invocare un costruttore da un altro metodo, posso invocare un costruttore SOLO da un altro costruttore (Ciò è possibile
    //SOLO se fatto come prima istruzione
    //Creiamo un costruttore
    FuelType(String t, double c, double l) {
        this.type = t;
        this.costPerLiter = c;
        this.ltperKmh = l;
    }
    //Creiamo un costruttore che prende solo 2 parametri, come faccio ad inizializzare pure ltperKmh?
    //Uso this! This infatti può essere usato anche per invocare un costruttore in un altro costruttore
    FuelType(String t, double c) {
        this.ltperKmh = 0.0;
        type = t;
        costPerLiter = c;
    }

}





