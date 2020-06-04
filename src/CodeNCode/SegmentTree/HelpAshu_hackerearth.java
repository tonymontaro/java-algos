package CodeNCode.SegmentTree;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.StringTokenizer;


public class HelpAshu_hackerearth {
    static PrintWriter out;
    static CF_Reader in;

    public static void main(String[] args) throws IOException {
        out = new PrintWriter(new OutputStreamWriter(System.out));
        in = new CF_Reader();
        int n = in.intNext();
        long[] arr = in.nextLongArray(n);
        SegTree segTree = new SegTree(arr);
        int q = in.intNext();
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < q; i++) {
            int query = in.intNext();
            if (query == 0) segTree.update(in.intNext()-1, in.longNext());
            else {
                int l = in.intNext()-1, r = in.intNext()-1;
                int res = segTree.rangeNumEvenQuery(l, r);
                result.append((query == 1) ? res : (r - l + 1) - res).append("\n");
            }
        }
        out.println(result);

        out.close();
    }


    static class SegTree {
        long[] arr;
        Integer[] segTree;

        public SegTree(long[] arr) {
            this.arr = arr;
            segTree = new Integer[arr.length * 4];
            Arrays.fill(segTree, null);
            construct(0, 0, arr.length - 1);
        }

        int construct(int idx, int start, int end) {
            if (start == end) segTree[idx] = (arr[start] % 2 == 0) ? 1 : 0;
            else {
                int mid = (start + end) / 2;
                int left = construct(2 * idx + 1, start, mid);
                int right = construct(2 * idx + 2, mid + 1, end);
                segTree[idx] = left + right;
            }
            return segTree[idx];
        }

        int rangeNumEvenQuery(int qStart, int qEnd) {
            // Queries are zero indexed
            return rangeNumEvenQuery(0, 0, arr.length - 1, qStart, qEnd);
        }

        int rangeNumEvenQuery(int idx, int start, int end, int qStart, int qEnd) {
            if (start >= qStart && end <= qEnd) return segTree[idx];
            else if (start > qEnd || end < qStart) return 0;
            int mid = (start + end) / 2;
            int left = rangeNumEvenQuery(2 * idx + 1, start, mid, qStart, qEnd);
            int right = rangeNumEvenQuery(2 * idx + 2, mid + 1, end, qStart, qEnd);
            return left + right;
        }

        void update(int queryIdx, long value) {
            int updateVal = 0;
            if ((value % 2 == 0) && (arr[queryIdx] % 2 == 1)) updateVal = 1;
            if ((value % 2 == 1) && (arr[queryIdx] % 2 == 0)) updateVal = -1;
            arr[queryIdx] = value;
            update(0, 0, arr.length-1, queryIdx, updateVal);
        }
        void update(int idx, int start, int end, int qIdx, int updateVal) {
            if (start > qIdx || end < qIdx) return;
            segTree[idx] += updateVal;
            if (start != end) {
                int mid = (start + end) / 2;
                update(2 * idx + 1, start, mid, qIdx, updateVal);
                update(2 * idx + 2, mid + 1, end, qIdx, updateVal);
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
