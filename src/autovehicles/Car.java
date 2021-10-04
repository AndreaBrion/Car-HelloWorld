package autovehicles;
import autovehicles.fuel.*;

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
        public void brake (double amount) {
            if (amount > speed)
                this.fullBrake();
            else
                speed = speed - amount;
        }
        public void fullBrake() {
            this.speed = 0;
        }
        double computeConsumedFuel (double speedIncrease, double litresPerKmh) {
            return speedIncrease*litresPerKmh;
        }
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


//Visto i concetti di classi, oggetti vediamo :
//Vediamo come strutturare il codice in modo da nascondere la nostra implementazione.
//Incapsulamento e information hiding
//
//Una classe definisce un contratto, un contratto concettualmente quando prendi una classe e la istanzi puoi fare tot cose (lo sviluppatore "promette"
//che l'utilizzatore potrà fare un tot azioni" -> come un contratto)
//Parlando di contratti dobbiamo precisare però che stiamo astraendo (non stiamo andando a vedere COME è fatto il metodo X, a me interessa si possa vedere
//la firma del metodo)
//L'idea è di astrarre tutto ciò che è implementazione e ci focalizziamo sulle componenti importanti per l'utilizzo concettuale della classe (per istanziarla
//e utilizzarla)
//Importante : commentare le funzioni in modo che un'altra persona debba indagare il codice per capire cosa fa un metodo
//L'incapsulamento
//Quando creaimo/pensiamo-ad una classe dobbiamo creare un'entità logica coerente :
//ES ho una macchina con n litri di benz, con una velocità e un tipo di carburante (ciascuno incrementa la velocità in modo diverso)
//   La classe va strutturata in modo che i dati e le funzionalità coerenti con se stesse siano all'interno della classe
//   Qui speed, fuel staranno in car. Il tipo di carburante starà in un'altra classe in quanto più macchine usano il carburante e concettualmente
//   "quanto km/h incremento per litro di carburante appartiene all'unità concettuale di "Tipo di carburante" "
//In questa idea i dati vengono messi insieme o impacchettati alle funzionalità che lavorano con questi dati.
//L'obiettivo è sia che non vi sia codice duplicato, ma principalmente l'idea di incapsulamento significa "tutta l'unità è chiusa in sè stessa", quindi con un
//buon incapsulamento modo riusciamo ad massimizzare l'informazione che nascondiamo
//Quei campi non voglio che qualcuno possa modificarli -> voglio ad esempio che venga usato accelerate per accellerare e non che un utente possa cambiare la sua velocità
//solo modificando il campo speed (così mantengo una coerenza logica con la mia implementazione -> in modo che la benzina venga ridotta)
//Dobbiamo nascondere tutto ciò su cui noi vogliamo avere un controllo e tutto quello che non interessa all'utilizzatore -> così manteniamo una coerenza logica
//Qual è lo scopo finale?
//Lo scopo finale dell'incapsulamento è avere uno stato di ciascun oggetto coerente, inoltre l'uso dei package comporta un buon uso di incapsulamento
//capsula può avere da una classe ad un package o più
//Inizialmente il metodo accelerate aveva solo la sottrazione del carburante, in seguito
//abbiamo fatto in modo che il carburante non potesse andare sotto 0 (quantità) in quanto non avrebbe molto senso
//
//Oggi vediamo i modificatori d'accesso
//Nascondere l'informazione è importante perchè :
//	-Così quando vedo una classe e vedo l'informazione esposta vedo solo ciò che lo sviluppatore considerò interessante
//	-Limitare l'accesso a componenti della classe quindi oggetti che andiamo ad istanziare -> importante non solo per la consistenza logica, ma anche per
//												  le dipendenze (Nell'evolversi il codice potrebbe aver necessità
//												  che dei valori ad esempio velocità mantenga altre info ad esempio
// 												  l'unità di misura usata (mp/s o km/h ecc) -> così riduciamo la dipendenza
//											   	  fra i moduli e anche fra versioni successive di codice)
//
//La regola d'oro per questo è : Chi usa il mio codice (il client) deve avere accesso a tutto ciò di cui ha bisogno e non deve vedere accesso a nulla di cui non gli serva
//
//Private -> visto, scritto e acceduto solo dalla classe
//Protected -> visto, scritto e acceduto classi,sottoclassi e package
//Default -> come protected solo che non per le sottoclassi
//Ma cosa significa quando definisco una variabile private o default?
//Significa che ho viste diverse dell'istanza della classe in base a dove mi trovo.
//Come decidiamo se uno o gli altri?
//Public -> tutto quello che c'è nel contratto
//Private -> tutto ciò che è privato (dettagli implementativi interni)
//NB Se ho due classi con un campo con stesso nome ho un conflitto di nomi se invoco il campo? No per accedere al campo devo
//   accedere all'oggetto!
//   I campo NON sono variabili globali, in quanto sono sempre all'interno all'interno di un blocco classe, quindi dentro un oggetto!
//
//Il package è un unità coerente che contiene tutte le implementazioni che riguardano le macchine.
//
//L'uso dei get ha un grosso contro : Quando eseguo una chiamata ad un get, viene creto spazio nello stack per i parametri ecc... Questo ha un peso
//computazionale diverso dall'accedere semplicemente un campo! -> svantaggio se ho un programma dove ho risorse computazionale limitata
//Linguaggi ad oggetti poco indicato per soluzioni con risorse computazionali, sono invece ottimi per quando abbiamo tante risorse
//computazionali e applicazioni che implementano una logica complessa, ma riusano tante librerie

