package autovehicles;
import autovehicles.fuel.*;

/**
 * This class represents a car
 * @author Andrea Brion 860595
 * @since 1.0
 */
public class Car {
       //I campi di Car sono  variabili che servono solo alla classe Car -> information hiding
       //abbiamo lascaito solo il fueltype visibile fuori dalla classe Car
       //I metodi di Car però servono -> information hiding non significa porre tutto private
        private double speed = 0.0;
        private double fuel = 0.0;
        private FuelType fuelType = null;
        Car(FuelType f) {
            fuelType = f;
        }
        Car() {
        }
        void refuel (FuelTank tank) {
            if (this.getFuelType().isCompatible(tank)) {
                fuel = fuel + tank.getAmount();
                tank.setAmount(0);
                // oppure possiamo usare tank.emptyTank();
            }

        }

    /**
     * Questo metodo riduce la velocità di una quantità passata come parametro, non ritorna nulla
     * @param amount Sta per la quantità di velocità ridotta. Deve essere maggiore o uguale a 0 per creare effetto
     *               if and only if (amount >= 0)
     * @requires amount >= 0
     * @author Andrea Brion
     * @since 1.0
     */
    public void brake (double amount) {
            if (amount > speed)
                this.fullBrake();
            else
                speed = speed - amount;
        }

    /**
     * Ferma la macchina, non prende parametri e non ha tipo di ritorno
     * @author Andrea Brion
     * @since 1.0
     */
    public void fullBrake() {
            this.speed = 0;
        }
        double computeConsumedFuel (double speedIncrease, double litresPerKmh) {
            return speedIncrease*litresPerKmh;
        }

    /**
     * Accelerate the car of the given amount of km/h
     *
     * @param amount of speed in km/h. If there is not enough fuel, it accelerates as much as possible
     *
     * @requires amount >= 0
     * @ensures if (computeConsumedFuel(amount, fuelType.getLtperKmh());) < fuel -> speed = pre(speed) + amount
     * @ensures if (computeConsumedFuel(amount, fuelType.getLtperKmh());) >= fuel -> speed = pre(speed) + fuel/fuelType.getLitresPerKmh()
     */
    public void accellerate (double amount) {
            double fuelCons = computeConsumedFuel(amount, fuelType.getLtperKmh());
            if (fuelCons < fuel) {
                speed = speed + amount;
                fuel = fuel - amount* fuelType.getCostPerLiter();
            }
            else {
               double increaseSpeed = fuel / fuelType.getCostPerLiter();
                speed = speed + increaseSpeed;
                fuel = 0;
            }
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