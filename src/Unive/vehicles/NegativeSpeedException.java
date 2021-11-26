package Unive.vehicles;

public class NegativeSpeedException extends Exception {
    public NegativeSpeedException(double i) {
        super("Hai inserito un valore negativo, non è possibile accellerare un veicolo di " + i);
    }
}
