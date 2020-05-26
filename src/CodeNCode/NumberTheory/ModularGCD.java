package CodeNCode.NumberTheory;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.math.BigInteger;
import java.util.StringTokenizer;

public class ModularGCD {
    static PrintWriter out;
    static CF_Reader in;

    public static void main(String[] args) throws IOException {
        out = new PrintWriter(new OutputStreamWriter(System.out));
        in = new CF_Reader();
        BigInteger MOD = new BigInteger("1000000007");
        int tests = in.intNext();
        for (int t = 0; t < tests; t++) {
            BigInteger a = new BigInteger(in.next());
            BigInteger b = new BigInteger(in.next());
            BigInteger n = new BigInteger(in.next());

            BigInteger diff = a.compareTo(b) > 0 ? a.subtract(b) : b.subtract(a);
            if (diff.equals(BigInteger.ZERO)) {
                BigInteger aPow = binpow_withMod(a, n, MOD);
                BigInteger bPow = binpow_withMod(b, n, MOD);
                out.println((aPow.add(bPow)).mod(MOD));
            } else {
                BigInteger aPow = binpow_withMod(a, n, diff);
                BigInteger bPow = binpow_withMod(b, n, diff);
                BigInteger gcdNum = gcd(aPow.add(bPow), diff);
                out.println(gcdNum.mod(MOD));
            }
        }
        out.close();
    }

    static BigInteger binpow_withMod(BigInteger a, BigInteger b, BigInteger MOD) {
        BigInteger res = BigInteger.ONE;
        BigInteger two = new BigInteger("2");
        while (b.compareTo(BigInteger.ZERO) > 0) {
            if ((b.mod(two)).equals(BigInteger.ONE)) {
                res = (res.multiply(a)).mod(MOD);
            }
            a = (a.multiply(a)).mod(MOD);
            b = b.divide(two);
        }
        return res;
    }

    static BigInteger gcd (BigInteger a, BigInteger b) {
        return b.equals(BigInteger.ZERO) ? a : gcd (b, a.mod(b));
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