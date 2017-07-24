import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

/**
 * Created by rober on 7/10/2017.
 * BFS not only computes connected vertices from the source,
 * but it also computes the shortest path to each node becuase
 * it searches in a depth-depth order.
 */
public class BreadthFirstPaths {
    private boolean[] marked;
    private int[] edgeTo;
    private int count;
    private int s;

    public BreadthFirstPaths(Graph G, int s) {
        this.s = s;
        marked = new boolean[G.getV()];
        bfs(G, s);
    }

    private void bfs(Graph G, int v) {
        LinkedList<Integer> queue = new LinkedList<Integer>();
        queue.addLast(v);
        marked[v] = true;
        while(!queue.isEmpty()) {
            int temp = queue.removeFirst();
            for (int i : G.adj(v)) {
                if (!marked[i]) {
                    queue.addLast(i);
                    marked[i] = true;
                    edgeTo[i] = v;
                }
            }
        }
    }

    public Iterable<Integer> pathTo(int v) {
        if (!hasPathTo(v)) return null;
        Stack<Integer> path = new Stack<Integer>();
        for (int i = v; i != s; i = edgeTo[i]) {
            path.push(i);
        }
        path.push(s);
        return path;
    }

    public boolean hasPathTo(int v) {
        return marked[v];
    }
}
