
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.StringTokenizer;

public class TwoKnights {
    static PrintWriter out;
    static CF_Reader in;

    public static void main(String[] args) throws IOException {
        out = new PrintWriter(new OutputStreamWriter(System.out));
        in = new CF_Reader();

        int n = in.intNext();
        StringBuilder res = new StringBuilder();
        for (int i = 1; i <= n; i++) {
            res.append(solve(i)).append("\n");
        }
        out.println(res);

        out.close();
    }

    static long solve(int n) {
        if (n <= 1) return 0;
        long nSq = n * n;
        long total = (nSq * (nSq - 1)) / 2;
        long attacks = getAttacks(n);
        return total - attacks;
    }

    static long getAttacks(int n) {
        if (n <= 2) return 0;
        if (n <= 4) return n == 3 ? 8 : 24;
        int half = (n + 1) / 2;
        long firstRow = 2 + 3 + (half - 2) * 4;
        long secondRow = 3 + 4 + (half - 2) * 6;
        long thirdRow = 4 + 6 + (half - 2) * 8;
        long total = (firstRow + secondRow + (half - 2) * thirdRow) * 2;
        if (n % 2 == 1) total -= ((thirdRow) * 2 - 4);
        return total;
    }

    static int[][] directions;
    static long solve2(long n) {
        if (n <= 1) return 0;
        directions = new int[][]{{-1, -2}, {1, -2}, {-1, 2}, {1, 2}, {-2, -1}, {-2, 1}, {2, -1}, {2, 1}};
        long nSq = n * n;
        long total = (nSq * (nSq - 1)) / 2;
        long half = (n + 1) / 2;
        long attacks = 0;
        long[][] grid = new long[(int)n][(int)n];
        for (int r = 0; r < half; r++) {
            for (int c = 0; c < half; c++) {
                long attack = getAttack(r, c, n);
                attacks += (n % 2 == 1 && (r == half-1 || c == half-1)) ? attack : attack * 2;
                grid[r][c] = attack;
            }
        }
        if (n % 2 == 1) attacks -= getAttack((int)half-1, (int)half-1, n) / 2;
        for (long[] row: grid) out.println(Arrays.toString(row));
        out.printf("%d %d\n", total, attacks);
        total -= attacks;
        return total;
    }

    static long getAttack(int r, int c, long n) {
        long attack = 0;
        for (int[] coord: directions) {
            int row = coord[0] + r;
            int col = coord[1] + c;
            if (0 <= row && row < n && 0 <= col && col < n) attack++;
        }
        return attack;
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


