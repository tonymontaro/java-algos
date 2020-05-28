package CodeNCode.DynamicProgramming2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.Comparator;
import java.util.StringTokenizer;

public class LetsGoRolling_incorrect {
    static PrintWriter out;
    static CF_Reader in;

    public static void main(String[] args) throws IOException {
        out = new PrintWriter(new OutputStreamWriter(System.out));
        in = new CF_Reader();


        int n = in.intNext();
        long[][] nums = new long[n][2];
        for (int i = 0; i < n; i++) {
            nums[i][0] = in.longNext();
            nums[i][1] = in.longNext();
        }
        Arrays.sort(nums, Comparator.comparingLong(o -> o[0]));
        long mark = nums[0][0];
        long total = nums[0][1];

        for (int i = 1; i < n; i++) {
            long markCost = nums[i][1];
            long distant = nums[i][0] - mark;
            if (markCost <= distant) {
                mark = nums[i][0];
                total += markCost;
            } else {
                total += distant;
            }
        }
        out.println(total);

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
