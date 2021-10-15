package vehicles;
import vehicles.fuel.*;

/**
 * This class represents a car
 * @author Andrea Brion 860595
 * @since 1.0
 */
public class Car extends Vechicle {
       // Facendo car estensione posso levare i metodi in car che sono già presenti in Vehicle
       // Uso i metodi della classe dopo extends, basta vedere i suggerimenti facendo "myCar."
        private double speed = 0.0;
        private double fuel = 0.0;
        private FuelType fuelType = null;
        Car(FuelType f) {
            super(0);
            fuelType = f;
        }

    public Car() {
            super(0);
    }

    public boolean isFuelEmpty() {
            if (fuel <= 0) {
                super.fullBrake();
                return true;
            }
            else
                return false;
        }
        void refuel (FuelTank tank) {
            if (this.getFuelType().isCompatible(tank)) {
                fuel = fuel + tank.getAmount();
                tank.setAmount(0);
                // oppure possiamo usare tank.emptyTank();
            }

        }
        double computeConsumedFuel (double speedIncrease, double litresPerKmh) {
            return speedIncrease*litresPerKmh;
        }
        public FuelType getFuelType() {
        return fuelType;
    }
        //Porre set e get in una classe rende praticamente le variabili interessate public
        //Possiamo però porre dei controlli/regole nei set
        //ad esempio rendiamo impossibile porre una velocità negativa!
        //I set modificano lo stato dell'oggetto!
        public void setSpeed(double speed) {
            if (this.speed >= 0)
                this.speed = speed;
    }
        public double getFuelCons () {
        return this.fuelType.getCostPerLiter();
    }
        public static void main(String[] args) {
            Car myCar = new Car();
            FuelType diesel = new FuelType("Diesel", 0.01, 1.4);
            myCar.fuelType = diesel;
            diesel.setCostPerLiter(1.35);
            myCar.refuel(new FuelTank(diesel, 99999));
            myCar.accellerate(100);
            myCar.brake(90);
            System.out.println(myCar.fuel);
            System.out.println(myCar.speed);
            myCar.accellerate(50);
            System.out.println(myCar.speed);
            myCar.brake(100);
            System.out.println(myCar.speed);
        }
}

//Commenti : Devono essere strutturati ed esternamente visibili
//  	   Una volta compilato il codice, i commenti non si vedono più -> visibili solo nel sorgente
//	   // - /* */ -> Commenti interni, persi alla compilazione -> commenti che servono al creatore del codice o a chi ci lavorera, ma non servono all'utilizzatore della libreria
//	   /** */ -> Commenti simili agli interni, usati prima della dichiarazione di una classe, di un campo pubblico, di un metodo pubblico
//	   Javadoc -> costruiamo una documentazione apposita -> crea una pagina per classe
//	   	      Vedi slides
//	   	      Per costruire la pagina html dopo aver posto i commenti digitare da shell :
//		      "cd C:\Users\Utente\Desktop\Esami Settembre\Esami gennaio\Java Mod I\EserciziInClasse\L1\src>
//                       javadoc autovehicles.NomeClasse"
//	   	      Otteniamo così un file html nella directory del file sorgente
//		      Dall'idee però possiamo creare lo stesso file con più facilità (possiamo anche determinare che commenti rendere visibili o meno)
//	 	      Il file creato andrà nel file html di nome "index"
//	   	      Regole per commentare :
//              Commentare le tutto ciò che è public
//              Non commentare tutto ciòche è private
//              Decidere se commentare ciò che è protected/default