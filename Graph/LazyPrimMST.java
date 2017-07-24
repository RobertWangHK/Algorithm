package Graph;

import java.util.LinkedList;

/**
 * Created by rober on 7/13/2017.
 */
public class LazyPrimMST {

    private LinkedList<Edge> mst;

    public LazyPrimMST(EdgeWeightedGraph G) {
        mst = new LinkedList<Edge>();
        MinPQ<Edge> pq = new MinPQ<Edge>();
        UnionFind uf = new UnionFind(G.getV());

        for (Edge e : G.edges(0)) {
            pq.insert(e);
        }

        while (!pq.isEmpty() && mst.size() < G.getV() - 1) {
            Edge edge = pq.delMin();
            int v = edge.either();
            int w = edge.other(v);
            if (uf.connected(v, w)) continue;
            for (Edge e : G.edges(w)) {
                int other = e.other(w);
                if (!uf.connected(w, other))
                    pq.insert(e);
            }
            mst.add(edge);
            uf.union(v, w);
        }

    }

    private Iterable<Edge> edges() {
        return mst;
    }
}
