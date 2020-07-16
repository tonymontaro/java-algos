package GoogleKickStart.Year_2019.Year_2019_G;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.HashSet;
import java.util.StringTokenizer;

public class BookReading {
    static PrintWriter out;
    static CF_Reader in;

    public static void main(String[] args) throws IOException {
        out = new PrintWriter(new OutputStreamWriter(System.out));
        in = new CF_Reader();

        int tests = in.intNext();
        for (int t = 1; t < tests + 1; t++) {
            out.printf("Case #%d: %d\n", t, solve(in.intNext()));
        }
        out.close();
    }

    static long solve(int n) throws IOException {
        // O(nlogn) time | O(n) space
        HashMap<Long, Long> seen = new HashMap<>();
        long total = 0;
        int numTurn = in.intNext();
        int numReaders = in.intNext();
        HashSet<Integer> turn = new HashSet<>();
        for (int i = 0; i < numTurn; i++) turn.add(in.intNext());
        for (int i = 0; i < numReaders; i++) {
            long reader = in.longNext();
            if (!seen.containsKey(reader)) {
                long read = n / reader;
                for (int j = 0; j <= n; j += reader) if (turn.contains(j)) read--;
                seen.put(reader, read);
            }
            total += seen.get(reader);
        }
        return total;
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
