package AdvancedGraph;

import Graph.TopologicalOrder;
import java.util.Stack;

/**
 * Created by rober on 7/14/2017.
 */
public class AcyclicSP {
    private int[] edgeTo;
    private double[] distTo;
    private int s;

    public AcyclicSP(EdgeWeightedDigraph G, int s) {
        edgeTo = new int[G.getV()];
        distTo = new double[G.getV()];
        this.s = s;

        for (int i = 0; i < G.getV(); i++) {
            distTo[i] = Double.POSITIVE_INFINITY;
        }
        distTo[s] = 0.0;

        TopologicalOrder topological = new TopologicalOrder(G);
        for (int v : topological.reverseOrder()) {
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
