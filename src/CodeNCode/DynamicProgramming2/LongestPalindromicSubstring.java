package CodeNCode.DynamicProgramming2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.StringTokenizer;


public class LongestPalindromicSubstring {
    static PrintWriter out;
    static CF_Reader in;
    static char ch;
    static char[] arr;
    static Integer[][][] dp;

    public static void main(String[] args) throws IOException {
        out = new PrintWriter(new OutputStreamWriter(System.out));
        in = new CF_Reader();

        int tests = in.intNext();
        StringBuilder res = new StringBuilder();
        for (int t = 0; t < tests; t++) {
            ch = in.charNext();
            arr = in.next().toCharArray();
            int ln = arr.length;
            dp = new Integer[ln][ln][2];
            for (Integer[][] row: dp)  for (Integer[] b: row) Arrays.fill(b, null);
            res.append(solve(0, arr.length - 1, 0)).append("\n");
        }
        out.print(res);

        out.close();
    }

    public String longestPalindrome(String s) {
        return "";
    }

//    static int solve()

    static int solve(int s, int e, int seen) {
        if (s >= e) {
            if (s == e && (seen == 1 || arr[s] == ch)) return 1;
            return 0;
        }
        int oldSeen = seen;
        if (dp[s][e][seen] == null) {
            // don't add
            int skipped = solve(s + 1, e, seen);

            // add it
            if (arr[s] == ch) seen = 1;
            int end = getEnd(s, e, arr[s]);
            int added;
            if (s == end) added = (seen == 1) ? 1 : 0;
            else {
                int res = solve(s + 1, end - 1, seen);
                added = (seen == 1 || res > 0) ? res + 2 : 0;
            }
            dp[s][e][oldSeen] =  Math.max(skipped, added);
        }
        return dp[s][e][oldSeen];
    }

    static int getEnd(int s, int e, char chr) {
        while (e > s) {
            if (arr[e] == chr) return e;
            e--;
        }
        return e;
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


