package CodeForces.CF_1370;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.StringTokenizer;


public class OddEvenSubsequence {
    static PrintWriter out;
    static CF_Reader in;
    static long[] arr;
    static int lim;
    static int n;
    static long res = Long.MAX_VALUE;

    public static void main(String[] args) throws IOException {
        out = new PrintWriter(new OutputStreamWriter(System.out));
        in = new CF_Reader();


        n = in.intNext();
        int k = in.intNext();
        lim = (k + 1) / 2;
        arr = in.nextLongArray(n);
        solve(0, 0, Long.MIN_VALUE);
        if (k % 2 == 1) {
            n--;
            lim--;
            solve(1, 0, Long.MIN_VALUE);
        }


        out.println(res);
        out.close();
    }

    static void solve(int idx, int count, long maxSoFar) {
        if (count == lim) {
            res = Math.min(res, maxSoFar);
            return;
        }
        if (idx >= n) return;
        // skip
        solve(idx + 1, count, maxSoFar);
        // add
        maxSoFar = Math.max(maxSoFar, arr[idx]);
        solve(idx + 2, count + 1, maxSoFar);
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


