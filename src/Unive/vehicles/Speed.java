package Unive.vehicles;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
    //Così poniamo il livello durante l'esecuzione
    @Retention(RetentionPolicy.RUNTIME)
    /*@Target è un'annotazione in cui possiamo definire un array di ElementType (tipo, campo, metodo,
    parametro, costruttore e variabile locale)
    * */
    @Target({
            ElementType.METHOD,
            ElementType.FIELD,
            ElementType.PARAMETER
    })
    /*Così definita non possiamo porre @Speed su una classe!
    * */
    //si scrive @interface ma si legge : annotations
    public @interface Speed {
        //Diamo un valore di default, essendo in Italia : Km/h
        String type() default "Km/h";
        boolean forward();
    }
