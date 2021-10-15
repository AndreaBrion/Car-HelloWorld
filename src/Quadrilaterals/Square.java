package Quadrilaterals;

public class Square extends Rectangle{ // quadrato E' un rettangolo! RELAZIONE DI ESSERE!
    private int edge;

    public Square(int w, int h) {
        super(w, h);
    }
    public void Square(int e) {
        this.edge = e;
    }
    public int getArea () {
        return (this.edge * this.edge) ;
    }

}
