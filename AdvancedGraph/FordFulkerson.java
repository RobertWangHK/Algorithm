package AdvancedGraph;

import java.util.LinkedList;

/**
 * Created by rober on 7/17/2017.
 */
public class FordFulkerson {
    private boolean[] marked;
    private FlowEdge[] edgeTo;
    private double flow;

    public FordFulkerson(FlowNetwork G, int s, int t) {
        flow = 0;
        double bottleneck = Double.POSITIVE_INFINITY;
        while (hasAugmentedPath(G, s, t)) {
            for (int i = t; i != s; i = edgeTo[i].other(i)) {
                bottleneck = Math.min(bottleneck, edgeTo[i].residualCapacityTo(i));
            }

            for (int i = t; i != s; i = edgeTo[i].other(i)) {
                edgeTo[i].addResidualFlowTo(i, bottleneck);
            }

            flow += bottleneck;
        }
    }
    /**
     * Idea to find shortest path to target, BFS, after queue
     * is empty, return marked[t], if true -> exists a path and
     * this path has to be the shortest one
     */
    public boolean hasAugmentedPath(FlowNetwork G, int s, int t) {
        marked = new boolean[G.getV()];
        edgeTo = new FlowEdge[G.getV()];
        LinkedList<Integer> list = new LinkedList<Integer>();

        list.addLast(s);
        marked[s] = true;

        while (!list.isEmpty()) {
            int v = list.removeFirst();
            for (FlowEdge e : G.adj(v)) {
                int w = e.other(v);
                if (e.residualCapacityTo(w) > 0 && !marked[w]) {
                    list.addLast(w);
                    marked[w] = true;
                    edgeTo[w] = e;
                }
            }
        }
        return marked[t];
    }


}
