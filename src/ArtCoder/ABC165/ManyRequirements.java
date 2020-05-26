package ArtCoder.ABC165;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.StringTokenizer;

public class ManyRequirements {
    static PrintWriter out;
    static int q;
    static int n;
    static int m;
    static int[][] queries;
    static long best;

    public static void main(String[] args) throws IOException {
        out = new PrintWriter(new OutputStreamWriter(System.out));
        CF_Reader in = new CF_Reader();

        n = in.intNext();
        m = in.intNext();
        q = in.intNext();
        queries = new int[q][4];

        for (int i = 0; i < q; i++) {
            queries[i] = in.nextIntArray(4);
        }

        int[] nums = new int[n];
        solve(0, 1, nums);
        out.println(best);

        out.close();
    }

    static void solve(int size, int val, int[] nums) {
        if (size == n) {
            long total = 0;
            for (int i = 0; i < q; i++) {
                int a = queries[i][0], b = queries[i][1], c = queries[i][2], d = queries[i][3];
                if (nums[b-1] - nums[a-1] == c) total += d;
            }
            best = Math.max(best, total);
        } else {
            for (int i = val; i <= m; i++) {
                nums[size] = i;
                solve(size + 1, i, nums);
            }
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
