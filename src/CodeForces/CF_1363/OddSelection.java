package CodeForces.CF_1363;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.StringTokenizer;

public class OddSelection {
    static PrintWriter out;
    static CF_Reader in;

    public static void main(String[] args) throws IOException {
        out = new PrintWriter(new OutputStreamWriter(System.out));
        in = new CF_Reader();

        int tests = in.intNext();
        StringBuilder res = new StringBuilder();
        for (int t = 0; t < tests; t++) {
            int n = in.intNext(), x = in.intNext();
            res.append(solve(in.nextIntArray(n), x)).append("\n");
        }
        out.println(res);

        out.close();
    }

    static String solve(int[] nums, int x) {
        int odd = 0;
        int even = 0;
        for (int num: nums) {
            if (num % 2 == 0) even++;
            else odd++;
        }
        int oddN = (odd % 2 == 0) ? odd - 1 : odd;
        int N = (x % 2 == 0) ? x - 1 : x;
        int minN = Math.min(oddN, N);
        if (minN <= 0 || even < (x - minN)) return "No";
        return "Yes";
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


