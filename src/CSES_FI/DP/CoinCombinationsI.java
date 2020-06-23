package CSES_FI.DP;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.StringTokenizer;


public class CoinCombinationsI {
    static PrintWriter out;
    static CF_Reader in;

    public static void main(String[] args) throws IOException {
        out = new PrintWriter(new OutputStreamWriter(System.out));
        in = new CF_Reader();

        int n = in.intNext(), k = in.intNext();
        int[] coins = in.nextIntArray(n);

        out.println(numberOfWays(k, coins));

        out.close();
    }

    static int numberOfWays(int k, int[] coins) {
        int[] dp = new int[k + 1];
        int MOD = 1000000007;
        dp[0] = 1;
        for (int i = 1; i <= k; i++) {
            for (int coin : coins) {
                if (i >= coin) dp[i] = (dp[i] + dp[i - coin]) % MOD;
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

        int intNext() throws IOException {
            return Integer.parseInt(next());
        }

        public int[] nextIntArray(final int n) throws IOException {
            final int[] a = new int[n];
            for (int i = 0; i < n; i++)
                a[i] = intNext();
            return a;
        }
    }
}


