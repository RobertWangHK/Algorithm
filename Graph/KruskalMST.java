package Graph;

import java.util.LinkedList;

/**
 * Created by rober on 7/13/2017.
 */
public class KruskalMST {
    private LinkedList<Edge> mst = new LinkedList<Edge>();

    public KruskalMST(EdgeWeightedGraph G) {
        UnionFind uf = new UnionFind(G.getV());
        MinPQ<Edge> pq = new MinPQ<Edge>();
        // build minimum priority queue for edges
        for (Edge e : G.edges())
            pq.insert(e);

        while (!pq.isEmpty() && mst.size() < G.getV() - 1) {
            Edge curr = pq.delMin();
            int v = curr.either();
            int w = curr.other(v);
            if (!uf.connected(v, w)) {
                mst.push(curr);
                uf.union(v,w);
            }
        }
    }

    public Iterable<Edge> edges() {
        return mst;
    }
}
