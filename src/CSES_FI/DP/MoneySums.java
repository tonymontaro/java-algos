package CSES_FI.DP;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.StringTokenizer;

public class MoneySums {
    static PrintWriter out;
    static CF_Reader in;
    static int total = 0;

    public static void main(String[] args) throws IOException {
        out = new PrintWriter(new OutputStreamWriter(System.out));
        in = new CF_Reader();

        int n = in.intNext();
        int[] coins = in.nextIntArray(n);

        out.println(validSums(coins));

        out.close();
    }

    static StringBuilder validSums(int[] coins) {
        boolean[] prev = new boolean[total + 1];
        prev[0] = true;
        for (int coin: coins) {
            boolean[] valid = new boolean[total + 1];
            for (int i = 0; i <= total; i++) {
                if (coin <= i) valid[i] = prev[i] || prev[i - coin];
                else valid[i] = prev[i];
            }
            prev = valid;
        }
        StringBuilder res = new StringBuilder();
        int count = 0;
        for (int i = 1; i <= total; i++) if (prev[i]) {
            count++;
            res.append(i).append(" ");
        }
        out.println(count);
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
            for (int i = 0; i < n; i++) {
                a[i] = intNext();
                total += a[i];
            }
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


