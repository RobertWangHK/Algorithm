package String;

/**
 * Created by rober on 7/18/2017.
 */
public class ElementarySort {

    static int R = 256;    // stands for unicode -> 256 different characters

    public static void keyIndexSort(int[] a, int R) {
        int N = a.length;
        int[] temp = new int[N];
        int[] count = new int[R + 1];

        for (int i = 0; i < N; i++) {
            count[a[i] + 1]++;
        }
        for (int r = 0; r < R; r++) {
            count[r+1] += count[r];
        }
        for (int i = 0; i < N; i++) {
            temp[count[a[i]]++] = a[i];
        }
        for (int i = 0; i < N; i++) {
            a[i] = temp[i];
        }
    }

    /**
     * LSD sort, key must be fixed length
     * has to examine all key characters while MSD only needs to
     * recursive when duplicate keys encountered
     */
    public static void LSDSort(String[] a, int w) {
        int N = a.length;
        String[] aux = new String[N];

        // iterate from LSB to MSB
        for (int d = w-1; d >= 0; d--) {
            int[] count = new int[R + 1];
            for (int i = 0; i < N; i++) {
                count[a[i].charAt(d)+1]++;
            }
            for (int r = 0; r < R; r++) {
                count[r+1] += count[r];
            }
            for (int i = 0; i < N; i++) {
                aux[count[a[i].charAt(d)]++] = a[i];
            }
            for (int i = 0; i< N; i++) {
                a[i] = aux[i];
            }
        }
    }

    /**
     * MSD sort is in a recursive manner, can deal with unfixed string
     * sort problem, introduce new -1 character denoting end of string,
     * so we treat -1 as normal character that goes to the top front
     * 1> R = 256 + 1
     * 2> count[] size = R + 2
     * 3> alter boundary judgement statement
     *
     * problem is that it has to maintain count array for each procedure
     * call, so if tiny array -> lots of wasted space
     * recursive is done on each duplicate character (keys), so its performacne
     * depends on keys, worst case all equal
     */
    public static void MSDSort(String[] a) {
        String[] aux = new String[a.length];
        MSDSort(a, aux, 0, a.length, 0);
    }

    private static void MSDSort(String[] a, String[] aux, int lo, int hi, int d) {
        if (hi <= lo) return;
        if (hi - lo <= 3) {
            InsertionSort(a, lo, hi, d);
            return;
        }
        // count is local to the procedure
        int[] count = new int[R + 2];

        // count array items add up to hi - lo
        for (int i = lo; i <= hi; i++) {
            count[charAt(a[i], d) + 2]++;
        }

        // compensate for -1
        for (int r = 0; r < R + 1; r++) {
            count[r+1] += count[r];
        }

        // compensate for the extra -1 character
        for (int i = lo; i<= hi; i++) {
            aux[count[charAt(a[i], d) + 1]++] = a[i];
        }

        // copy to original
        for (int i = lo; i< hi; i++) {
            a[i] = aux[i - lo];
        }

        // recursive call is done on each subarray set (duplicate character)
        for (int r = 0; r < R; r++) {
            MSDSort(a, aux, lo + count[r], lo + count[r+1], d+1);
        }
    }

    public static void threeWayQuickSort(String[] a) {
        threeWayQuickSort(a, 0, a.length, 0);
    }

    /**
     * smaller -- lt (exclusive) -- equal -- gt (inclusive) -- greater
     */
    private static void threeWayQuickSort(String[] a, int lo, int hi, int d) {
        if (hi <= lo) return;
        int lt = lo, gt = hi;
        int v = charAt(a[lo], d);
        int i = lo + 1;

        while (i <= gt) {
            int t = charAt(a[i], d);
            if (t < v) exch(a, lt++, i++);
            else if (t > v) exch(a, i, gt--);
            else i++;
        }

        threeWayQuickSort(a, lo, lt - 1, d);
        if (gt > lt) threeWayQuickSort(a, lt, gt, d + 1);
        threeWayQuickSort(a, gt + 1, hi, d);
    }

    private static int charAt(String a, int d) {
        if (d < a.length()) return a.charAt(d);
        return -1;
    }


    public static void InsertionSort(String[] a, int lo, int hi, int d) {
        for (int i = lo; i <= hi; i++) {
            for (int j = i; j > lo && less(a[j], a[j-1], d); j--) {
                exch(a, j, j-1);
            }
        }
    }

    public static boolean less(String v, String w, int d) {
        return v.substring(d).compareTo(w.substring(d)) < 0;
    }
    public static void exch (Object[] list, int i, int j) {
        Object temp = list[j];
        list[j] = list[i];
        list[i] = temp;
    }
}
