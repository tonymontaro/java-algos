package CSES_FI.Introductory_Problems;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

public class NumberSpiral {
    static PrintWriter out;
    static CF_Reader in;

    public static void main(String[] args) throws IOException {
        out = new PrintWriter(new OutputStreamWriter(System.out));
        in = new CF_Reader();


        int cases = in.intNext();
        for (int t = 0; t < cases; t++) {
            long r = in.longNext();
            long c = in.longNext();

            if (r < c) {
                long[] colStart = calcStart(c, c % 2 == 1);
                out.println(colStart[0] + colStart[1] * (r - 1));
            } else {
                long[] rowStart = calcStart(r, r % 2 == 0);
                out.println(rowStart[0] + rowStart[1] * (c - 1));
            }
        }

        out.close();
    }

    static long[] calcStart(long r, boolean isDecreasing) {
        long[] res = new long[2];
        if (isDecreasing) {
            res[0] = calculateSn(r);
            res[1] = -1;
        } else {
            res[0] = calculateSn(r-1) + 1;
            res[1] = 1;
        }
        return res;
    }

    static long calculateSn(long n) {
        return (n * (2 + (n - 1) * 2)) / 2;
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


