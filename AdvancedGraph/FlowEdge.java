package AdvancedGraph;

import sun.security.provider.certpath.Vertex;

/**
 * Created by rober on 7/17/2017.
 */
public class FlowEdge implements Comparable<FlowEdge>{
    private final int v, w; // v is forward edge
    private final double capacity;
    private double flow;

    public FlowEdge(int v, int w, double capacity) {
        this.v = v;
        this.w = w;
        this.capacity = capacity;
    }

    public int from() { return v; }
    public int to() {return w; }
    public double capacity() { return capacity; }
    public double Flow() { return flow; }

    public int other(int vertex) {
        if (vertex == v) return w;
        return v;
    }

    public double residualCapacityTo(int vertex) {
        if (vertex == v) return flow;
        return capacity - flow;
    }
    public void addResidualFlowTo(int vertex, double delta) {
        if (vertex == v) flow -= delta;
        else flow += delta;
    }

    public int compareTo(FlowEdge e) {
        return Double.compare(flow, e.flow);
    }
}
