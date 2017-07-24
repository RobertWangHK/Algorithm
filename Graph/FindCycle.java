package Graph;

import java.util.Stack;

/**
 * Created by rober on 7/11/2017.
 * Find cycle in directed graph
 */
public class FindCycle {
    private boolean[] marked;
    private boolean hasCyle;
    private boolean[] onStack;

    public FindCycle(DiGraph G) {
        marked = new boolean[G.getV()];
        for (int i = 0; i < G.getV(); i++) {
            if (!marked[i]) {
                onStack = new boolean[G.getV()];
                findCycle(G, i);
            }
        }
    }

    private void findCycle(DiGraph G, int v) {
        marked[v] = true;
        onStack[v] = true;
        /**
         * Only consider the case where there is a edge pointing
         * back to one of its parents on the path, after traversing
         * all its children, needs to remove parent from onStack, might
         * be pointed by some other unrelated vertexes.
         */
        for (int w : G.adj(v)) {
            if (!marked[w]) findCycle(G, w);
            else if (onStack[w]) hasCyle = true; return;
        }
        onStack[v] = false;
    }

    public boolean hasCycle(){ return hasCyle;}
}
