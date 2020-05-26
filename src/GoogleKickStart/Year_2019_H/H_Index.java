package GoogleKickStart.Year_2019_H;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class H_Index {
    static PrintWriter out;
    static CF_Reader in;

    public static void main(String[] args) throws IOException {
        out = new PrintWriter(new OutputStreamWriter(System.out));
        in = new CF_Reader();

        int tests = in.intNext();
        for (int t = 1; t < tests + 1; t++) {
            long n = in.longNext();
            out.printf("Case #%d: %s\n", t, solve(n));
        }

        out.close();
    }

    static StringBuilder solve(long n) throws IOException {
        // O(nLogn) time | O(n) space
        StringBuilder res = new StringBuilder();
        long H = 0;
        PriorityQueue<Long> greaterThanH = new PriorityQueue<>();
        for (int i = 0; i < n; i++) {
            long num = in.longNext();
            if (num > H) greaterThanH.add(num);
            if (greaterThanH.size() > H) {
                H += 1;
                while (greaterThanH.size() > 0 && greaterThanH.peek() <= H) {
                    greaterThanH.poll();
                }
            }
            res.append(H);
            if (i < n - 1) res.append(" ");
        }

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
