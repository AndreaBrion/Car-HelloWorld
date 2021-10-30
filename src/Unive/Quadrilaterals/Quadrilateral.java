package Unive.Quadrilaterals;

public abstract class Quadrilateral extends Polyhedron {
    private final int edge1, edge2, edge3, edge4;
    protected Quadrilateral(int edge1, int edge2, int edge3, int edge4) {
        this.edge1 = edge1;
        this.edge2 = edge2;
        this.edge3 = edge3;
        this.edge4 = edge4;
    }
    abstract public double getA();
    public double getPerim() {
        return edge1+edge2+edge3+edge4;
    }
    public int getEdge1() {
        return edge1;
    }
    public int getEdge2() {
        return edge2;
    }
    public int getEdge3() {
        return edge3;
    }
    public int getEdge4() {
        return edge4;
    }

    //public Polyhedron addEdge (double edge) {
    //    return new Pentagon(this.getEdge1(), this.getEdge2(), this.getEdge3(), this.getEdge4() , edge);

   // }
}
