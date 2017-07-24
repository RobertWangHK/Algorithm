package Graph;

/**
 * Created by rober on 7/13/2017.
 * Strong component is of the same idea as connected component
 * as in undirected graph
 *
 * 1. run DFS on G^R to get reverse postorder
 * 2. run DFS on G considering postorder given by first DFS
 */
public class StrongComponent {

    private boolean[] marked;
    private int count;
    private int[] id;

    public StrongComponent(DiGraph G) {
        marked = new boolean[G.getV()];
        id = new int[G.getV()];
        DiGraph reverse = G.reverse();
        DepthFirstOrder dfs = new DepthFirstOrder(reverse);

        for (int i = 0; i < G.getV(); i++) {
            if (!marked[i]) {
                dfs(G, i);
                count++;
            }
        }
    }

    private void dfs(DiGraph G, int v) {
        marked[v] = true;
        id[v] = count;      // should be placed here for 1st element
        for (int w : G.adj(v)) {
            if (!marked[w]) {
                dfs(G, w);
            }
        }
    }

    public boolean StronglyConnected(int v, int w) {
        return id[w] == id[v];
    }
}
