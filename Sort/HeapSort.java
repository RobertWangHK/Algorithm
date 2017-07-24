/**
 * Created by rober on 6/29/2017.
 */
public class heapSort<item extends Comparable>{
    //first algorith that takes in place and needs NlogN in worst case
    private maxpriorityQueue.maxPQ<item> maxPQ;

    private void sort(Comparable[] list) {
        maxPQ = new maxpriorityQueue.maxPQ<item>(list);
        int lastIndex = list.length;
        /**
         * make pq in maxPQ a binary heap
         * iteratively sink root of subheaps (bottom up order)
         * the last root's index is N / 2
         */
        for (int i = lastIndex / 2; i >= 1; i--) {
            maxPQ.sink(i);
        }
        /**
         * continuously pop the max element from maxPQ to array
         * can directly call the delMax method
         */
        for (int i = lastIndex - 1; i >= 0; i--) {
            list[i] = maxPQ.delMax();
        }
    }
}
