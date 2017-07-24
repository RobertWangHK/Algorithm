package Graph;

/**
 * Created by rober on 7/11/2017.
 * Like undirected graph implementation, algorithms are based on
 * iterating through vertices pointing from v (connected with v);
 */
public class DiGraph {
    private final int V;
    private final SET<Integer>[] adj;

    public DiGraph(int v) {
        this.V = v;
        adj = (SET<Integer>[]) new SET[V];
        for (int i = 0; i < V; i++) {
            adj[i] = new SET<Integer>();
        }
    }

    /**
     * addEdge from v to w == v -> w
     * @param v point origin
     * @param w point tail
     */
    public void addEdge(int v, int w) {
        adj[v].add(w);
    }

    public Iterable<Integer> adj(int v) {
        return adj[v];
    }

    public int getV() {
        return V;
    }

    public DiGraph reverse() {
        DiGraph temp = new DiGraph(V);
        for (int v = 0; v < V; v++) {
            for (int w : adj(v)) {
                temp.addEdge(w, v);
            }
        }
        return temp;
    }
}
