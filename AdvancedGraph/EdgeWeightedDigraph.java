package AdvancedGraph;


/**
 * Created by rober on 7/14/2017.
 */
public class EdgeWeightedDigraph {
    private final int V;
    private final SET<DirectedEdge>[] adj;

    public EdgeWeightedDigraph(int V) {
        this.V = V;
        this.adj = (SET<DirectedEdge>[]) new SET[V];
        for (int i = 0; i < V; i++) {
            adj[i] = new SET<DirectedEdge>();
        }
    }

    public void addEdge(DirectedEdge e) {
        int v = e.from();
        int w = e.to();
        adj[v].add(e);
    }

    public Iterable<DirectedEdge> adj(int v) {
        return adj[v];
    }

    public int getV() {
        return V;
    }
}
