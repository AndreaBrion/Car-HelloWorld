package Unive.vehicles;

public class InconsistentSpeedException extends Throwable {
    public InconsistentSpeedException() {
        super("Il veicolo ha una velocità di base che non ha senso");
    }
}
