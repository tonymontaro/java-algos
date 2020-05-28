package CodeNCode.DynamicProgramming2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.StringTokenizer;

public class DigitSum {
    static PrintWriter out;
    static CF_Reader in;
    static long[][][] dp;

    public static void main(String[] args) throws IOException {
        out = new PrintWriter(new OutputStreamWriter(System.out));
        in = new CF_Reader();

//        out.println(n_digits_sum(0, 1, 0));
//        out.println(n_digits_sum(0, 2, 0));
//        out.println(n_digits_sum(0, 3, 0));
//        out.println(n_digits_sum(0, 6, 0));
        // 345 -> 3417

//        out.println(digits_sum_up_to_n(0, 3, 0, true, new int[]{1, 0, 0}));
//        out.println(digits_sum_up_to_n("345"));
//        out.println(digits_sum_up_to_n("100"));
//        out.println(digits_sum_up_to_n("9"));
        out.println(digits_sum_up_to_n("35"));

        out.close();
    }

    static long n_digits_sum(int pos, int maxN, long sum) {
        if (pos >= maxN) return sum;
        long total = 0;
        for (int i = 0; i < 10; i++) total += n_digits_sum(pos + 1, maxN, sum + i);
        return total;
    }

    static long digits_sum_up_to_n(String n) {
        int ln = n.length();
        int[] limitArr = new int[ln];
        dp = new long[ln][ln * 9][2];
        for(long[][] a: dp) for (long[] b: a) Arrays.fill(b, -1);
        for (int i = 0; i < n.length(); i++) limitArr[i] = n.charAt(i) - '0';
        return digits_sum_up_to_n(0, n.length(), 0, 1, limitArr);
    }

    static long digits_sum_up_to_n(int pos, int maxN, int sum, int limit, int[] limitArr) {
        if (pos >= maxN) return sum;
        if (dp[pos][sum][limit] == -1) {
            int end = (limit == 1) ? limitArr[pos] : 10;
            long total = 0;
            for (int i = 0; i < end; i++) {
                total += digits_sum_up_to_n(pos + 1, maxN, sum + i, 0, limitArr);
            }
            if (limit == 1) total += digits_sum_up_to_n(pos + 1, maxN, sum + end, 1, limitArr);
            dp[pos][sum][limit] = total;
        }

        return dp[pos][sum][limit];
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
