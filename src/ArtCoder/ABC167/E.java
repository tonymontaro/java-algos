package ArtCoder.ABC167;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.StringTokenizer;

public class E {
    static PrintWriter out;
    static CF_Reader in;
    static final long MOD = 998244353;

    public static void main(String[] args) throws IOException {
        out = new PrintWriter(new OutputStreamWriter(System.out));
        in = new CF_Reader();

        long n = in.longNext(), m = in.longNext(), k = in.longNext();

        long ans = 0;
        for (int i = 0; i < k + 1; i++) {
            ans += (((nCr_withMod(n-1, i) * m) % MOD) * binpow_withMod(m - 1, n - k - 1)) % MOD;
        }
        out.println(ans);

        out.close();
    }



    static long binpow_withMod(long a, long b) {
        long res = 1;
        while (b > 0) {
            if (b % 2 == 1) res = (res * a) % MOD;
            a = (a * a) % MOD;
            b /= 2;
        }
        return res;
    }

    static long nPr_withMod(long n, long r) {
        long res = 1;
        for (long i = (n - r + 1); i <= n; i++) {
            res = (res * i) % MOD;
        }
        return res;
    }

    static long nCr_withMod(long n, long r) {
        long r_factorial = nPr_withMod(r, r);
        long first = nPr_withMod(n, r);
        long second = binpow_withMod(r_factorial, MOD-2);
        return  (first * second) % MOD;
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
