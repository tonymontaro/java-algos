package CSES_FI.DP;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.StringTokenizer;

public class ArrayDescription {
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
        for (Integer[] row: ways) Arrays.fill(row, 0);

        out.println(solve());

        out.close();
    }

    static int solve() {
        if (n == 1) return (arr[0] == 0) ? upperBound : 1;
        if (arr[0] != 0) {
            for (int i: new int[]{-1, 0, 1}) {
                int num = arr[0] + i;
                if (num > 0 && num <= upperBound) ways[0][num] = 1;
            }
        } else {
            Arrays.fill(ways[0], 3);
            ways[0][0] = 0;
            ways[0][1] = ways[0][upperBound] = 2;
        }

        for (int idx = 1; idx < n-1; idx++) {
            if (arr[idx] != 0) {
                int tmp = ways[idx - 1][arr[idx]];
                for (int i: new int[]{-1, 0, 1}) {
                    int num = arr[idx] + i;
                    if (num > 0 && num <= upperBound) ways[idx][num] = tmp;
                }
            } else {
                for (int pos = 1; pos <= upperBound; pos++) fill(idx, pos);
            }
        }
//        for (Integer[] row: ways) out.println(Arrays.toString(row));
        int result = 0;
        if (arr[n - 1] != 0) result = ways[n - 2][arr[n - 1]];
        else for (int i = 1; i <= upperBound; i++) result = (result + ways[n - 2][i]) % MOD;
        return result;
    }

    static void fill(int idx, int val) {
        int res = 0;
        for (int i: new int[]{-1, 0, 1}) {
            int num = val + i;
            if (num > 0 && num <= upperBound) res = (res + ways[idx - 1][num]) % MOD;
        }
        ways[idx][val] = res;
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


