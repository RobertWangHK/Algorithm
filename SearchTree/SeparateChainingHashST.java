/**
 * Created by rober on 7/8/2017.
 */
public class SeparateChainingHashST <Key, Value>{
    private int M = 100;
    private Node[] st = new Node[M];

    private static class Node {
        private Object key;
        private Object val;
        private Node next;

        private Node(Object key, Object val, Node curr) {
            while (curr != null) {
                curr = curr.next;
            }
            curr = new Node(key, val);
        }
        private Node(Object key, Object val) {
            this.key = key;
            this.val = val;
        }
    }

    private int hash(Key key) {
        return (key.hashCode() & 0x7fffffff) % M;
    }

    public Value get(Key key) {
        int i = hash(key);
        for (Node curr = st[i]; curr != null; curr = curr.next){
            if (key.equals(curr.key)) return (Value)curr.val;
        }
        return null;
    }

    public void put(Key key, Value value) {
        int i = hash(key);
        for (Node curr = st[i]; curr != null; curr = curr.next) {
            if (key.equals(curr.key)) curr.val = value; return;
        }
        st[i] = new Node(key, value, st[i]);
    }
}
