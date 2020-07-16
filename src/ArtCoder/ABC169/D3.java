package ArtCoder.ABC169;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.StringTokenizer;

public class D3 {
    static PrintWriter out;
    static CF_Reader in;

    public static void main(String[] args) throws IOException {
        out = new PrintWriter(new OutputStreamWriter(System.out));
        in = new CF_Reader();

        long n = in.longNext();
//        ArrayList<Long> primes = ;
        out.println(generatePrimes((int) Math.sqrt(n), n));
//        out.println(solve(primes, n));
        out.close();
    }

    static int generatePrimes(int n, long on) {
        if (on <= 1) return 0;
        int res = 0;
        long un = on;
        ArrayList<Long> primes = new ArrayList<>();
        boolean[] nonPrimes = new boolean[n + 1];
        for (long i = 2; i <= n; i++) {
            if (nonPrimes[(int) i]) continue;
            primes.add(i);
            long j = 2;
            if (un % i == 0) {
                res++;
                un = un / i;
            }
            while (j * i <= n) {
                nonPrimes[(int) (j * i)] = true;
                if (un % (j * i) == 0) {
                    res++;
                    un = un / (j * i);
                }
                j++;

            }
        }
        return (res > 0) ? res : 1;
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


