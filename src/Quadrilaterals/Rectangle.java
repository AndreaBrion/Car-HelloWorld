package Quadrilaterals;

/**
 * Classe che rappresenta un rettangolo
 * @param "W e H stanno per larghezza ed altezza"
 */
public class Rectangle extends Quadrilateral{
    /*aggiunto questo costruttore per far funzionare il costruttore nella classe estesa Square (la quale usa il costruttore
    * della superclasse Rectangle*/
    public Rectangle(int w, int h) {
        super(w,w,h,h);
    }
    public double getH() {
        return super.getEdge3();
    }
    public double getW() {
        return super.getEdge1();
    }
    public double getA() {
        return this.getH()*this.getW();
    }
}
