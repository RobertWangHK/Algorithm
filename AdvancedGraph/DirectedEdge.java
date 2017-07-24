package AdvancedGraph;

/**
 * Created by rober on 7/14/2017.
 */
public class DirectedEdge implements Comparable<DirectedEdge>{
    private final int v, w;
    private final double weight;

    public DirectedEdge(int v, int w, double weight) {
        this.v = v;
        this.w = w;
        this.weight = weight;
    }

    public int from() {return v;}
    public int to() {return w;}
    public double weight() {return weight();}

    public int compareTo(DirectedEdge that) {
        if (weight > that.weight) return +1;
        if (weight < that.weight) return -1;
        return 0;
    }

}
