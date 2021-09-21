public class FuelType {
    String type;
    double ltperKmh;
    double costPerLiter;
    //Qui notiamo che ltperKmh fa divenire ridondante "double ltperKmh" e quindi possiamo levarlo dalla
    //classe car
    double FUEL_CONS;

    //Creiamo un costruttore
    FuelType(String t, double c, double l) {
        type = t;
        costPerLiter = c;
        ltperKmh = l;
    }
}
