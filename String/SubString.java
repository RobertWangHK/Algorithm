package String;

import Graph.In;

import java.security.InvalidKeyException;

/**
 * Created by rober on 7/23/2017.
 */
public class SubString {

    private static int R = 256;

    public static int brutForce(String patten, String data) {

        int i, N = data.length();
        int j, M = patten.length();

        for (i = 0, j = 0; i < N - M && j < M; i++) {
            if (data.charAt(i) == patten.charAt(j)) j++;
            else { i -= j; j = 0;}
        }
        if (j == M) return i -= M;
        else return N;
    }

    public static int KMPDFA(String patten, String data) {
        int[][] dfa;
        dfa = buildDFA(patten);
        int i, N = data.length();
        int j, M = patten.length();
        // i never gets decremented -> linear algorithm
        for (i = 0, j = 0; i < N && j < M; i++) {
            j = dfa[data.charAt(i)][j];
        }
        if (j == M) return i - M;
        else return N;
    }

    public int search(String patten, In in) {
        int i, j;
        int M = patten.length();
        int[][] dfa;
        dfa = buildDFA(patten);
        for (i = 0, j = 0; !in.isEmpty() && j < M; i++) {
            j = dfa[in.readChar()][j];
        }
        if (j == M) return i - M;
        else return -1;
    }

    // Construct a definite state machine for state transition
    private static int[][] buildDFA(String patten) {
        int M = patten.length();
        int[][] dfa = new int[R][M];

        // dfa is of the form: [character][state] -> next state
        dfa[patten.charAt(0)][0] = 1; // others are zero

        for (int X = 0, j = 1; j < M; j++) {
            for (int r = 0; r < R; r++) {
                dfa[r][j] = dfa[r][X];
            }
            dfa[patten.charAt(j)][j] = j + 1;
            //update X to what if charAt(j) is fit in X
            X = dfa[patten.charAt(j)][X];
        }
        return dfa;
    }
}
