package Practice.Hackerearth.DP;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.StringTokenizer;

public class XsquareAndChocolatesBars {
    static PrintWriter out;
    static CF_Reader in;

    public static void main(String[] args) throws IOException {
        out = new PrintWriter(new OutputStreamWriter(System.out));
        in = new CF_Reader();

        int tc = in.intNext();
        for (int t = 0; t < tc; t++) {
            out.println(solve(in.next().toCharArray()));
        }

        out.close();
    }

    static int solve(char[] arr) {
        int ln = arr.length;
        int maxNum = 0;

        int[] dp = new int[ln + 1];
        for (int i = 2; i < ln; i++) {
            if (arr[i] != arr[i-1] || arr[i] != arr[i-2]) {
                dp[i+1] = dp[i-2] + 3;
                maxNum = Math.max(maxNum, dp[i+1]);
            } else dp[i+1] = 0;
        }

        return ln - (maxNum - (maxNum % 3));
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


