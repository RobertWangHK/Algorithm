/**
 * Created by rober on 7/9/2017.
 */
public class LinearProbingHashST<Key, Value> {
    private int M;
    private int size;
    private Key[] keys;
    private Value[] vals;

    public LinearProbingHashST(int capacity) {
        M = capacity;
        size= 0;
        keys = (Key[] )new Object[M];
        vals = (Value[]) new Object[M];
    }

    private int hash(Key key) {
        return (key.hashCode() & 0x7fffffff) % M;
    }

    private void put(Key key, Value val) {
        int i = hash(key);
        for (; keys[i] != null; i = (i + 1) % M) {
            if (keys[i].equals(key)) return;
        }
        keys[i] = key;
        vals[i] = val;
        size++;

        /* check fillment*/
        if (size >= M / 2) {
            resize(size * 2);
        }
    }

    private Value search(Key key) {
        int i = hash(key);
        for (; keys[i] != null; i = (i + 1) % M) {
            if (keys[i].equals(key)) return vals[i];
        }
        return null;
    }
    /* resize vals & keys by given new capacity */
    private void resize(int capacity) {
        LinearProbingHashST<Key, Value> temp = new LinearProbingHashST<Key, Value>(capacity);
        for (int i = 0; i < M; i++) {
            if (keys[i] != null) temp.put(keys[i], vals[i]);
        }
        keys = temp.keys;
        vals = temp.vals;
        M = temp.M;
    }
}
