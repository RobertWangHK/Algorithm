package Graph;

/**
 * Created by rober on 7/13/2017.
 */
public class Edge implements Comparable<Edge> {
    private double weight;
    private int v;
    private int w;

    public Edge(int v, int w, double weight) {
        this.v = v;
        this.w = w;
        this.weight = weight;
    }

    /**
     * Return eigher or other vertex
     * @return
     */
    public int either() {
        return v;
    }
    public int other(int vertex){
        if (vertex == v) return w;
        else return v;
    }
    public int compareTo(Edge that) {
        if (weight > that.weight) return +1;
        if (weight < that.weight) return -1;
        return 0;
    }
    public double weight() {
        return weight;
    }

    @Override
    public String toString() {
        String construct = Double.toString(v) + " - " +
                Double.toString(w) + " " + Double.toString(weight);
        return construct;
    }
}
