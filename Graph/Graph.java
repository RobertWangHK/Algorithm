/**
 * Created by rober on 7/10/2017.
 */

import java.util.ArrayList;
import java.util.LinkedList;

/**
 * Adjacent list graph representation
 * Givens iterating over vertices adjacent to v in time
 * appropriation to number of adjacent vertices
 */
public class Graph {

    private SET<Integer>[] adj;
    private final int V;

    public Graph(int V) {
        this.V = V;
        adj = (SET<Integer>[]) new SET[V];
        for (int i = 0; i < V; i++) {
            adj[i] = new SET<Integer>();
        }
    }

    public void addEdge(int v, int w) {
        adj[v].add(w);
        adj[w].add(v);
    }

    public Iterable<Integer> adj(int v) {
        return adj[v];
    }

    public int getV() {
        return V;
    }

    public static int degree(Graph G, int v) {
        int degree = 0;
        for (int w: G.adj(v)) {
            degree++;
        }
        return degree;
    }

    public static int maxDegree(Graph G) {
        int max = 0;
        for (int v = 0; v < G.getV(); v++) {
            if (degree(G, v) > max) {
                max = degree(G, v);
            }
        }
        return max;
    }
}
