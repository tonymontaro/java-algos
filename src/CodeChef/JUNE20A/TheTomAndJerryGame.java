package CodeChef.JUNE20A;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.StringTokenizer;

public class TheTomAndJerryGame {
    static PrintWriter out;
    static CF_Reader in;

    public static void main(String[] args) throws IOException {
        // O(logn) time | O(1) space
        out = new PrintWriter(new OutputStreamWriter(System.out));
        in = new CF_Reader();

        int tests = in.intNext();
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < tests; i++) {
            long tomStrength = in.longNext();
            long oldTomStrength = tomStrength;
            long num = 2;
            while (tomStrength % 2 == 0) {
                tomStrength /= 2;
                num *= 2;
            }
            if (tomStrength == 1) result.append(0);
            else result.append(oldTomStrength / num);
            result.append("\n");
        }
        out.println(result);

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


