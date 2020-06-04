package CodeForces.CF_1363;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.StringTokenizer;

public class SubsequenceHate {
    static PrintWriter out;
    static CF_Reader in;

    public static void main(String[] args) throws IOException {
        out = new PrintWriter(new OutputStreamWriter(System.out));
        in = new CF_Reader();

        int tests = in.intNext();
        for (int t = 0; t < tests; t++) {
            char[] arr = in.next().toCharArray();
            int ln = arr.length;
            out.println(solve(arr));
        }

        out.close();
    }

    static int solve(char[] arr) {
        return Math.min(solve(0, arr, '0', 0), solve(0, arr, '1', 0));
    }

    static int solve(int idx, char[] arr, char prev, int change) {
        if (idx >= arr.length) return 0;
        if (change == 1) {
            if (arr[idx] == prev) return solve(idx + 1, arr, prev, change);
            else return 1 + solve(idx + 1, arr, prev, change);
        } else {
            if (prev == arr[idx]) return solve(idx + 1, arr, prev, change);
            return solve(idx + 1, arr, arr[idx], change + 1);
        }
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


