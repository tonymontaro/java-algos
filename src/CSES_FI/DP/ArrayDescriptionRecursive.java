import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.StringTokenizer;

public class ArrayDescriptionRecursive {
    static PrintWriter out;
    static CF_Reader in;
    static int upperBound;
    static int n;
    static int[] arr;
    static Integer[][] ways;
    static int MOD = 1000000007;

    public static void main(String[] args) throws IOException {
        out = new PrintWriter(new OutputStreamWriter(System.out));
        in = new CF_Reader();

        n = in.intNext();
        upperBound = in.intNext();
        arr = in.nextIntArray(n);

        ways = new Integer[n][upperBound + 1];
        for (Integer[] row: ways) Arrays.fill(row, null);
        out.println(solve());
        for (Integer[] row: ways) out.println(Arrays.toString(row));

        out.close();
    }

    static int solve() {
        if (arr[0] != 0) return solve(1, arr[0]);
        int res = 0;
        for (int i = 1; i <= upperBound; i++) {
            res = (res + solve(1, i)) % MOD;;
        }
        return res;
    }

    static int solve(int idx, int prev) {
        if (prev < 1 || prev > upperBound) return 0;
        if (idx >= n) return 1;
        if (ways[idx][prev] == null) {
            int res = 0;
            if (arr[idx] != 0) res = (Math.abs(arr[idx] - prev) > 1) ? 0 : solve(idx + 1, arr[idx]);
            else {
                for (int i: new int[]{-1, 0, 1}) {
                    res = (res + solve(idx + 1, prev + i)) % MOD;
                }
            }
            ways[idx][prev] = res;
        }
        return ways[idx][prev];
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