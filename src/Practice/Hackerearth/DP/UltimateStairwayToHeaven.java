package Practice.Hackerearth.DP;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.StringTokenizer;

public class UltimateStairwayToHeaven {
    static PrintWriter out;
    static CF_Reader in;
    static int MOD = 1000000007;

    public static void main(String[] args) throws IOException {
        // O(n) time and space
        out = new PrintWriter(new OutputStreamWriter(System.out));
        in = new CF_Reader();

        int n = in.intNext();
        int[] arr = in.nextIntArrayReversed(n);
        out.print(solve(n, arr));

        out.close();
    }

    static int solve(int n, int[] arr) {
        int[] dp = new int[n + 1];
        dp[1] = 1;

        for (int i = 2; i <= n; i++) {
            int start = i - 1;
            int end = Math.max(0, (i - arr[i-1] - 1));
            dp[i] = (dp[i - 1] + (dp[start] - dp[end] + MOD) % MOD) % MOD;
        }

        return (dp[n] - dp[n-1] + MOD) % MOD;
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

        public int[] nextIntArrayReversed(final int n) throws IOException {
            final int[] a = new int[n];
            for (int i = 0; i < n; i++)
                a[n - i - 1] = intNext();
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


/*
// Long version
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.StringTokenizer;

class UltimateStairwayToHeaven {
    static PrintWriter out;
    static CF_Reader in;
    static long MOD = 1000000007;

    public static void main(String[] args) throws IOException {
        // O(n) time and space
        out = new PrintWriter(new OutputStreamWriter(System.out));
        in = new CF_Reader();

        int n = in.intNext();
        long[] arr = in.nextLongArrayReversed(n);
        out.print(solve(n, arr));

        out.close();
    }

    static long solve(int n, long[] arr) {
        long[] dp = new long[n + 1];
        dp[1] = 1;

        for (long i = 2; i <= n; i++) {
            long start = i - 1;
            long end = Math.max(0, (i - arr[(int)i-1] - 1));
            dp[(int)i] = (dp[(int)i - 1] + (dp[(int)start] - dp[(int)end] + MOD)) % MOD;
        }

        return (dp[n] - dp[n-1] + MOD) % MOD;
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

        public long[] nextLongArrayReversed(final int n) throws IOException {
            final long[] a = new long[n];
            for (int i = 0; i < n; i++)
                a[n - i - 1] = longNext();
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


 */