/**
 * Created by rober on 7/6/2017.
 * It is for interval insertion, deletion and intersection query
 */
public class IntervalST<Value> {
    private Node root;

    private class Node {
        private Interval1D interval;
        private Value value;
        Node left, right;
        double maxEnd;         // max right endpoint of subtrees
        int count;

        public Node(Interval1D interval, Value value) {
            this.interval = interval;
            this.value = value;
            this.count = 1;
            this.maxEnd = interval.max();
        }
    }


    /***************************************************************************
     * BST search
     ***************************************************************************/

    public boolean contains(Interval1D interval) {
        return get(interval) != null;
    }

    public Value get(Interval1D interval) {
        return get(root, interval).value;
    }

    private Node get(Node root, Interval1D interval) {
        if (root == null) return null;
        int compare = Interval1D.MIN_ENDPOINT_ORDER.compare(interval, root.interval);
        if (compare > 0) return get(root.right, interval);
        else if (compare < 0) return get(root.left, interval);
        else {
            return root;
        }
    }

    /***************************************************************************
     * Insertion
     ***************************************************************************/

    public void put(Interval1D interval, Value value) {
        if (contains(interval)) return;
        root = put(root, interval, value);
    }

    private Node put(Node root, Interval1D interval, Value value) {
        if (root == null) return new Node(interval, value);
        int compare = Interval1D.MIN_ENDPOINT_ORDER.compare(interval, root.interval);
        if (compare > 0) {
            root.right = put(root.right, interval, value);
        } else if (compare < 0) {
            root.left = put(root.left, interval, value);
        }
        fix(root);
        return root;
    }

    /***************************************************************************
     * Interval searching
     ***************************************************************************/

    public Interval1D search(Interval1D interval) {
        return search(root, interval);
    }

    /**
     * Already prove that if left does nto intersect with interval ->
     * left's min > interval's max -> so does right's min > interval's max
     *
     * @param root
     * @param interval
     * @return
     */
    private Interval1D search(Node root, Interval1D interval) {
        while (root != null) {
            if (root.interval.intersects(interval)) return root.interval;
            if (root.left == null) {
                root = root.right;
            } else if (root.interval.max() < interval.min()) {
                root = root.right;
            } else {
                root = root.left;
            }
        }
        return null;
    }

    /***************************************************************************
     * useful binary tree functions
     ***************************************************************************/

    // return number of nodes in subtree rooted at x
    public int size() {
        return size(root);
    }

    private int size(Node x) {
        if (x == null) return 0;
        else return x.count;
    }

    // height of tree (empty tree height = 0)
    public int height() {
        return height(root);
    }

    private int height(Node x) {
        if (x == null) return 0;
        return 1 + Math.max(height(x.left), height(x.right));
    }

    private void fix(Node x) {
        if (x == null) return;
        x.count = 1 + size(x.left) + size(x.right);
        x.maxEnd = Math.max(x.interval.max(), Math.max(max(x.left), max(x.right)));
    }

    private double max(Node x) {
        if (x == null) return Integer.MIN_VALUE;
        return x.maxEnd;
    }
}