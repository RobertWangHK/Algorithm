import java.util.LinkedList;

/**
 * Created by rober on 7/2/2017.
 */
public class BinarySearchTree<Key extends Comparable<Key>, Value> {
    private Node root;

    /**
     * Inorder iterable key list for BST object
     * @return
     */
    public Iterable<Key> keys() {
        LinkedList<Key> q = new LinkedList<Key>();
        inorderTraverse(root, q);
        return q;
    }

    private void inorderTraverse(Node current, LinkedList<Key> q) {
        if (current == null) return;
        inorderTraverse(current.left, q);
        q.addLast(current.key);
        inorderTraverse(current.right, q);
    }

    public void delete(Key key) {
        root = delete(root, key);
    }

    /**
     * Replace node with its successor, update left/right child, delete by no one pointing to (garbage collection)
     * @param current
     * @param key
     * @return
     */
    private Node delete(Node current, Key key) {
        if (current == null) return null;
        else if (key.compareTo(current.key) < 0) return delete(current.left, key);
        else if (key.compareTo(current.key) > 0) return delete(current.right, key);
            //find the Node with the key value
        else {
            if (current.left == null && current.right == null) return null;
            else if (current.left == null) return current.right;
            else if (current.right == null) return current.left;
                //Node has both left and right child
            else {
                Node temp = current;
                current = min(current.right);
                current.left = temp.left;
                current.right = delMin(temp.right);
            }
            current.count = 1 + size(current.left) + size(current.right);
            return current;
        }
    }

    //delete the minimum key Node
    public void delMin() {
        root = delMin(root);
    }

    private Node delMin(Node current) {
        if (current.left == null) return current.right;
        else current.left = delMin(current.left);
        current.count = 1 + size(current.left) + size(current.right);
        return current;
    }

    private Node min(Node current) {
        if (current.left == null) return current;
        else return min(current.left);
    }

    public boolean conains(Key key) {
        if (key == null) throw new IllegalArgumentException("null key");
        return get(key) != null;
    }

    /**
     * general search process for key (same as binary search)
     * @param key
     * @return
     */
    public Value get(Key key) {
        Node current = root;
        while(current != null) {
            if (key.compareTo(current.key) < 0) current = current.left;
            else if (key.compareTo(current.key) > 0) current = current.right;
            else return current.value;
        }
        return null;
    }

    /**
     * Return number of elements smaller than Node denoted by key
     */
    public int rank(Key key) {
        return rank(root, key);
    }

    private int rank(Node current, Key key) {
        if (current == null) return 0;
        if (key.compareTo(current.key) == 0) return size(current.left);
        else if (key.compareTo(current.key) < 0) return rank(current.left, key);
        else {
            return rank(current.right, key) + size(current.left) + 1;
        }
    }

    // Return count info
    public int size() {
        return size(root);
    }

    private int size(Node current) {
        if (current == null) return 0;
        return current.count;
    }

    //return the maximum key smaller than query
    public Key floor(Key key) {
        Node floor = floor(root, key);
        if (floor == null) return null;
        return floor.key;
    }

    private Node floor(Node current, Key key) {
        if (current == null) return null;
        else if (key.compareTo(current.key) == 0) return current;
        else if (key.compareTo(current.key) < 0) {
            return floor(current.left, key);
        } else {
            // trick part, current might be floor if right is null
            Node right = floor(current.right, key);
            if (right != null) return right;
        }
        return current;
    }

    /**
     * Same logic as get operation
     * @param key
     * @param value
     */
    public void put(Key key, Value value) {
        root = put(root, key, value);
    }

    private Node put(Node current, Key key, Value value) {
        if (current == null) return new Node(key, value);
        else if (key.compareTo(current.key) < 0) {
            current.left = put(current.left, key, value);
        }
        else if (key.compareTo(current.key) > 0) {
            current.right = put(current.right, key, value);
        }
        else current.value = value;
        current.count = 1 + size(current.left) + size(current.right);
        return current;
    }


    public Key select(int k) {
        if (k < 0 || k > size(root)) {throw new IllegalArgumentException("invalid argument for select");}
        Node selected = select(root, k);
        return selected.key;
    }

    private Node select(Node current, int k) {
        if (current == null) return null;
        else if (size(current.left) > k) {
            return select(current.left, k);
        }
        else if (size(current.left) < k) {
            return select(current.right, k - size(current.left) - 1);
        }
        else return current;
    }

    /**
     * get the number of elements in between key lo and key hi
     * @param lo
     * @param hi
     * @return
     */
    public int size(Key lo, Key hi) {
        if (lo == null) throw new IllegalArgumentException("lo");
        if (hi == null) throw new IllegalArgumentException("hi");
        if (lo.compareTo(hi) > 0) return 0;
        if (conains(hi)) return rank(hi) - rank(lo) + 1;
        return rank(hi) - rank(lo);
    }

    public int height() {
        return height(root);
    }

    private int height(Node current) {
        if (current == null) return 0;
        return 1 + Math.max(height(current.left), height(current.right));
    }

    private class Node {
        private Key key;
        private Value value;
        private Node left, right;
        private int count;

        public Node(Key key, Value value){
            this.key = key;
            this.value = value;
        }
    }
}
