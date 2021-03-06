package Tools;

import java.io.*;
import java.util.StringTokenizer;

public class BitMasking {
    static PrintWriter out;
    static CF_Reader in;

    public static void main(String[] args) throws IOException {
        out = new PrintWriter(new OutputStreamWriter(System.out));
        in = new CF_Reader();

        out.println(6 & 4);
        char[] arr = new char[]{'A', 'B', 'C'};

        int total = 1 << arr.length; // same as 2^ln possibilities, 2 << 3 -> 8: 1000

        for (int i = 0; i < total; i++) {
            // i from 0 to 2^ln => 0: 000, 1: 001, 2: 010, 3: 011, 4: 100, 5: 101, 6: 110, 7: 111
            for (int j = 0; j < arr.length; j++) {
                // shifts one by j and compares: j -> 001, 010, 100
                int mask = 1 << j;
                if ((mask & i) != 0) {
                    out.printf("%s ", arr[j]);
                }
            }
            out.println();
        }

        out.close();
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
