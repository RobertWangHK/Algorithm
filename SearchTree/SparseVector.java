/**
 * Created by rober on 7/9/2017.
 */

import java.util.HashMap;
import java.util.Iterator;

/**
 * Efficient iterator for sparse matrix multiplications
 * Replace each row of 2d array to sparsematrix representation
 */
public class SparseVector{
    private HashMap<Integer, Double> hm;
    public SparseVector() {
        hm = new HashMap<>();
    }
    public void put(int key, double value) {hm.put(key, value);}
    public double get(int key) {
        if (!hm.containsKey(key)) return 0.0;
        return hm.get(key);
    }
    public Iterable<Integer> iterator() {
        return hm.keySet();
    }
    public double dot(double[] that) {
        double sum = 0.0;
        for (Integer i : iterator()) {
            sum += hm.get(i) * that[i];
        }
        return sum;
    }
}
