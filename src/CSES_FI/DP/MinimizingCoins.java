package CSES_FI.DP;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.StringTokenizer;

public class MinimizingCoins {
    static PrintWriter out;
    static CF_Reader in;

    public static void main(String[] args) throws IOException {
        out = new PrintWriter(new OutputStreamWriter(System.out));
        in = new CF_Reader();

        int n = in.intNext(), k = in.intNext();
        int[] coins = in.nextIntArray(n);

        out.println(minCoins(k, coins));

        out.close();
    }

    static int minCoins(int k, int[] coins) {
        Arrays.sort(coins);
        int[] dp = new int[k + 1];
        Arrays.fill(dp, -1);
        dp[0] = 0;

        for (int i = 1; i <= k; i++) {
            for (int coin: coins) {
                if (coin > i) break;
                boolean canAddCoin = dp[i - coin] != -1;
                boolean shouldAddCoin = dp[i] == -1 || (1 + dp[i - coin]) < dp[i];
                if (canAddCoin && shouldAddCoin) dp[i] = 1 + dp[i - coin];
            }
        }

        return dp[k];
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


