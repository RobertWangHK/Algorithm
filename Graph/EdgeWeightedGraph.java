package Graph;

import java.util.Iterator;

/**
 * Created by rober on 7/13/2017.
 */
public class EdgeWeightedGraph {
    private final int V;
    private final SET<Edge>[] adj;

    public EdgeWeightedGraph(int v) {
        this.V = v;
        adj = (SET<Edge>[]) new SET[V];
        for (int i = 0; i < V; i++) {
            adj[i] = new SET<Edge>();
        }
    }

    public void addEdge(Edge e) {
        int v = e.either();
        int w = e.other(v);
        adj[v].add(e);
        adj[w].add(e);
    }

    public Iterable<Edge> adj(int v) {
        return adj[v];
    }

    /**
     * Retuen all edges in this edge-weighted graph, to iterate
     * over the edges intthis edge-weighted graph, use foreach notation
     * {@code for (Edge e: G.edges()}
     * @return
     */
    public Iterable<Edge> edges() {
        SET<Edge> list = new SET<Edge>();
        for (int i = 0; i < getV(); i++) {
            for (Edge e : adj(i)) {
                if (e.other(i) > i) list.add(e);
            }
        }
        return list;
    }

    public Iterable<Edge> edges(int v) {
        SET<Edge> list = new SET<Edge>();
        for (int i = 0; i < getV(); i++) {
            for (Edge e : adj(i)) {
                if (e.either() == v && e.other(i) > i)
                    list.add(e);
            }
        }
        return list;
    }

    public int getV() {
        return V;
    }

    public EdgeWeightedGraph reverse() {
        EdgeWeightedGraph temp = new EdgeWeightedGraph(V);
        for (int v = 0; v < V; v++) {
            for (Edge w : adj(v)) {
                temp.addEdge(new Edge(w.either(),
                        w.other(w.either()), w.weight()));
            }
        }
        return temp;
    }

}
