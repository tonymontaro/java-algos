package CodeNCode.NumberTheory;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.StringTokenizer;


// https://www.codechef.com/problems/GCDQ
public class GCDQueries {
    static PrintWriter out;
    static CF_Reader in;

    public static void main(String[] args) throws IOException {
        out = new PrintWriter(new OutputStreamWriter(System.out));
        in = new CF_Reader();

        int tests = in.intNext();
        StringBuilder result = new StringBuilder();
        for (int t = 0; t < tests; t++) {
            int n = in.intNext();
            int q = in.intNext();
            int[] nums = in.nextIntArray(n);
            int[] prefix = new int[n + 1];
            int[] suffix = new int[n + 1];
            for (int i = 1; i < n + 1; i++) {
                prefix[i] = gcd(prefix[i - 1], nums[i-1]);
                suffix[n - i] = gcd(suffix[n - i + 1], nums[n - i]);
            }

            for (int i = 0; i < q; i++) {
                int left = in.intNext(), right = in.intNext();
                result.append(solve(left, right, prefix, suffix)).append("\n");
            }
        }
        out.println(result);

        out.close();
    }

    static int solve(int left, int right, int[] prefix, int[] suffix) {
        int prefixGcd = prefix[left - 1];
        int suffixGcd = suffix[right];
        return gcd(prefixGcd, suffixGcd);
    }

    static int gcd(int a, int b) {
        if (b == 0) return a;
        return gcd(b, a % b);
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
