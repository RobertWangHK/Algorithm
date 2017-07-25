package DataCompression;

/**
 * Created by rober on 7/25/2017.
 */
public class RunLength {
    private final static int R = 256;
    private final static int lgR = 8;

    public static void compress() {
        char run = 0;
        boolean old = false;

        while (!BinaryStdIn.isEmpty()) {
            boolean b = BinaryStdIn.readBoolean();
            // switch to new boolean
            if (b != old) {
                BinaryStdOut.write(run, lgR);
                run = 1;
                old = b;
            }
            else {
                /**
                 * num of b overceeds R limit -> output a empty
                 * denoting continuous segment & continue run++
                 */
                if (run == R - 1) {
                    BinaryStdOut.write(run, lgR);
                    run = 0;
                    BinaryStdOut.write(run, lgR);
                }
                run++;
            }
        }
        BinaryStdOut.write(run, lgR);
        BinaryStdOut.close();
    }

    public static void expand() {
        boolean b = false;
        while (!BinaryStdIn.isEmpty()) {
            int run = BinaryStdIn.readInt(lgR);
            for (int i = 0; i < run; i++)
                BinaryStdOut.write(b);
            if (run != 0) b = !b;
        }
        BinaryStdOut.close();
    }

}
