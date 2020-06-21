package CSES_FI.Book.Cp2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.StringTokenizer;

public class BitMask {
    static PrintWriter out;
    static CF_Reader in;

    public static void main(String[] args) throws IOException {
        out = new PrintWriter(new OutputStreamWriter(System.out));
        in = new CF_Reader();

        out.println(printBitRepr(4));
        out.println(printBitRepr(~4));
        out.println(setKthBitToOne(4, 1)); // 100 -> 110 (6)
        out.println(setKthBitToOne(4, 0)); // 100 -> 101 (5)
        out.println(setKthBitToOne(4, 2)); // 100 -> 100 (4)
        out.println("set bit to zero:");
        out.println(setKthBitToZero(6, 1)); // 110 -> 100 (4)
        out.println(setKthBitToZero(6, 0)); // 110 -> 100 (6)
        out.println("invert bit:");
        out.println(invertKthBit(6, 0)); // 110 -> 111 (7)
        out.println(invertKthBit(6, 1)); // 110 -> 100 (4)
        out.println("is power of two");
        out.println(isPowerOfTwo(6)); // false
        out.println(isPowerOfTwo(8)); // true
        out.close();
    }

    static StringBuilder printBitRepr(int n) {
        StringBuilder res = new StringBuilder();
        for (int i = 0; i < 32; i++) {
            int mask = (1 << i);
            res.append((mask & n) == 0 ? "0" : "1");
        }
        return res;
    }

    static int setKthBitToOne(int n, int k) {
        // zero indexed
        return (n | (1 << k));
    }
    static int setKthBitToZero(int n, int k) {
        return (n & ~(1 << k));
    }
    static int invertKthBit(int n, int k) {
        return (n ^ (1 << k));
    }
    static boolean isPowerOfTwo(int n) {
        return (n & (n - 1)) == 0;
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


