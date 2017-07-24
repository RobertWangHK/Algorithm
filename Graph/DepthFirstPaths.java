import java.util.Stack;

/**
 * Created by rober on 7/10/2017.
 * Run time == sum of degrees
 */
public class DepthFirstPaths {
    private boolean[] marked;
    private int[] edgeTo;
    private int s;
    private int count;

    /**
     * Marked indicates number of vertices connected to s
     * @param G Pass-in graph object
     * @param s Origin of paths
     */
    public DepthFirstPaths(Graph G, int s) {
        this.s = s;
        marked = new boolean[G.getV()];
        dfs(G, s);
    }

    /**
     * For vertices connected to s, if unmarked, iterate
     * over them, and add edgeTo from its parent vertice.
     * e.g. v -> w, edgeTo[w] = v
     * @param G
     * @param v
     */
    private void dfs(Graph G, int v) {
        marked[v] = true;
        count++;
        for (int w : G.adj(v)) {
            if (!marked[w]) {
                dfs(G, w);
                edgeTo[w] = v;
            }
        }
    }

    /**
     * Return Iterable stack that contains path from s to
     * any node v
     * @param v query node
     * @return Iterable stack
     */
    public Iterable<Integer> pathTo(int v) {
        if (!hasPathTo(v)) return null;
        Stack<Integer> path = new Stack<Integer>();
        for (int i = v; i != s; i = edgeTo[i]) {
            path.push(i);
        }
        path.push(s);
        return path;
    }

    /**
     * Can find vertices connected to source in constatn time and
     * can find a path to s in time proportional to its length
     * @param v
     * @return
     */
    public boolean hasPathTo(int v) {
        return marked[v];
    }
}
