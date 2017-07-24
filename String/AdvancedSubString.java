package String;

/**
 * Created by rober on 7/23/2017.
 */
public class AdvancedSubString {

    /**
     * Mathc patten from right to left
     * case 1: if char not in patten, align pattn with next character
     * case 2: if char in patten, align patten's character with the
     * char position, this step is done by querying a predefined index table.
     */
    public static int BoyerMoore (String patten, String data) {return 0;}
    public static int RabinKarp (String patten, String data) {
        long pattenHash = hash(patten, patten.length());
        return search(data, pattenHash);
    }

    //R is digit num
    private static long hash(String key, int M) {
        long h = 0;
        for (int j = 0; j < M; j++) {
            // still get the same result
            h = (10 * h + key.charAt(j)) % 997;
        }
        return h;
    }

    // Potential false collision because of the wrong choice of modular value Q
    private static int search(String data, long pattenHash) {
        int N = data.length();
        long hash = hash(data, 5);
        if (hash == pattenHash) return 0;
        for (int i = 0; i < data.length(); i++) {
            hash = hash + 997 - data.charAt(i - 5) % 997;
            hash = (hash + data.charAt(i)) % 997;
            if (hash == pattenHash) return i;
        }
        return data.length();
    }
}
