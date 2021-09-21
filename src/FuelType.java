public class FuelType {
    String type;
    double ltperKmh;
    double costPerLiter;
    //Qui notiamo che ltperKmh fa divenire ridondante "double ltperKmh" e quindi possiamo levarlo dalla
    //classe car
    double FUEL_CONS;
    //NB costruttore : E' il Primo metodo invocato in una classe e viene eseguito SOLO una volta
    //quindi se voglio invocare un costruttore da un altro metodo, posso invocare un costruttore SOLO da un altro costruttore (Ciò è possibile
    //SOLO se fatto come prima istruzione
    //Creiamo un costruttore
    FuelType(String t, double c, double l) {
        type = t;
        costPerLiter = c;
        ltperKmh = l;
    }
    //Creiamo un costruttore che prende solo 2 parametri, come faccio ad inizializzare pure ltperKmh?
    //Uso this! This infatti può essere usato anche per invocare un costruttore in un altro costruttore
    FuelType(String t, double c) {
        this.ltperKmh = 0.0;
        type = t;
        costPerLiter = c;
    }

}

//Se volessimo indicare in modo + chiaro i parametri non dovremmo porre lo stesso nome dei campi della classe -> così rischieremmo di nasconderli!
//Java da adisposizione una parola chiave THIS.
///    FuelType(String type, double costPerLiter, double ltperKmh) {
//        this.type = type;
//        this.costPerLiter = costPerLiter;
//        this.ltperKmh = ltperKmh;
//      }
//This rappresenta un puntatore all'oggetto che chiama il metodo! (detta istanza corrente dell'oggetto)
//



