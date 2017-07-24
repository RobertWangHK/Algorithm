/**
 * Created by rober on 6/22/2017.
 */

//key must be comparable
public class maxpriorityQueue {
    /**
     * max priority always return the best item for process
     */
    public static class naivepriorityQueue<item extends Comparable> {
        /**
         * Insertion is constant time but deletion takes N time
         **/
        private item[] uq; //unordered priority queue
        private int N; //num of elements in the queue
        public naivepriorityQueue(int capacity) {
            uq = (item[]) new Comparable[capacity];
        }
        public boolean isEmpty() {
            return N == 0;
        }
        public void insert(item value) {
            uq[N++] = value;
        }
        public item delMax() {
            int max = 0;
            for (int i = 1; i < N; i++) {
                if (elementarySort.less(uq[max], uq[i])) {
                    max = i;
                }
            }
            elementarySort.exch(uq, max, N-1);
            return uq[--N];
        }
    }
    public static class maxPQ<item extends Comparable> {
        /**
         * priority queue using binary heap implementation
         */
        private item[] pq; //needs to start at index 1, not 0
        private int N; //denotes last index
        public maxPQ(int capacity) {
            pq = (item[]) new Comparable[capacity + 1];
        }
        public maxPQ(Comparable[] list) {
            pq = (item[]) new Comparable[list.length + 1];
            for (int i = 1; i < N + 1; i++) {
                pq[i] = (item)list[i - 1];
            }
        }
        public void insert(item v) {
            pq[++N] = v;
            swim(N);
        }
        public item delMax() {
            item result = pq[1];
            exch(1, N--);
            sink(1);
            pq[N + 1] = null;
            return result;
        }
        private void swim(int k) {
            // swim up new inserted children until smaller than its parent
            while (k > 1 && less(k/2, k)) {
                exch(k/2, k);
                k = k/2;
            }
        }
        public void sink(int k) {
            while(2*k <= N) {
                int leftChild = 2 * k;
                int maxChild = leftChild;
                if (leftChild < N && less(leftChild, leftChild + 1)) { //right children exists
                    maxChild = leftChild + 1;
                }
                if (!less(k, maxChild)) {
                    break;
                }
                exch(k, maxChild);
                k = maxChild;
            }
        }
        private boolean less(int a, int b) {
            return pq[a].compareTo(pq[b]) < 0;
        }
        private void exch(int a, int b){
            item temp = pq[b];
            pq[b] = pq[a];
            pq[a] = temp;
        }
    }
}
