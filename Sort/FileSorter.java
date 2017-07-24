package tianming.Sort;
import java.io.File;

/**
 * Created by tianming on 6/19/17.
 */
public class FileSorter {
    public static void main(String[] args) {
        File directory = new File(args[0]);
        File[] files = directory.listFiles();
    }
}
