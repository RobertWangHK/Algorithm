package AdvancedGraph;

/**
 * Created by rober on 7/17/2017.
 */
public class FlowNetwork {
    private final int V;
    private SET<FlowEdge>[] adj;

    public FlowNetwork(int V) {
        this.V = V;
        this.adj = (SET<FlowEdge>[]) new SET[V];
        for (int i = 0; i < V; i++)
            adj[i] = new SET<FlowEdge>();
    }

    public void addEdge(FlowEdge e) {
        int v = e.from();
        int w = e.to();
        adj[v].add(e);
        adj[w].add(e);
    }

    public Iterable<FlowEdge> adj(int v) {
        return adj[v];
    }

    public int getV() { return V; }
}
