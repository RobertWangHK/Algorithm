package tianming.Sort;
import java.util.Comparator;
import java.util.Random;

/**
 * Created by rober on 6/19/2017.
 */
public class ElementarySort {
    //selection sort runs in quadratic time regardless of initial input arrays
    //selection sort is not stable, orders of equal key items might not preserve
    public static void selectionSort(Comparable[] list) {
        int length = list.length;
        for (int i = 0; i < length; i++) {
            int min = i;
            for (int j = i+1; j < length; j++) {
                if (less(list[j], list[i]))
                    min = j;
            }
            if (min != i)
                exch(list, i, min);
        }
    }

    //for partially sorted array (linear number of inversions), insertionSort runs in linear time
    public static void insertionSort(Comparable[] list) {
        int length = list.length;
        for (int i = 0; i < length; i++) {
            for (int j = i; j > 0; j--) {
                if (less(list[j], list[i])) {
                    exch(list, j, i);
                }
                else {
                    break;
                }
            }
        }
    }

    /**initial large step -> less comparision and exchanges to make
     * later small step -> already partially sorted
     * shell sort is not stable, long distance traverse of items
     */
    public static void shellSort(Comparable[] list) {
        int length = list.length;
        int s = 1;
        while(s <= length / 3) {
            s = s * 3 + 1; //index
        }
        while(s >= 1) {
            for (int i = s; i < length; i++) {
                for (int j = i; j >= s; j-= s) {
                    if (less(list[j], list[j-s])) {
                        exch(list, j, j-s);
                    }
                    else {
                        break;
                    }
                }
            }
            s = s / 3; //retrieve index for sorting
        }
    }

    //generate uniformed shuffled arrays in linear time
    //the shuffle range should be 0 and i, not the whole array
    public static void shuffleSort(Comparable[] list) {
        int length = list.length;
        Random r = new Random();
        for (int i = 0; i < length; i++) {
            int temp = r.nextInt(i + 1);
            exch(list, i, temp);
        }
    }

    //helper function for Comparable list & variables
    public static boolean less (Comparable a, Comparable b) {
        return a.compareTo(b) < 0;
    }
    public static void exch (Comparable[] list, int i, int j) {
        Comparable temp = list[j];
        list[j] = list[i];
        list[i] = temp;
    }

    //helper function for normal Object list & variables, with comparator c
    public static boolean less (Comparator c, Object a, Object b) {
        return c.compare(a, b) < 0;
    }
    public static void exch (Object[] list, int i, int j) {
        Object temp = list[j];
        list[j] = list[i];
        list[i] = temp;
    }
}
