import java.io.File;
import java.util.TreeMap;

/**
 * Created by rober on 7/9/2017.
 */
public class FileIndex {
    public static void main(String[] args) {
        TreeMap<String, SET<File>> tm = new TreeMap<>();

        for (String filename: args) {
            File file = new File(filename);
            In in = new In(file);
            while (!in.isEmpty()) {
                String s = in.readString();
                if (!tm.containsKey(s)) {
                    tm.put(s, new SET<File>());
                }
                tm.get(s).add(file);
            }
        }

        while (!StdIn.isEmpty()) {
            String query = StdIn.readString();
            StdOut.print(tm.get(query));
        }

    }
}
