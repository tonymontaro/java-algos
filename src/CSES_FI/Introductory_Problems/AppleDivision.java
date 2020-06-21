package CSES_FI.Introductory_Problems;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.StringTokenizer;

public class AppleDivision {
    static PrintWriter out;
    static CF_Reader in;
    static long total = 0;
    static int n;
    static long[] nums;

    public static void main(String[] args) throws IOException {
        out = new PrintWriter(new OutputStreamWriter(System.out));
        in = new CF_Reader();

        n = in.intNext();
        nums = in.nextLongArray(n);
        out.println(minGroupDifference(0, 0));

        out.close();
    }

    static long minGroupDifference(int idx, long currentSum) {
        if (idx >= n) {
            long diff = total - currentSum;
            return Math.abs(currentSum - diff);
        }

        long added = minGroupDifference(idx + 1, currentSum + nums[idx]);
        long skipped = minGroupDifference(idx + 1, currentSum);

        return Math.min(added, skipped);
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
                total += a[i];
            }
            return a;
        }

        String line() throws IOException {
            return br.readLine().trim();
        }
    }
}


