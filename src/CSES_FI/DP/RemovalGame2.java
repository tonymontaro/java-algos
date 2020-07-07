package CSES_FI.DP;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.StringTokenizer;

public class RemovalGame2 {
    static PrintWriter out;
    static CF_Reader in;
    static long[] prefixSum;
    static long[] arr;
    static Long[][] cache;

    public static void main(String[] args) throws IOException {
        out = new PrintWriter(new OutputStreamWriter(System.out));
        in = new CF_Reader();

        int n = in.intNext();
        prefixSum = new long[n + 1];
        arr = in.nextLongArray(n);

        long startTime = System.nanoTime();
        cache = new Long[n + 1][n + 1];
        for (Long[] row: cache) Arrays.fill(row, null);
        out.println(solve(n));
        long timeElapsed = System.nanoTime() - startTime;
        System.out.println("Execution time in milliseconds: " + timeElapsed / 1000000);
//        for (Long[] row: cache) out.println(Arrays.toString(row));

        out.close();
    }

    static long solve(int n) {
        for (int k = 0; k < n; k++) {
            for (int high = k + 1; high <= n; high++) {
                int low = high - k;
                if (high - low <= 1) cache[low][high] = (low == high) ? arr[low-1] : Math.max(arr[low - 1], arr[high - 1]);
                else {
                    long first = arr[low - 1] + (prefixSum[high] - prefixSum[low]) - cache[low + 1][high];
                    long last = arr[high - 1] + (prefixSum[high - 1] - prefixSum[low - 1]) - cache[low][high - 1];
                    cache[low][high] = Math.max(first, last);
                }
            }
        }
        return cache[1][n];
    }

    static long bestScore(int low, int high) {
        if (high - low <= 1) return (low == high) ? arr[low-1] : Math.max(arr[low - 1], arr[high - 1]);
        long first = arr[low - 1] + (prefixSum[high] - prefixSum[low]) - cache[low + 1][high];
        long last = arr[high - 1] + (prefixSum[high - 1] - prefixSum[low - 1]) - cache[low][high - 1];
        return Math.max(first, last);
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
            for (int i = 0; i < n; i++) {
                a[i] = longNext();
                prefixSum[i + 1] = prefixSum[i] + a[i];
            }
            return a;
        }

        String line() throws IOException {
            return br.readLine().trim();
        }
    }
}


