package Unive.vehicles;

public class ExtendedRace extends Race {
    public static int race(Vechicle v1, Vechicle v2, double lenght){
        System.out.println("Racing in the esxtended version");
        return Race.race(v1, v2, 100);
        //Se facessimo :
        //return super.race(v1, v2, lenght);
        //Otterremo errore!
        //super qui non è visibile, in quanto super è un puntatore all'oggetto che rappresenta la superclasse
        //siamo dentro ad un metodo statico e non un'istanza -> super non disponibile
        //QUANDO DOBBIAMO INVOCARE UN METODO IN UNA SUPERCLASSE USO IL NOME DELLA SUPERCLASSE COME RECEIVER!
    }
}
