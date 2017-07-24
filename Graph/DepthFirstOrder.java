package Graph;

import java.util.Stack;

/**
 * Created by rober on 7/11/2017.
 * Return one topological order of the directed graph
 * e.g. pre-requisite course for other courses, so courses that
 * are required by other succeeding courses come first.
 */
public class DepthFirstOrder {
    private boolean[] marked;
    private Stack<Integer> reverseOrder;
    private boolean hasCyle;
    private boolean[] onStack;

    public DepthFirstOrder(DiGraph G) {
        reverseOrder = new Stack<Integer>();
        marked = new boolean[G.getV()];
        onStack = new boolean[G.getV()];
        for (int i = 0; i < G.getV(); i++) {
            if (!marked[i]) dfs(G, i);
        }
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
