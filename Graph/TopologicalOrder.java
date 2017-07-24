package Graph;

import AdvancedGraph.DirectedEdge;
import AdvancedGraph.EdgeWeightedDigraph;

import java.util.Stack;

/**
 * Created by rober on 7/11/2017.
 *
 * This topological order assumes that there is no cycles
 *
 * Return one topological order of the directed graph
 * e.g. pre-requisite course for other courses, so courses that
 * are required by other succeeding courses come first.
 */
public class TopologicalOrder {
    private boolean[] marked;
    private Stack<Integer> reverseOrder;
    private boolean hasCyle;
    private boolean[] onStack;

    public TopologicalOrder(DiGraph G) {
        reverseOrder = new Stack<Integer>();
        marked = new boolean[G.getV()];
        onStack = new boolean[G.getV()];
        for (int i = 0; i < G.getV(); i++) {
            if (!marked[i]) dfs(G, i);
        }
    }

    public TopologicalOrder(EdgeWeightedDigraph G) {
        reverseOrder = new Stack<Integer>();
        marked = new boolean[G.getV()];
        onStack = new boolean[G.getV()];
        for (int i = 0; i < G.getV(); i++) {
            if (!marked[i]) dfs(G, i);
        }
    }

    private void dfs(EdgeWeightedDigraph G, int v) {
        marked[v] = true;
        for (DirectedEdge e : G.adj(v)) {
            if (!marked[v]) dfs(G, e.to());
        }
        reverseOrder.push(v);
    }

    private void dfs(DiGraph G, int v) {
        marked[v] = true;
        for (int w : G.adj(v)) {
            if (!marked[v]) dfs(G, w);
        }
        reverseOrder.push(v);
    }

    public Iterable<Integer> reverseOrder() {
        return reverseOrder;
    }
}
