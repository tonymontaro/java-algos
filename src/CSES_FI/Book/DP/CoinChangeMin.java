package CSES_FI.Book.DP;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.StringTokenizer;

public class CoinChangeMin {
    static PrintWriter out;
    static CF_Reader in;

    public static void main(String[] args) throws IOException {
        out = new PrintWriter(new OutputStreamWriter(System.out));
        in = new CF_Reader();

        int n = 10;
        int[] coins = new int[]{1, 3, 4};
        out.println(solve(n, coins));

        out.println(solveAndConstruct(n, coins));

        out.close();
    }

    static int solve(int n, int[] coins) {
        int[] dp = new int[n + 1];
        Arrays.fill(dp, Integer.MAX_VALUE);
        for (int coin : coins) {
            for (int i = 1; i <= n; i++) {
                if (i == coin) dp[i] = 1;
                else if (i >= coin) dp[i] = Math.min(dp[i], 1 + dp[i - coin]);
            }
            out.println(Arrays.toString(dp));
        }
        return dp[n];
    }

    static StringBuilder solveAndConstruct(int n, int[] coins) {
        int[] dp = new int[n + 1];
        int[] selectedCoin = new int[n + 1];
        Arrays.fill(dp, Integer.MAX_VALUE);
        for (int coin : coins) {
            for (int i = 1; i <= n; i++) {
                if (i == coin) {
                    dp[i] = 1;
                    selectedCoin[i] = coin;
                } else if (i >= coin && (1 + dp[i - coin]) < dp[i]) {
                    dp[i] = 1 + dp[i - coin];
                    selectedCoin[i] = coin;
                }
            }
            out.println(Arrays.toString(dp));
        }
        StringBuilder res = new StringBuilder();
        out.println(Arrays.toString(selectedCoin));
        while (n > 0) {
            res.append(selectedCoin[n]).append(" ");
            n -= selectedCoin[n];
        }
        return res;
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


