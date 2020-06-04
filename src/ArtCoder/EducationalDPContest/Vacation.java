package ArtCoder.EducationalDPContest;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Vacation {
    static PrintWriter out;
    static CF_Reader in;
    static long[][] arr;
    static int n;
    static Long[][] dp;

    public static void main(String[] args) throws IOException {
        out = new PrintWriter(new OutputStreamWriter(System.out));
        in = new CF_Reader();

        n = in.intNext();
        arr = new long[n][3];
        dp = new Long[n][3];
        for(Long[] row: dp) Arrays.fill(row, null);
        for (int i = 0; i < n; i++) {
            arr[i] = in.nextLongArray(3);
        }
        long best = Long.MIN_VALUE;
        for (int i = 0; i < 3; i++) {
            best = Math.max(best, solve(0, i));
        }
        out.println(best);
        out.close();
    }

    static long solve(int idx, int prev) {
        if (idx >= n) return 0;

        if (dp[idx][prev] == null) {
            long best = Long.MIN_VALUE;
            for (int i = 0; i < 3; i++) {
                if (i != prev) best = Math.max(best, arr[idx][i] + solve(idx + 1, i));
            }
            dp[idx][prev] = best;
        }
        return dp[idx][prev];
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
