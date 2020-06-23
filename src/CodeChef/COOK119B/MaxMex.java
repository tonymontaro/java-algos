package CodeChef.COOK119B;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.StringTokenizer;

public class MaxMex {
    static PrintWriter out;
    static CF_Reader in;

    public static void main(String[] args) throws IOException {
        out = new PrintWriter(new OutputStreamWriter(System.out));
        in = new CF_Reader();

        int cases = in.intNext();
        StringBuilder result = new StringBuilder();

        for (int t = 0; t < cases; t++) {
            int n = in.intNext(), m = in.intNext();
            HashMap<Integer, Integer> counts = new HashMap<>();
            for (int i = 0; i < n; i++) {
                counts.merge(in.intNext(), 1, Integer::sum);
            }
            result.append(findMexValue(n, m, counts)).append("\n");
        }
        out.println(result);

        out.close();
    }

    static int findMexValue(int n, int m, HashMap<Integer, Integer> counts) {
        for (int i = 1; i < m; i++) {
            if (!counts.containsKey(i)) return -1;
        }
        int mCount = counts.getOrDefault(m, 0);
        return n - mCount;
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


