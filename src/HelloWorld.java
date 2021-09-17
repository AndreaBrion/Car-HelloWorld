public class HelloWorld {
    public static void main (String[] args) {
            System.out.println("Hello "+args[0]);
    }
}


//Se dovessimo eseguire da linea di comando :
//Aprire terminale, per entrare nella cartella del file sorgente scrivere :
// cd C:\Users\Utente\Desktop\Esami Settembre\Esami gennaio\Java Mod I\EserciziInClasse\L1\src
//Per compilarlo :
//javac Helloworld.java
//Otteniamo così il file Helloworld.class
//Per eseguirlo (qui dobbiamo dare in input una parola visto "+args[0]"
//java HelloWorld pietro
//NB Quando indichiamo così l'esecuzione java esegue varie classi interne alla cartella, in futur vederemo come indicare cosa eseguire

// NB Scrivendo "java" nel terminale otteniamo delle istruzioni, ad esempio
//Uso: java [-opzioni] class [argomenti...]
//        (per eseguire una classe)
//        oppure  java [-opzioni] -jar filejar [argomenti...]
//        (per eseguire un file jar)
