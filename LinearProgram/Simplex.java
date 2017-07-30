/**
 * Created by rober on 7/30/2017.
 */
public class Simplex {
    private double[][] a;
    private int m, n;

    public Simplex(double[][] A, double[] b, double[] c) {
        m = b.length;
        n = c.length;

        // Equation coefficients
        a = new double[m + 1][m + n + 1];
        for (int i = 0; i < m; i++)
            for (int j = 0; j < n; j++)
                a[i][j] = A[i][j];

        // Slack variable coefficients.
        for (int j = n; j < m + n; j++) a[j - n][j] = 1.0;

        // Objective function coefficients
        for (int j = 0; j < n; j++) a[m][j] = c[j];

        // Equation values
        for (int i = 0; i < m; i++) a[i][m + n] = b[i];
    }

    // Choose next pivot column according to its coefficients in objective
    // function, e.g. positive
    private int bland() {
        for (int q = 0; q < m + n; q++)
            if (a[m][q] > 0) return q;
        return -1;
    }

    // Choose leaving row (used to substitute newly chosen column variable)
    // by smallest value / coefficient value
    private int minRatioRule(int q) {
        // q is the column number
        int p = -1;
        for (int i = 0; i < m; i++) {
            if (a[i][q] <= 0) continue;
            else if (p == -1) p = i; // initialize p to 1st row
            else if (a[i][m + n] / a[i][q] < a[p][m + n] / a[p][q])
                p = i;
        }
        return p;
    }

    // Given row p & column q, do the pivot by first
    public void pivot(int p, int q) {
        for (int i = 0; i <= m; i++) {
            for (int j = 0; j <= m + n; j++) {
                if (i != p && j != q) {
                    // Substitute a[i][q] by subtracting corresponding value
                    // of a[i][q] * a[p][j] / a[p][q] from a[i][j]
                    a[i][j] -= a[i][q] * a[p][j] / a[p][q];
                }
            }
        }
        for (int i = 0; i <= m; i++)
            if (i != p) a[i][q] = 0.0;
        for (int j = 0; j <= m+n; j++)
            if (j != q) a[p][j] /= a[p][q];
        a[p][q] = 1.0;
    }

    public void solve() {
        while (true) {
            int q = bland();
            if (q == -1) break;

            int p = minRatioRule(q);
            if (p == -1) break;
            pivot(p,q);
        }
    }
}
