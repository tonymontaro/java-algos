
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.StringTokenizer;

public class GridPaths {
    static PrintWriter out;
    static CF_Reader in;
    static int[][] grid;
    static int n;

    public static void main(String[] args) throws IOException {
        out = new PrintWriter(new OutputStreamWriter(System.out));
        in = new CF_Reader();

        n = in.intNext();
        grid = new int[n][n];
        for (int r = 0; r < n; r++) {
            char[] line = in.line().toCharArray();
            for (int c = 0; c < n; c++) {
                grid[r][c] = (line[c] == '*') ? -1 : 0;
            }
        }
        out.println(numPaths());

        out.close();
    }

    static int numPaths() {
        // grid[r][c] = -1 or 0
        if (grid[n-1][n-1] == -1) return 0;
        int MOD = 1000000007;
        grid[n-1][n-1] = 1;
        for (int row = n-1; row >= 0; row--) {
            for (int col = n - 1; col >= 0; col--) {
                if (grid[row][col] == -1) grid[row][col] = 0;
                else grid[row][col] = (grid[row][col] + getVal(row + 1, col) + getVal(row, col + 1)) % MOD;
            }
        }
        return grid[0][0];
    }

    static int getVal(int row, int col) {
        if (row >= n || col >= n) return 0;
        return grid[row][col];
    }

    static class CF_Reader {
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
}


