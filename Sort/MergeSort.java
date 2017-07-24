package tianming.Sort;

/**
 * Created by tianming on 6/20/17.
 */

/**
 * merge sort is optimal for comparison (tree) based sorting algorithm
 * 2^h >= N! -> h >= log(N!) = Nlog(N)
 */
public class mergeSort {

    private static int cutoff = 5;

    private static void merge(Comparable[] list, Comparable[] aux, int lo, int mid, int hi) {
        assert isSorted(list, lo, mid);
        assert isSorted(list, mid, hi);
        for (int i = lo; i <= hi; i++)
            aux[i] = list[i];
        int k = lo, i = lo, j = mid + 1;
        for (; k <= hi; k++) {
            if (i > mid) list[k] = aux[j++];
            else if (j > hi) list[k] = aux[i++];
            else if (ElementarySort.less(aux[i], aux[j])) list[k] = aux[i++];
            else list[k] = aux[j++];
        }
        assert isSorted(list, lo, hi);
    }

    private static void sort(Comparable[] list, Comparable[] aux, int lo, int hi) {
        if (hi <= lo + cutoff) {
            ElementarySort.insertionSort(list);
        }
        int mid = (lo + hi) / 2;
        sort(list, aux, lo, mid);
        sort(list, aux, mid, hi);
        merge(list, aux, lo, mid, hi);
    }

    private static boolean isSorted(Comparable[] list, int lo, int hi) {
        for (int i = lo; i < hi; i++) {
            if (ElementarySort.less(list[i+1], list[i])) return false;
        }
        return true;
    }

    public void sort(Comparable[] list) {
        Comparable[] aux = new Comparable[list.length];
        sort(list, aux, 0, list.length - 1);
    }
}
