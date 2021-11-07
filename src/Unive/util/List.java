package Unive.util;

import Unive.vehicles.Bicycle;
import Unive.vehicles.Vechicle;
import Unive.vehicles.autovehicles.Car;
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
    public static void main(String[] args) {
       /* List<Vechicle> listV = new List<Vechicle>();
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
*/
          /*
          Se avessimo lo stesso approccio non potremmo assegnare un ad un array di Vechicle uno di Bicycle, qui però funziona
          Non possiamo fare il contrario
          */
          Vechicle[] v = new Vechicle[1];
          Bicycle[] b = new Bicycle[1];
          v[0] = new Car(0, new FuelType("Petrol",1.4, 0.01));
          b[0] = new Bicycle(0);
          //Infatti posso fare :
          v = b;
          /* ma non posso fare :
          b = v;
          L'idea qui è il concetto di covarianza
          Gli array in Java sono COVARIANTI, cioè ad un array di tipo A posso assegnare un array
          di tipo N sse il tipo statico di N è sottotipo del valore assegnato (cioè di A)
          Vediamo alcuni problemi dato ciò :
           */
          v = b;
          v[0] = new Car(0, new FuelType("Petrol",1.4, 0.01)); // qui otterrei ERRORE
          Bicycle b1 = b[0]; // qui so che sto estraendo una Bicycle
        /*
        L'errore indica che non possiamo porre una Car in v[0], in quanto v[] è un array di Bicycle dove il tipo statico è Vechicle mentre
        il dinamico di Bicycle
        L'errore sta nel voler porre un elemento Car in un array di Bicycle!
          * */
    }

}