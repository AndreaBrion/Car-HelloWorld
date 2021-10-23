package Quadrilaterals;

public abstract class Polyhedron {
    private double[] edges;
    public double getP() {
        double p = 0.0;
        for (int i = 0; i < edges.length; i++) {
            p += edges[i];
        }
        return p;
    }
    public abstract double getA();
    public abstract Polyhedron addEdge(double edge);

}
