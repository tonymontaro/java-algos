package CodeNCode.DynamicProgramming2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.StringTokenizer;

public class LetsGoRolling {
    static PrintWriter out;
    static CF_Reader in;
    static long[][] nums;
    static Long[][] dp;

    public static void main(String[] args) throws IOException {
        out = new PrintWriter(new OutputStreamWriter(System.out));
        in = new CF_Reader();

        int n = in.intNext();
        nums = new long[n][2];
        for (int i = 0; i < n; i++) {
            nums[i][0] = in.longNext();
            nums[i][1] = in.longNext();
        }
        Arrays.sort(nums, Comparator.comparingLong(o -> o[0]));
        dp = new Long[3000][3000];
        for (Long[] row: dp) Arrays.fill(row, null);

        out.println(nums[0][1] + solve(1, 0));

        out.close();
    }

    static long solve(int idx, int pin) {
        if (idx >= nums.length) return 0;
        if (dp[idx][pin] == null) {
            long pinCurrent = nums[idx][1] + solve(idx + 1, idx);
            long doNotPinCurrent = (nums[idx][0] - nums[pin][0]) + solve(idx + 1, pin);
            long result = Math.min(pinCurrent, doNotPinCurrent);
            dp[idx][pin] =  result;
        }
        return dp[idx][pin];
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
