public class FuelTank {
    FuelType type;
    double amount;
    int id;      //supponiamo di voler dare un numero identificativo per ogni tanica creata e che la classe sappia quale numero progressivo dare (info condiviso dalla classe)
    static int counter = 0; // i campi statici NON VENGONO MESSI ALL'INTERNO DEGLI OGGETTI, i campi statici vengono inizializzati SOLO una volta (all'inizio del programma) e non condiviso da tutti
    //Per accedere a questi campi è : nomeClasse.nomeCampoStatico
    public FuelTank(FuelType type) {
        this.type = type;
        this.amount = 0.0;
        this.id = FuelTank.counter;
        FuelTank.counter++;
    }
    public FuelTank(FuelType type, double amount) {
        this.type = type;
        this.amount = amount;
        this.id = FuelTank.counter;
        FuelTank.counter++;
    }
    public FuelTank(double amount) {
        this.amount = amount;
        this.type = null;
        this.id = FuelTank.counter;
        FuelTank.counter++;
    }
    //Un metodo statico vede SOLO i campi e metodi statici della classe stessa
    static void resetTanksCount() {
        FuelTank.counter = 0;
    //Qui non potremmo fare
    //id = 5;
    //nemmeno
    //this.id = 5;
    }
}
