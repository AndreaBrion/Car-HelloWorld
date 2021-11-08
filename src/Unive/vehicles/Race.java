package Unive.vehicles;
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
        System.out.println("Race between vehicles");
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
    public static int race (Car v1, Car v2, double length) {
        System.out.println("Race between cars");
        return -1;
    }
    public static int race (Car v1, Vechicle v2, double length) {
        System.out.println("Race between a car and a vehicles");
        return -1;
    }
    public class Race<T extends Vechicle> {
        private final Vechicle v1,v2;
        public Race(Vechicle v1, Vechicle v2){
            
        }
    }
    public static void main(String[] args) {
        FuelTypeCache cache = new FuelTypeCache();
        FuelType Petrol = new FuelType("Petrol", 1.4, 0.01);
        FuelType Diesel = new FuelType("Diesel", 1.3, 0.015);
        FuelTank two_lt = new FuelTank(Diesel, 2);
        Car C = new Car (0, cache.getFuelTypeFromName("Diesel"));
        Car Y = new Car (0, cache.getFuelTypeFromName("Petrol"));
        Bicycle B = new Bicycle(10, 1 ,1);
        Truck T = new Truck(Diesel);

       /*
        Car winner = Race.new_race(C, Y, 700);  so che questo ritorna una Vechicle
        Però otteniamo errore in quanto avrei bisogno di un tipo di ritorno Car
        dato ciò cambiamo la firma del metodo, da :
        public static Vechicle new_race(Vechicle v1, Vechicle v2, double length)
        a :
        public static <T> T new_race(T v1, T v2, double length)
        Ovviamente cambiando il relativo corpo del metodo
        Questo funziona, però T potrebbe essere QUALSIASI COSA -> NON L'HO VINCOLATO AD ESSERE
        VECHICLE
        Possiamo però porre dei limiti ai tipi passati come Generics, e lo otteniamo con :
        public static <T extends Vechicle> T new_race(T v1, T v2, double length)
        Così indico che T deve essere un Vechicle o un suo sottotipo, infatti ora gli
        oggetti vedono i metodi getSpeed e fullBrake. Cosa che prima dava errore!
        Ora non otteniamo più errore con :
        Car winner = Race.new_race(C, Y, 700);
        Chiaramente se inseriamo come parametro un tipo che non è Vechicle o suo sottotipo
        otteniamo errore
        Posso anche specificare il tipo Generics
        Car winner = Race.<Vechicle>new_race(C, Y, 700);
        Qui l'invocazione del metodo funziona, ma il tipo di ritorno che verrà inserito in "winner"
        è un Vechicle -> Errore, in quanto abbiamo forzato la firma indicando Car
        Per non ottenere errori :
        Vechicle winner = Race.<Vechicle>new_race(C, Y, 700);

        */

    }


}
