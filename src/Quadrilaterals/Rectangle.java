package Quadrilaterals;

/**
 * Classe che rappresenta un rettangolo
 * @param "W e H stanno per larghezza ed altezza"
 */
public class Rectangle {
    private int w,h;
    /*aggiunto questo costruttore per far funzionare il costruttore nella classe estesa Square (la quale usa il costruttore
    * della superclasse Rectangle*/
    public Rectangle() {
        this.w = 0;
        this.h = 0;
    }
    public Rectangle(int w, int h) {
        this.w = w;
        this.h = h;
    }
    public int getArea() {
        return this.h*this.w;
    }
}
