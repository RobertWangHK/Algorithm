package AdvancedGraph;

import java.util.Stack;

/**
 * Created by rober on 7/14/2017.
 */
public class DijkstraSP {
    private double distTo[];
    private int[] edgeTo;
    private IndexMinPQ<Double> pq;
    private int s;

    public DijkstraSP(EdgeWeightedDigraph G, int s) {
        edgeTo = new int[G.getV()];
        distTo = new double[G.getV()];
        pq = new IndexMinPQ<>(G.getV());
        this.s = s;

        // initiate distTo entries to infinity
        for (int i = 0; i < G.getV(); i++) {
            distTo[i] = Double.POSITIVE_INFINITY;
        }
        distTo[s] = 0.0;
        pq.insert(s, 0.0);

        while (!pq.isEmpty()) {

            // get vertex closest to source, take account
            // all of its edges and call relax
            int v = pq.delMin();
            for (DirectedEdge e : G.adj(v)) {
                relax(e);
            }
        }
    }

    private void relax(DirectedEdge e) {
        int v = e.from();
        int w = e.to();
        // update shortest path to w
        if (distTo[w] > distTo[v] + e.weight()) {
            distTo[w] = distTo[v] + e.weight();
            edgeTo[w] = v;
            if (pq.contains(w)) pq.decreaseKey(w, distTo[w]);
            else pq.insert(w, distTo[w]);
        }
    }

    public Iterable<Integer> pathTo(int v) {
        Stack<Integer> path = new Stack<Integer>();
        for (int i = v; i != s; i = edgeTo[i]) {
            path.push(i);
        }
        path.push(s);
        return path;
    }

}
