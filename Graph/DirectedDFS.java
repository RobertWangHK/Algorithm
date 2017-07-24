package Graph;

import java.util.Stack;

/**
 * Created by rober on 7/11/2017.
 */
public class DirectedDFS {
    private boolean[] marked;
    private int[] EdgeTo;
    private int s;

    public DirectedDFS(DiGraph G, int v) {
        marked = new boolean[G.getV()];
        EdgeTo = new int[G.getV()];
        s = v;
        dfs(G, v);
    }

    private void dfs(DiGraph G, int v) {
        if (!marked[v]) {
            for (int i : G.adj(v)) {
                if (!marked[i]) {
                    dfs(G, i);
                    EdgeTo[i] = v;
                }
            }
        }
    }

    /**
     * Return path to any vertice from source v
     * @param v
     * @return
     */
    public Iterable<Integer> pathTo(int v) {
        if (!marked[v]) return null;
        Stack<Integer> path = new Stack<Integer>();
        for (int i = v; i != s; i = EdgeTo[i]) {
            path.push(i);
        }
        path.push(s);
        return path;
    }

    public boolean visited(int v) {return marked[v];}
}
