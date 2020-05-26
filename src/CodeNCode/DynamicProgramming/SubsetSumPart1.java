package CodeNCode.DynamicProgramming;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.HashSet;
import java.util.StringTokenizer;

public class SubsetSumPart1 {
    static PrintWriter out;
    static CF_Reader in;

    public static void main(String[] args) throws IOException {
        out = new PrintWriter(new OutputStreamWriter(System.out));
        in = new CF_Reader();

        out.println(solve(new int[]{2, 5, 3, 4}, 12));
        out.println(solve(new int[]{2, 5, 3}, 9));
        out.println(solve(new int[]{2, 9, 5, 8, 3, 4}, 12));

        out.close();
    }

    static boolean solve(int[] nums, int m) {
        boolean[] prev = new boolean[m + 1];
        for (int num: nums) {
            boolean[] dp = new boolean[m + 1];
            for (int i = 0; i <= m; i++) {
                if (i < num) dp[i] = prev[i];
                else if (i == num) dp[i] = true;
                else dp[i] = prev[i] || prev[i - num];
            }
            prev = dp;
        }
        return prev[m];
    }

    static boolean solve2(int[] nums, int m) {
        HashSet<Integer> seen = new HashSet<>();
        seen.add(0);
        int total = 0;
        for (int num: nums) {
            total += num;
            seen.add(total);
            if (seen.contains(total - m)) return true;
        }
        return false;
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
