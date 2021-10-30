package Unive.vehicles;
import Unive.vehicles.animals.Carretto;
import Unive.vehicles.autovehicles.*;
import Unive.vehicles.fuel.*;

    public class Race {
    /**
     * @param v1 first vehicle
     * @param v2 second vehicle
     * @param length
     * @return the id of the winner or "-1" if there is a draw
     */
    public static int race (Vechicle v1, Vechicle v2, double length) {
        //qui viene invocato la versione di accellerate corretta sulla base della
        //classe di v1 e v2, se v1 è una bici viene usato accellerate di Bicycle!
        v1.fullBrake();
        v2.fullBrake();
        double distanceV1 = 0, distanceV2 = 0;
        if(v1 instanceof Loadable) //Senza interfaccia Loadable e implements, qui otterremmo degli errori -> non vede il tipo
            ((LoadableUnloadable) v1).unload();
        if(v2 instanceof Loadable) //Senza interfaccia Loadable e implements, qui otterremmo degli errori -> non vede il tipo
            ((LoadableUnloadable) v2).unload();
        if (v1 instanceof Printable) { //vediamo che qui stampa sse è Truck o Bicycle!
            ((Printable) v1).print();
        }
        if (v2 instanceof Printable) { //vediamo che qui stampa sse è Truck o Bicycle!
            ((Printable) v2).print();
        }
        if (v1 instanceof Car) { //ritorna vero se v1 è un istanza di Car o dei suoi sottotipi quindi anche Truck!
            // Se pero poniamo v1 instanceof Truck e v1 è Car allora
            // ritorna falso se v1 è Car!
            FuelTank three_lt = new FuelTank(((Car) v1).getFuelType(), 3);
            Car c1 = (Car) v1; //Questo funziona anche se Truck c1 = (Truck) v1
            c1.refuel(three_lt); // c1 potrebbe essere stesso tipo o sottotipo del tipo di v1
        }
        if (v2 instanceof Truck) {
            FuelTank three_lt = new FuelTank(((Truck) v2).getFuelType(), 3);
            Truck t2 = (Truck) v2;
            t2.refuel(three_lt);
        }
        while ((distanceV1 < length) && (distanceV2 < length)) {
            v1.accellerate(Math.random() * 10);
            v2.accellerate(Math.random() * 10);
            distanceV1 += v1.getSpeed();
            distanceV2 += v2.getSpeed();
        }
        if (distanceV1 >= length){
            if (distanceV2 >= length)
                return -1;
            else
                return 1;
        }
        else
            return 2;
    }
    public static void main(String[] args) {
        FuelType Diesel = new FuelType("Diesel", 0.01, 1.4);
        FuelTank two_lt = new FuelTank(Diesel, 2);
        Car C = new Car (Diesel);
        Bicycle B = new Bicycle(10, 1 ,1);
        Truck T = new Truck(Diesel);
        race(B,C, 100);
        Vechicle v1;
        Vechicle v2;
        if (Math.random() >= 0.5)
            v1 = C;
        else
            v1 = T;
        if (Math.random() >= 0.5)
            v2 = B;
        else
            v2 = T;
        race(v1,v2,10);
        //Non è detto che eseguendo il codice so che tipo dinamico ho
        /**
        Vechicle yourCar = new Car(Diesel);
        Car yourRealCar = (Car) yourCar;
        //Così eseguo il casting, ho tipo statico Vehicle e dinamico (che sappiamo e quindi possiamo fare il casting) Car
        //Posso fare un Veicolo e fare il casting a Car
        Vechicle myV = new Car(Diesel);
        Car myRealV = (Car) myV;
        //senza (Car) non potrei fare tale assegnamento!
        //NON POSSIAMO PERO' FARE, in quanto non posso fare un casting di una macchina a furgone
        //Un oggetto di tipo A non può essere castato ad B!
        Vechicle yourV = new Car(Diesel);
        Truck myRealT = (Truck) yourV;
        //Car non piò essere castata a Truck, ma viceversa si
        //Non è necessario fare casting del tipo pià basso, è possibile fare il castingdi qualsiasi livello intermedio
        //che sia compatibile, infatti
        Vechicle yourT = new Truck(Diesel);
        Truck yourRealT = (Truck) yourT;
        Car yourRealC = (Car) yourT;
        */
        //Questo mi da errore sia nella condizione dell'if (sempre falso) -> non c'è relazione di sottotipo
        // sia nell'assegnamento (codice morto->mai eseguito)
        if (v1 instanceof Bicycle)
            B = (Bicycle) v1;
        //Fare il casting di una superclasse in una sua superclasse è ridondante, in quanto la sottoclasse è sempre a
        //disposizione, come segue
        Car yourCar = new Car(Diesel);
        Vechicle v3 = (Vechicle) yourCar;
        Carretto myCarretto = new Carretto(0,0);
        race(myCarretto, B, 100);         //Senza aver specificato nella gerarchia dei tipi quale
                                                // classe implementa l'interfaccia loadable qui otterremo che nel metodo
                                                // dove c'è il controllo if (v1 instanceof Loadable) v1 NON viene visto
                                                //come Loadable -> Dobbiamo indicarlo! Lo indichiamo con implements!


    }


}
