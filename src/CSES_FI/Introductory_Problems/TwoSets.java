package CSES_FI.Introductory_Problems;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.HashSet;
import java.util.StringTokenizer;

public class TwoSets {
    static PrintWriter out;
    static CF_Reader in;

    public static void main(String[] args) throws IOException {
        out = new PrintWriter(new OutputStreamWriter(System.out));
        in = new CF_Reader();

        out.println(solve(in.intNext()));
        out.close();
    }

    static StringBuilder solve(long n) {
        long total = (n * (1 + n)) / 2;
        if (total % 2 == 1) return new StringBuilder("NO");
        StringBuilder res = new StringBuilder("YES\n");
        long half = total / 2;
        HashMap<Long, Long> seen = new HashMap<>();
        seen.put(0L, 0L);
        long start = 0;
        long end = 0;
        long cummulative = 0;
        for (long i = 1; i <= n; i++) {
            cummulative += i;
            seen.put(cummulative, i);
            long diff = cummulative - half;

            if (diff >= 0 && seen.containsKey(diff)) {
                start = seen.get(diff) + 1;
                end = i;
                break;
            }
        }
        StringBuilder consec = new StringBuilder();
        StringBuilder nonConsec = new StringBuilder();
        consec.append(end - start + 1).append("\n");
        nonConsec.append(n - (end - start + 1)).append("\n");
        for (long i = 1; i <= n; i++) {
            if (i >= start && i <= end) consec.append(i).append(" ");
            else nonConsec.append(i).append(" ");
        }
        consec.append("\n");
        res.append(consec).append(nonConsec);
        return res;
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


