package String;

import java.util.Arrays;

/**
 * Created by rober on 7/20/2017.
 * Suffix array sort to implement keyword in context search mechanism
 */
public class StringApplication {
    /**
     * Find keyword in context
     * 1> Suffix array generation
     * 2> Sort the suffix sort using three-way sort so that duplicate
     * string parts come together
     * 3> Using binary search to find keyword in context
     */
    public static String KeywordInContext() {
        return null;
    }

    /**
     * Find longest repeated substring
     * 1> Suffix array generation
     * 2> Sort the suffix sort using three-way sort so that duplicate
     * string parts come together
     * 3> Using binary search to find duplicate keys together
     */
    public static String LongestRepeated(String s) {
        int N = s.length();
        String[] suffix = new String[N];
        for (int i = 0; i < N; i++) {
            suffix[i] = s.substring(i, N); // Constant time
        }

        // But if two copies of longest
        Arrays.sort(suffix); // NlogN time for sorting
        String lrs = "";

        for (int i = 0; i < N-1; i++) {
            int len = lcp(suffix[i], suffix[i+1]);
            if (len > lrs.length()) {
                lrs = suffix[i].substring(0, len);
            }
        }
        return lrs;
    }

    /**
     * After 1st, each time double the amount of characters of keys
     * this way, we can achieve constant compare (make use of reverse dict)
     * this algorithm is useful to solve suffix sort which might
     * have long duplicate longest matches.
     * e.g. abcdeabcde -> suffix arrays: a, a, ab, ab, abc, abc, abcd, abcd .... quadratic time
     * but with Manber MSD algorithm -> NlogN complexity
     */

    public static void LinearithmicSuffix(String[] a) {}

    private static int lcp(String a, String b) {
        for (int i = 0; i < Math.min(a.length(), b.length()); i++) {
            if (a.charAt(i) != b.charAt(i)) return i;
        }
        return Math.min(a.length(), b.length());
    }

}
