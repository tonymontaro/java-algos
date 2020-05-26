package CodeNCode.DynamicProgramming2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.StringTokenizer;

// https://codeforces.com/problemset/problem/474/D
public class Flowers_474D {
    static PrintWriter out;
    static CF_Reader in;

    public static void main(String[] args) throws IOException {
        out = new PrintWriter(new OutputStreamWriter(System.out));
        in = new CF_Reader();

        int MAX = 100001;
        int MOD = 1000000007;
        int[] dp = new int[MAX];
        dp[0] = 1;
        int[] prefix = new int[MAX];

        int tests = in.intNext();
        int k = in.intNext();

        for (int i = 1; i < MAX; i++) {
            dp[i] = dp[i - 1];
            if (i >= k) dp[i] = (dp[i] + dp[i - k]) % MOD;
            prefix[i] = (prefix[i-1] + dp[i]) % MOD;
        }
        StringBuilder result = new StringBuilder();
        for (int t = 0; t < tests; t++) {
            int a = in.intNext() - 1;
            int b = in.intNext();
            result.append((prefix[b] - prefix[a] + MOD) % MOD).append("\n");
        }
        out.println(result);
        out.close();
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
