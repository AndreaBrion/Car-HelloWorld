package Unive.util;

import Unive.vehicles.Bicycle;
import Unive.vehicles.Vechicle;
import Unive.vehicles.autovehicles.Car;
import Unive.vehicles.autovehicles.Truck;
import Unive.vehicles.fuel.FuelType;

import javax.sound.sampled.BooleanControl;
import java.util.Arrays;
import java.util.Vector;

public class List<V> {
    private V[] elements;
    public void add(V el) {
        int n = elements.length+1;
        elements = Arrays.copyOf(elements, n); //Supponendo l'array sia pieno aggiungo i suoi elementi
        elements[n-1] = el; // alla fine dell'array, l'assegnamento come funziona ?
    } // "el" è il nostro parametro di tipo V, elements è un campo ed è un array di tipo V
    public boolean contains(V el) {
        for(int i=0; i < elements.length; i++)
            if(elements[i]==el)
                return true;
        return false;
    }
    public V get(int i) { //Qui so già che ritornerà un qualcosa di tipo V
        return elements[i];
    }
    public static <T> List <T> toList(T value) { //il tipo T esiste solo nel metodo toList() non appartiene alla classe
    //definito così possiamo usarlo anche all'interno del metodo e non solo nella firma
        List<T> result = new List<T>(); //creo una variabile result : Lista di tipo <T>
        result.add(value);
        return result;
    }
    public static <T> T getFirst(List<T> list) {
        return list.get(0);
    }
    public static void main(String[] args) {
      /*
       List<Vechicle> listV = new List<Vechicle>();
        List<Bicycle> listB = new List<Bicycle>();
        List<Vechicle> listV2 = new List<Vechicle>();
        listV2 = listB; Qui otteniamo un errore durante compilazione
       Immaginiamo possa assegnare una lista di Bicycle ad una lista di Vehicle.
          Bicycle b = listB.get(0); Su una lista di veicoli posso fare il put() di un qualsiasi veicolo, in quanto :
          listB.add(new Bicycle(0)); la lista è parametrizzata sul tipo V, il tipo V è Vechicle,
                                     e passando ad esso un sottotipo di Vechicle funziona.
                                     I problemi nascerebbero qui
          listV2.add(new Car(0,new FuelType("Petrol", 1.4, 0.01)));
          Il problema però è che sto aggiungendo un Vechicle su una lista di Vechicle, ma se avessi
          assegnato ad una lista di Vechicle una di Bicycle starei assegnando un Vechicle (Car o Truck) ad una Bicycle -> ERRORE

          Stesso errore se
          listB = listV2;
          Se volessi estrarre una bici
          Bicycle b1 = listB.get(0);
          Se la mia lista di bici è una lista di Vechicle posso aggiungere un sottotipo di Vechicle
          alla lista -> ERRORE

          Se avessimo lo stesso approccio non potremmo assegnare un ad un array di Vechicle uno di Bicycle, qui però funziona
          Non possiamo fare il contrario :

          Vechicle[] v = new Vechicle[1];
          Bicycle[] b = new Bicycle[1];
          v[0] = new Car(0, new FuelType("Petrol",1.4, 0.01));
          b[0] = new Bicycle(0);
          //Infatti posso fare :
          v = b;
          ma non posso fare :
          b = v;
          L'idea qui è il concetto di covarianza
          Gli array in Java sono COVARIANTI, cioè ad un array di tipo A posso assegnare un array
          di tipo N sse il tipo statico di N è sottotipo del valore assegnato (cioè di A)
          Vediamo alcuni problemi dato ciò :

          v = b;
          v[0] = new Car(0, new FuelType("Petrol",1.4, 0.01)); // qui otterrei ERRORE
          Bicycle b1 = b[0]; // qui so che sto estraendo una Bicycle

        L'errore indica che non possiamo porre una Car in v[0], in quanto v[] è un array di Bicycle dove il tipo statico è Vechicle mentre
        il dinamico di Bicycle
        L'errore sta nel voler porre un elemento Car in un array di Bicycle!


          //dato il metodo toList() se voglio costruire una lista di macchine
        // con la macchina costruita in precedenza :
          List<Car> c = List.<Car>toList(new Car(0,new FuelType("Petrol", 1.4, 0.01)));
          //Notiamo come invochiamo toList : Lista.<Tipo>METODO()
         //Così facendo la firma del metodo è da interpretare :
        //List<Car> toList(Car value)
        //Tutto ciò funzionava anche se al posto di Car usavamo Truck
        //Non possiamo però mischiare i tipi! Es :
  // List<Car> c = List.<Truck>toList(new Car(0,new FuelType("Petrol", 1.4, 0.01))); -> ERRORE
        */
//quando dichiaro una variabile devo passargli il tipo statico, dichiarando una variabile Generics devo specificare il Generics
//quando istanzio un Generics posso lasciare il controllo al compilatore (<?>), quando dichiaro il tipo devo indicare il Generics
        List<Car> v = new List<Car>();
        List<?> q = v; //qui infatti posso specificare (List<Car>) o porre una wildcard (List<?>)
//Una wildcard è un jolly -> lo usiamo dove siamo obbligati a specificare esplicitamente un tipo
//Volendo possiamo i Bound anche sulle wildcard
        List<? extends Vechicle> w = v; //indico di assegnare v a w (w è Vechicle o sottotipo di Vechicle)
//Indico che non so cos'è w, l'unica cosa che so è che è un Vechicle o un suo sottotipo, se io invece
//      List<Vechicle> w1 = v;
//NON FUNZIONA in quanto v è una lista di Car! -> INVARIANZA DI TIPI -> non possiamo assegnare una lista
//di Vechicle ad una lista di Car
//Ponendo List<? extends Vechicle> w = v, il compilatore dato il tipo di v lo interpreta come :
//      List<Car extends Vechicle> w = v;
//Siccome Car è un sottotipo l'assegnamento funziona
//Per capirci, durante l'esecuzione il compilatore nel "?" ci pone il tipo di v
//Infatti eseguendo siamo sicuri di ottenere un veicolo
        Vechicle e = w.get(0);
//il get ritornerà v in quanto definito come "public V get(int i)" e questo v altro non è che la wildcard che estende Vechicle
//NB Nel momento in cui una wildcard estende un Vechicle, quel qualcosa è un supertipo di una wildcard che estende una Car
//      Car e = q.get(0); allora qui otterremo Errore, stessa cosa se facessimo Car e = q.get(0);
//Non possiamo però aggiungere una Car ad una lista di Vechicle :
//      w.add(new Car(0, new FuelType("Petrol",1.4, 0.01))); // error
//Invece se ad una lista di Vechicle aggiungiamo un Truck o Car -> funziona
        v.add(new Car(0,new FuelType("Petrol",1.4,0.01)));
        v.add(new Truck(new FuelType("Petrol",1.4,0.01)));
//Perchè w.add non funziona? Perchè da List<? extends Vechicle> w = v; so che w è una lista di tipo Generics
//che estende Vechicle quindi potrebbe essere una lista di Vechicle,Car,Truck,Bicycle. Quando provo a fare w.add
//add prende un tipo V (che potrebbe essere Vechicle,Car,Truck,Bicycle), passando una Car ad w.add il compilatore
//da errore in quanto non sa di che tipo è la lista, potrebbe essere di un tipo diverso dall'oggetto inserito
        
    }

}