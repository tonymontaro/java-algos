package Practice.Hackerearth.CodeMonk_CP1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.StringTokenizer;

public class MonkAndInversions {
    static PrintWriter out;
    static CF_Reader in;

    static int n;
    public static void main(String[] args) throws IOException {
        out = new PrintWriter(new OutputStreamWriter(System.out));
        in = new CF_Reader();

        int tests = in.intNext();

        for (int t = 0; t < tests; t++) {
            n = in.intNext();
            int[][] grid = new int[n][n];
            for (int i = 0; i < n; i++) grid[i] = in.nextIntArray(n);
            int result = 0;
            for (int row = 0; row < n; row++) for (int col = 0; col < n; col++) result += check(row, col, grid);
            out.println(result);
        }

        out.close();
    }
    static int check(int row, int col, int[][] grid) {
        int res = 0;
        int val = grid[row][col];
        for (int r = row; r < n; r++) {
            for (int c = col; c < n; c++) if (grid[r][c] < val) res++;
        }
        return res;
    }
}

class CF_Reader {
    BufferedReader br;
    StringTokenizer st;

    public CF_Reader() throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in));
    }

    String next() throws IOException {
        while (st == null || !st.hasMoreTokens())
            st = new StringTokenizer(br.readLine().trim());
        return st.nextToken();
    }

    long longNext() throws IOException {
        return Long.parseLong(next());
    }

    int intNext() throws IOException {
        return Integer.parseInt(next());
    }

    double doubleNext() throws IOException {
        return Double.parseDouble(next());
    }

    char charNext() throws IOException {
        return next().charAt(0);
    }

    public int[] nextIntArray(final int n) throws IOException {
        final int[] a = new int[n];
        for (int i = 0; i < n; i++)
            a[i] = intNext();
        return a;
    }

    public long[] nextLongArray(final int n) throws IOException {
        final long[] a = new long[n];
        for (int i = 0; i < n; i++)
            a[i] = longNext();
        return a;
    }

    String line() throws IOException {
        return br.readLine().trim();
    }
}
