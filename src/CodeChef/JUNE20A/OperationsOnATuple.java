package CodeChef.JUNE20A;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.StringTokenizer;

public class OperationsOnATuple {
    static PrintWriter out;
    static CF_Reader in;
    static long[] start;
    static long[] end;

    public static void main(String[] args) throws IOException {
        out = new PrintWriter(new OutputStreamWriter(System.out));
        in = new CF_Reader();

        int tests = in.intNext();
        StringBuilder res = new StringBuilder();
        for (int t = 0; t < tests; t++) {
            start = in.nextLongArray(3);
            end = in.nextLongArray(3);
            res.append(solve(start, end)).append("\n");
        }
        out.println(res);

        out.close();
    }

    static int solve(long[] start, long[] end) {
        Long[] diffs = new Long[3];
        Double[] divisors = new Double[3];
        int zeros = 0;
        for (int i = 0; i < 3; i++) {
            diffs[i] = end[i] - start[i];
            if (diffs[i] == 0) zeros++;
            divisors[i] = (start[i] == 0) ? null : (double)end[i] / (double)start[i];
        }
        if (zeros == 3) return 0;
        if (zeros == 2 || oneEdit(diffs, divisors)) return 1;
        if (zeros == 1 || twoEdit(start, end, diffs, divisors)) return 2;


        return 3;
    }

    static boolean oneEdit(Long[] diffs, Double[] divisors) {
        Long x = diffs[0], y = diffs[1], z = diffs[2];
        Double a = divisors[0], b = divisors[1], c = divisors[2];
        if ((x == 0 && y.equals(z)) || (y == 0 && x.equals(z)) || ((z == 0) && x.equals(y))) return true;
        if (x.equals(y) && y.equals(z)) return true;

        if ((x == 0 && (b != null && b.equals(c))) || (y == 0 && (a != null && a.equals(c))) || (z == 0 && (a != null && a.equals(b)))) return true;
        return (a != null && a.equals(b) && b.equals(c));
    }

    static boolean twoEdit(long[] start, long[] end, Long[] diffs, Double[] divisors) {
        Long x = diffs[0], y = diffs[1], z = diffs[2];
        Double a = divisors[0], b = divisors[1], c = divisors[2];
        if (x.equals(y) || y.equals(z) || x.equals(z)) return true;
        if ((a != null && a.equals(b)) || (b != null && b.equals(c)) || (a != null && a.equals(c))) return true;

        boolean result = false;
        for (int ai = 0; ai < 3; ai++) {
            for (int bi = 0; bi < 3; bi++) {
                if (ai != bi) {
                    for (int ci = 0; ci < 3; ci++) {
                        if (ci != ai && ci != bi) {
                            result = result || multiplyThenAdd(ai, bi, ci) || addThenMultiply(ai, bi, ci);
                        }
                    }
                }
            }
        }
        return result;
    }

    static boolean multiplyThenAdd(int ai, int bi, int ci) {
        long a = start[ai], a1 = end[ai], b = start[bi], b1 = end[bi], c = start[ci], c1 = end[ci];
        long numerator = a1 - b1;
        long denominator = a - b;
        if (denominator == 0 || numerator % denominator != 0) return false;
        long x = numerator / denominator;
        long y = a1 - (a * x);

        long calculatedC1 = (c * x) + y;
        return ((c * x) == c1) || (calculatedC1 == c1);
    }

    static boolean addThenMultiply(int ai, int bi, int ci) {
        long a = start[ai], a1 = end[ai], b = start[bi], b1 = end[bi], c = start[ci], c1 = end[ci];
        long numerator = (b1 * a) - (a1 * b);
        long denominator = a1 - b1;
        if (denominator == 0 || numerator % denominator != 0) return false;
        long x = numerator / denominator;
        if ((a + x) == 0 || a1 % (a + x) != 0) return false;
        long y = a1 / (a + x);

        long calculatedC1 = (c + x) * y;
        return ((c + x) == c1) || (calculatedC1 == c1);
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


