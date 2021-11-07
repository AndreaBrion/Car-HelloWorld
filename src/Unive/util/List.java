package Unive.util;

import java.util.Arrays;

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
}