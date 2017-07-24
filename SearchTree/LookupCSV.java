import java.util.TreeMap;

/**
 * Created by rober on 7/9/2017.
 */
public class LookupCSV {

    public static void main(String[] args) {
        int keyField = Integer.parseInt(args[1]);
        int valField = Integer.parseInt(args[2]);

        TreeMap<String, String> tm = new TreeMap<>();
        In in = new In(args[0]);
        while (!in.isEmpty()) {
            String line = in.readLine();
            String[] tokens = line.split(",");
            String key = tokens[keyField];
            String val = tokens[valField];
            tm.put(key, val);
        }

        while (!StdIn.isEmpty()) {
            String s = StdIn.readString();
            if (tm.containsValue(s)) StdOut.println(tm.get(s));
            else StdOut.println("not found");
        }

    }
}
