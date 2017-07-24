/**
 * Created by rober on 6/22/2017.
 */
public class quickSort {
    private static int cut_off = 3;
    private static int partition(Comparable[] list, int lo, int hi) {
        int i = lo + 1, j = hi;
        while(i < j) {
            while (elementarySort.less(list[i], list[lo])) {
                i += 1;
            }
            while (elementarySort.less(list[lo], list[j])) {
                j -= 1;
            }
            elementarySort.exch(list, i, j);
        }
        elementarySort.exch(list, lo, j);
        return j; // return the partition point
    }

    public static void sort(Comparable[] list) {
        //or can use median of median 3 method to get partition value
        elementarySort.shuffleSort(list);
        int length = list.length;
        //sort(list, 0, length - 1);
        threeSort(list, 0, length - 1);
    }

    private static void sort(Comparable[] list, int lo, int hi) {
        if (lo == hi) return;
        if (lo + cut_off >= hi) {
            elementarySort.insertionSort(list);
        }
        int partition = partition(list, lo, hi);
        sort(list, lo, partition - 1);
        sort(list, partition + 1, hi);
    }

    public static void threeSort(Comparable[] list, int lo, int hi) {
        if (lo == hi) return;
        int lt = lo, gt = hi, i = lo;
        Comparable partition = list[lo];

        while (i < gt) {
            if (list[i].compareTo(partition) > 0) {
                //move less to the left of lt and keep lt pointing to the partition
                elementarySort.exch(list, i++, lt++);
            }
            else if (list[i].compareTo(partition) > 0) {
                //move greater to the right of gt
                elementarySort.exch(list, i, gt--);
            }
            else i++;
        }

        threeSort(list, lo, lt);
        threeSort(list, gt, hi);
    }

    public static Comparable select(Comparable[] list, int k) {
        elementarySort.shuffleSort(list);
        int lo = 0, hi = list.length - 1;
        while (hi > lo) {
            int j = partition(list, lo, hi);
            if (k > j) lo = j + 1;
            else if (k < j) hi = j -1;
            else return list[j];
        }
        return list[hi];
    }
}
