package Quadrilaterals;

public class Square extends Rectangle{ // quadrato E' un rettangolo! RELAZIONE DI ESSERE!
    private int edge;
    public Square(int edge) {
        super(edge,edge);
    }
    public double getEdge() {return super.getEdge1();}
    public double getArea() {
        return this.getEdge()*this.getEdge();
    }
    public static void main (String[] args){
        Rectangle R = new Rectangle(5,10);
        Square S = new Square(10);
        R.getPerim();
        S.getPerim();
        R.getArea();
        S.getArea();
    }
}
