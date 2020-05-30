package CodeNCode.SegmentTree;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.StringTokenizer;

public class RangeMinimumQuery {
    static PrintWriter out;
    static CF_Reader in;

    public static void main(String[] args) throws IOException {
        out = new PrintWriter(new OutputStreamWriter(System.out));
        in = new CF_Reader();

        int n = in.intNext();
        int[] arr = in.nextIntArray(n);
        SegTree segTree = new SegTree(arr);
        StringBuilder result = new StringBuilder();
        int queries = in.intNext();
        for (int q = 0; q < queries; q++) {
            result.append(segTree.rangeMinQuery(in.intNext(), in.intNext())).append("\n");
        }
        out.println(result);

        out.close();
    }

    static class SegTree {
        int[] arr;
        Integer[] segTree;

        public SegTree(int[] arr) {
            this.arr = arr;
            segTree = new Integer[arr.length * 4];
            Arrays.fill(segTree, null);
            construct(0, 0, arr.length - 1);
        }

        int construct(int idx, int start, int end) {
            if (start == end) segTree[idx] = arr[start];
            else {
                int mid = (start + end) / 2;
                int left = construct(2 * idx + 1, start, mid);
                int right = construct(2 * idx + 2, mid + 1, end);
                segTree[idx] = Math.min(left, right);
            }
            return segTree[idx];
        }

        int rangeMinQuery(int qStart, int qEnd) {
            // Queries are zero indexed
            return rangeMinQuery(0, 0, arr.length - 1, qStart, qEnd);
        }

        int rangeMinQuery(int idx, int start, int end, int qStart, int qEnd) {
            if (start >= qStart && end <= qEnd) return segTree[idx];
            else if (start > qEnd || end < qStart) return Integer.MAX_VALUE;
            int mid = (start + end) / 2;
            int left = rangeMinQuery(2 * idx + 1, start, mid, qStart, qEnd);
            int right = rangeMinQuery(2 * idx + 2, mid + 1, end, qStart, qEnd);
            return Math.min(left, right);
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
