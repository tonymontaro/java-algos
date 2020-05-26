package GoogleKickStart.Year_2020_C;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.HashSet;
import java.util.StringTokenizer;

public class PerfectSubarray {
    static PrintWriter out;
    static CF_Reader in;

    public static void main(String[] args) throws IOException {
        out = new PrintWriter(new OutputStreamWriter(System.out));
        in = new CF_Reader();

        int tests = in.intNext();
        for (int t = 1; t <= tests; t++) {
            HashMap<Long, Long> seen = new HashMap<>();
            long total = 0;
            seen.put(total, 1L);
            long result = 0;
            int n = in.intNext();
            long[] nums = new long[n];
            for (int i = 0; i < n; i++) {
                long num = in.longNext();
                nums[i] = num;
                total += num;
                seen.merge(total, 1L, Long::sum);

                long j = 1;
                while (j * j <= total) {
                    long diff = total - (j * j);
                    if (seen.containsKey(diff)) result += seen.get(diff);
                    j++;
                }
            }
            result += zeros(nums);
            out.printf("Case #%d: %d\n", t, result);
        }
        out.close();
    }

    static long zeros(long[] nums) {
        int i = 0;
        long res = 0;
        while (i < nums.length) {
            if (nums[i] == 0) {
                long k = 1;
                while(i < nums.length && nums[i] == 0) {
                    res += k;
                    k++;
                    i++;
                }
            }
            i++;
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
