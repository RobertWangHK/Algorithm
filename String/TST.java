package String;

/**
 * Created by rober on 7/21/2017.
 * Amount of space taken is less than TrieST
 * Can be combined that use R way at top level and then TST
 */
public class TST<Value> {

    private Node root;

    private class Node{
        private Value value;
        private char c;
        private Node left, right, mid;
    }

    public void put(String key, Value value) {
        root = put(root, key, value, 0);
    }

    private Node put(Node root, String key, Value value, int d) {
        char c = key.charAt(d);
        if (root == null) {
            root = new Node();
            root.c = c;
        }

        if (c < root.c) {
            root.left = put(root.left, key, value, d + 1);
        }

        else if (c > root.c) {
            root.right = put(root.right, key, value, d + 1);
        }

        /**
         * if not last character of key, go down middle
         */
        else if (d < key.length()) {
            root.mid = put(root.mid, key, value, d + 1);
        }

        /**
         * last key character -> set value accordingly
         */
        else root.value = value;

        return root;
    }

    public boolean contains(String key) {
        return get(key) != null;
    }

    public Value get(String key) {
        Node x = get(root, key, 0);
        if (x == null) return null;
        return x.value;
    }

    private Node get(Node curr, String key, int d) {
        if (curr == null) return null;
        char c = key.charAt(d);
        if (c < curr.c) {
            return get(curr.left, key, d + 1);
        }
        else if (c > curr.c) {
            return get(curr.right, key, d + 1);
        }
        else if (d < key.length()) {
            return get(curr.mid, key, d + 1);
        }
        else {
            return curr;
        }
    }

}
