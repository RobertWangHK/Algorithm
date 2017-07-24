package String;

import java.util.LinkedList;
import java.util.Queue;

/**
 * Created by rober on 7/21/2017.
 */
public class TrieST<Value> {
    private static final int R = 256;
    private Node root = new Node();

    private static class Node {
        private Object value;
        private Node[] next = new Node[R];
    }

    private void put(String key, Value value) {
        root = put(root, key, value, 0);
    }

    private Node put(Node root, String key, Value value, int d) {
        if (root == null) root = new Node();
        if (d == key.length()) {
            root.value = value;
            return root;
        }
        char c = key.charAt(d);
        root.next[c] = put(root.next[c], key, value, d + 1);
        return root;
    }

    public boolean contains(String key) {
        return get(key) != null;
    }

    public Value get(String key) {
        Node x = get(root, key, 0);
        if (x == null) return null;
        return (Value)x.value;
    }

    private Node get(Node curr, String key, int d) {
        if (curr == null) return null;
        if (d == key.length()) return curr;
        char c = key.charAt(d);
        return get(curr.next[c], key, d + 1);
    }

    public Iterable<String> keys() {
        LinkedList<String> q = new LinkedList<String>();
        collect(root, "", q);
        return q;
    }

    public Iterable<String> keysWithPrefix(String prefix) {
        LinkedList<String> q = new LinkedList<String>();
        Node x = get(root, prefix, 0);
        collect(x, prefix, q);
        return q;
    }

    private void collect(Node curr, String path, LinkedList q) {
        if (curr == null) return;;
        if (curr.value != null) q.addLast(path);
        for (char c = 0; c < R; c++) {
            if (curr.next[c] != null) {
                collect(curr.next[c], path + c, q);
            }
        }
    }

    public String longestPrefixOf(String query) {
        int length = search(root, query, 0);
        return query.substring(0, length);
    }

    private int search(Node curr, String query, int length) {
        if (curr == null) return length;
        char c = query.charAt(length);
        if (curr.next[c] != null) {
            return search(curr.next[c], query, length + 1);
        }
        return length;
    }


}
