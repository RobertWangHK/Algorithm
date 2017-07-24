package Graph;

import java.util.LinkedList;
import java.util.Stack;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by rober on 7/11/2017.
 */
public class DirectedBFS {
    private boolean marked[];   // set of discovered website
    private int EdgeTo[];       // restore path from source
    private int s;              // source

    public DirectedBFS(DiGraph G, int v) {
        s = v;
        marked = new boolean[G.getV()];
        EdgeTo = new int[G.getV()];
        bfs(G, v);
    }

    /**
     * while queue is not empty, pop queue and search all websites
     * that it can reach, if not discovered before, add to queue and
     * continue.
     * @param G
     * @param v
     */
    private void bfs(DiGraph G, int v) {
        marked[v] = true;
        LinkedList<Integer> queue = new LinkedList<Integer>();
        queue.addLast(v);
        while (!queue.isEmpty()) {
            int w = queue.removeFirst();
            for (int i : G.adj(w)) {
                if (!marked[i]) {
                    queue.addLast(i);
                    marked[i] = true;
                    EdgeTo[i] = w;
                }
            }
        }
    }

    public Iterable<Integer> EdgeTo(int v) {
        if (!marked[v]) return null;
        Stack<Integer> path = new Stack<Integer>();
        for (int i = v; i != s; i = EdgeTo[i]) {
            path.push(i);
        }
        path.push(s);
        return path;
    }

    static void searchWebsite(String root) {
        LinkedList<String> queue = new LinkedList<>();
        SET<String> discovered = new SET<String>();
        queue.addLast(root);
        discovered.add(root);

        while (!queue.isEmpty()) {
            String v = queue.removeFirst();
            System.out.println(v);
            In in = new In(v);
            String input = in.readAll();

            String regexp = "http://(\\w+\\.)*(\\w+)";
            Pattern patten = Pattern.compile(regexp);
            Matcher marcher = patten.matcher(input);

            while(marcher.find()) {
                String w = marcher.group();
                if (!discovered.contain(w)) {
                    discovered.add(w);
                    queue.add(w);
                }
            }
        }
    }

    public static void main(String[] args) {
        String root = "http://www.princeton.edu";
        DirectedBFS.searchWebsite(root);
    }
}
