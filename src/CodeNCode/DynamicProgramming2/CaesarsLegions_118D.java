package CodeNCode.DynamicProgramming2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.HashMap;
import java.util.StringTokenizer;

public class CaesarsLegions_118D {
    static PrintWriter out;
    static CF_Reader in;
    static int k1;
    static int k2;
    static int MOD = 100000000;
    static HashMap<String, Integer> seen = new HashMap<>();

    public static void main(String[] args) throws IOException {
        out = new PrintWriter(new OutputStreamWriter(System.out));
        in = new CF_Reader();

        int n1 = in.intNext(), n2 = in.intNext();
        k1 = in.intNext();
        k2 = in.intNext();
        out.println(solve(n1, n2, 0, 0));

        out.close();
    }

    static int solve(int n1, int n2, int consec1, int consec2) {
        if ((n1 + n2) == 0) return 1;
        String rep = Arrays.toString((new int[]{n1, n2, consec1, consec2}));
        if (!seen.containsKey(rep)) {
            int x = 0;
            if (n1 > 0 && consec1 < k1) x = solve(n1-1, n2, consec1+1, 0);
            int y = 0;
            if (n2 > 0 && consec2 < k2) y = solve(n1, n2-1, 0, consec2+1);
            seen.put(rep, (x + y) % MOD);
        }
        return seen.get(rep);
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
