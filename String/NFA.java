package String;

import Graph.DiGraph;
import Graph.DirectedDFS;

import java.util.HashSet;
import java.util.Set;
import java.util.Stack;

/**
 * Created by rober on 7/24/2017.
 * Regular Expression Patten matching using Non-deterministic
 * Finite Machine
 */
public class NFA {
    private char[] re;
    private DiGraph G;
    private int M;

    public NFA(String reg) {
        M = reg.length();
        re = reg.toCharArray();
        G = buildGraph();
    }

    // Simulate the operation of NFA once the digraph is built
    public boolean recognize(String txt){
        Set<Integer> pc = new HashSet<Integer>();

        // add all states reachable from 0 to pc
        DirectedDFS dfs = new DirectedDFS(G, 0);
        for (int i = 0; i < G.getV(); i++) {
            if (dfs.marked(i)) pc.add(i);
        }

        /**
         * For each included node, check its value,
         * 1> '.' : match anything or value == char array, next state
         * 2> iterate next state and store alpha reachable states (red)
         * 3> add all next states & their reachable states (red lines)
         * to newly created set.
         * 4> continue till the end, if reach accept state (end of reg),
         * return true, else return false.
         */
        for (int i = 0; i < txt.length(); i++) {
            Set<Integer> match = new HashSet<Integer>();
            for (int v : pc) {
                if (v == M) continue;
                if (re[v] == '.' || re[v] == txt.charAt(i))
                    match.add(v + 1);
            }

            dfs = new DirectedDFS(G, match.iterator());
            pc = new HashSet<Integer>();
            for (i = 0; i < G.getV(); i++) {
                if (dfs.marked(i)) pc.add(i);
            }
        }

        for (int v : pc) {
            if (v == M) return true;
        }
        return false;

    }

    /**
     * the Digraph is only for representing all alpha (red) transitions
     * of the NFA digraph
     */
    public DiGraph buildGraph() {
        DiGraph G = new DiGraph(M + 1);
        Stack<Integer> ops = new Stack<Integer>();
        for (int i = 0 ; i < M; i++) {
            int lp = i;

            if (re[i] == '(' || re[i] == '|') ops.push(i);

            else if (re[i] == ')') {
                int or = ops.pop();
                if (re[or] == '|') {
                    lp = ops.pop();
                    G.addEdge(lp, or + 1);
                    G.addEdge(or, i);
                }
                else lp = or;
            }

            if (i < M-1 && re[i + 1] == '*') {
                G.addEdge(i + 1, lp);
                G.addEdge(lp, i + 1);
            }

            if (re[i] == '(' || re[i] == '*' || re[i] == ')') {
                G.addEdge(i, i + 1);
            }
        }
        return G;
    }
}
