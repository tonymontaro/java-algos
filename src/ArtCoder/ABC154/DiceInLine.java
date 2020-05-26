package ArtCoder.ABC154;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.StringTokenizer;

public class DiceInLine {
    static PrintWriter out;

    public static void main(String[] args) throws IOException {
        out = new PrintWriter(new OutputStreamWriter(System.out));
        CF_Reader in = new CF_Reader();

        int n = in.intNext(), k = in.intNext();
        long[] nums = new long[(int) n];
        for (int i = 0; i < n; i++) nums[i] = in.longNext();

        double[] maxAdjacent = getMaxAdjacentNums(nums, k);
        double res = 0;
        for (double nm: maxAdjacent) res += (1 + nm) / 2;
        out.println(res);

        out.close();
    }

    static double[] getMaxAdjacentNums(long[] nums, int k) {
        int endIdx = 0;
        long total = 0;
        long best = 0;

        for (int i = 0; i < nums.length; i++) {
            if (i < k) total += nums[i];
            else {
                total += nums[i];
                total -= nums[i - k];
            }
            if (i >= k - 1 && total > best) {
                best = total;
                endIdx = i;
            }
        }
        double[] res = new double[k];
        int start = endIdx - k + 1;
        for (int i = start; i <= endIdx; i++) res[i - start] = nums[i];
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
