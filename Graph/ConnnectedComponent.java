/**
 * Created by rober on 7/10/2017.
 */
public class ConnnectedComponent {
    private boolean marked[];
    private int[] id;
    private int count;      // number of components

    public ConnnectedComponent(Graph G) {
        marked = new boolean[G.getV()];
        id = new int[G.getV()];
        for (int i = 0; i < G.getV(); i++) {
            if (!marked[i]) {
                dfs(G, i);
                // update count after dfs(G, i)
                count++;

            }
        }
    }

    private void dfs(Graph G, int v) {
        marked[v] = true;
        id[v] = count;
        for (int w : G.adj(v)) {
            if (!marked[w]) {
                dfs(G, w);
            }
        }
    }

    public int count() {return count;}
    public int id(int v) {return id[v];}
    public boolean connected(int v, int w) {
        return id[v] == id[w];
    }
}
