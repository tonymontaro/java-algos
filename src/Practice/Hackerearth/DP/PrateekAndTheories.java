package Practice.Hackerearth.DP;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.StringTokenizer;

public class PrateekAndTheories {
    static PrintWriter out;
    static CF_Reader in;

    public static void main(String[] args) throws IOException {
        out = new PrintWriter(new OutputStreamWriter(System.out));
        in = new CF_Reader();

        int tests = in.intNext();
        StringBuilder res = new StringBuilder();
        for (int t = 0; t < tests; t++) {
            int queries = in.intNext();
            HashMap<Integer, Integer> arr = new HashMap<>();
            ArrayList<Integer> points = new ArrayList<>();
            int maxNum = 0;
            for (int i = 0; i < queries; i++) {
                int a = in.intNext(), b = in.intNext();
                arr.merge(a, 1, Integer::sum);
                arr.merge(b, -1, Integer::sum);
            }
            long prev = 0;
            long best = 0;
            int[] nums = new int[arr.size()];
            int idx = 0;
            for (int num: arr.keySet()) {
                nums[idx] = num;
                idx++;
            }
            Arrays.sort(nums);
            for (int num: nums) {
                long total = prev + arr.get(num);
                best = Math.max(best, total);
                prev = total;
            }
            res.append(best).append("\n");
        }
        out.println(res);

        out.close();
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


