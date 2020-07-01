package CodeForces.CF1369;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.*;

public class  RationalLee {
    static PrintWriter out;
    static CF_Reader in;

    public static void main(String[] args) throws IOException {
        out = new PrintWriter(new OutputStreamWriter(System.out));
        in = new CF_Reader();

        int cases = in.intNext();
        StringBuilder result = new StringBuilder();

        for (int t = 0; t < cases; t++) {
            int n = in.intNext(), f = in.intNext();
            Integer[] nums = in.nextIntArray(n);
            Integer[] friends = in.nextIntArray(f);
            Arrays.sort(nums);
            Arrays.sort(friends);

            int[] start = new int[f];
            int[] end = new int[f];

            int high = n - 1;
            for (int fIdx = 0; fIdx < f; fIdx++) {
                start[fIdx] = nums[high];
                end[fIdx] = nums[high];
                high--;
                friends[fIdx]--;
            }
            int fIdx = f - 1;
            int idx = 0;
            while (idx <= high) {
                end[fIdx] = nums[idx];
                idx += friends[fIdx];
                fIdx--;
            }
            long total = 0;
            for (int i = 0; i < f; i++) {
                total += (start[i] + end[i]);
            }
            result.append(total).append("\n");
        }
        out.println(result);


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

        public Integer[] nextIntArray(final int n) throws IOException {
            final Integer[] a = new Integer[n];
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


