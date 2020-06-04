package GoogleKickStart.Year_2020_C;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Candies {
    static PrintWriter out;
    static CF_Reader in;

    public static void main(String[] args) throws IOException {
        out = new PrintWriter(new OutputStreamWriter(System.out));
        in = new CF_Reader();

        int tests = in.intNext();
        for (int t = 1; t <= tests; t++) {
            int n = in.intNext(), queries = in.intNext();
            long[] nums = in.nextLongArray(n);
            long[] multiples = new long[n];

            for (int i = 0; i < n; i++) {
                if (i % 2 == 1) nums[i] *= -1;
                multiples[i] = nums[i] * (1 + i);
            }

            SegTree numSeg = new SegTree(nums);
            SegTree multipleSeg = new SegTree(multiples);


            long total = 0;
            for (int i = 0; i < queries; i++) {
                char q = in.charNext();
                if (q == 'U') {
                    int idx = in.intNext()-1;
                    long val = in.longNext();
                    numSeg.update(idx, val);
                    multipleSeg.update(idx, val*(idx+1));
                } else {
                    int start = in.intNext()-1;
                    int end = in.intNext()-1;
                    long multRes = multipleSeg.rangeSumQuery(start, end);
                    long numRes = numSeg.rangeSumQuery(start, end);
                    long res = multRes - numRes * start;
                    if (start % 2 == 1) res *= -1;
                    total += res;
                }
            }
            out.printf("Case #%d: %d\n", t, total);
        }
        out.close();
    }


    static class SegTree {
        long[] arr;
        Long[] segTree;

        public SegTree(long[] arr) {
            this.arr = arr;
            segTree = new Long[arr.length * 4];
            Arrays.fill(segTree, null);
            construct(0, 0, arr.length - 1);
        }

        long construct(int idx, int start, int end) {
            if (start == end) segTree[idx] = arr[start];
            else {
                int mid = (start + end) / 2;
                long left = construct(2 * idx + 1, start, mid);
                long right = construct(2 * idx + 2, mid + 1, end);
                segTree[idx] = left + right;
            }
            return segTree[idx];
        }

        long rangeSumQuery(int qStart, int qEnd) {
            // Queries are zero indexed
            return rangeSumQuery(0, 0, arr.length - 1, qStart, qEnd);
        }

        long rangeSumQuery(int idx, int start, int end, int qStart, int qEnd) {
            if (start >= qStart && end <= qEnd) return segTree[idx];
            else if (start > qEnd || end < qStart) return 0;
            int mid = (start + end) / 2;
            long left = rangeSumQuery(2 * idx + 1, start, mid, qStart, qEnd);
            long right = rangeSumQuery(2 * idx + 2, mid + 1, end, qStart, qEnd);
            return left + right;
        }

        void update(int queryIdx, long value) {
            if (queryIdx % 2 == 1) value *= -1;
            long diff = value - arr[queryIdx];
            arr[queryIdx] = value;
            update(0, 0, arr.length-1, queryIdx, diff);
        }
        void update(int idx, int start, int end, int qIdx, long diff) {
            if (start > qIdx || end < qIdx) return;
            segTree[idx] += diff;
            if (start != end) {
                int mid = (start + end) / 2;
                update(2 * idx + 1, start, mid, qIdx, diff);
                update(2 * idx + 2, mid + 1, end, qIdx, diff);
            }
        }
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
