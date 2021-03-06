package CSES_FI.RangeQueries;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.*;

public class RangeSumQueries1 {
    static PrintWriter out;
    static CF_Reader in;
    static ArrayList<Integer>[] adj;

    public static void main(String[] args) throws IOException {
        out = new PrintWriter(new OutputStreamWriter(System.out));
        in = new CF_Reader();

        StringBuilder res = new StringBuilder();
        int n = in.intNext(), queries = in.intNext();
        long[] prefixSums = new long[n + 1];
        for (int i = 1; i < n + 1; i++) {
            prefixSums[i] = prefixSums[i-1] + in.longNext();
        }

        for (int i = 0; i < queries; i++) {
            int first = in.intNext();
            int last = in.intNext();
            res.append(prefixSums[last] - prefixSums[first - 1]).append("\n");
        }
        out.println(res);

        out.close();
    }



    static class LongSegTree {
        long[] arr;
        Long[] segTree;

        public LongSegTree(long[] arr) {
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

        void update(int queryIdx, int value) {
            arr[queryIdx] = value;
            update(0, 0, arr.length - 1, queryIdx);
        }

        void update(int idx, int start, int end, int qIdx) {
            if (start > qIdx || end < qIdx) return;
            segTree[idx] = Math.min(segTree[idx], arr[qIdx]);
            if (start != end) {
                int mid = (start + end) / 2;
                update(2 * idx + 1, start, mid, qIdx);
                update(2 * idx + 2, mid + 1, end, qIdx);
            }
        }
    }

    static class CF_Reader {
        BufferedReader br;
        StringTokenizer st;

        public CF_Reader() throws IOException {
            br = new BufferedReader(new InputStreamReader(System.in));
        }

        public ArrayList<Integer>[] adjacencyList(int n, int m) throws IOException {
            ArrayList<Integer>[] adj = new ArrayList[n + 1];
            for (int i = 1; i <= n; i++) adj[i] = new ArrayList<>();
            for (int i = 0; i < m; i++) {
                int a = intNext(), b = intNext();
                adj[a].add(b);
                adj[b].add(a);
            }
            return adj;
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

    static class util {
        public static int upperBound(long[] array, long obj) {
            int l = 0, r = array.length - 1;
            while (r - l >= 0) {
                int c = (l + r) / 2;
                if (obj < array[c]) {
                    r = c - 1;
                } else {
                    l = c + 1;
                }
            }
            return l;
        }

        public static int upperBound(ArrayList<Long> array, long obj) {
            int l = 0, r = array.size() - 1;
            while (r - l >= 0) {
                int c = (l + r) / 2;
                if (obj < array.get(c)) {
                    r = c - 1;
                } else {
                    l = c + 1;
                }
            }
            return l;
        }

        public static int lowerBound(long[] array, long obj) {
            int l = 0, r = array.length - 1;
            while (r - l >= 0) {
                int c = (l + r) / 2;
                if (obj <= array[c]) {
                    r = c - 1;
                } else {
                    l = c + 1;
                }
            }
            return l;
        }

        public static int lowerBound(ArrayList<Long> array, long obj) {
            int l = 0, r = array.size() - 1;
            while (r - l >= 0) {
                int c = (l + r) / 2;
                if (obj <= array.get(c)) {
                    r = c - 1;
                } else {
                    l = c + 1;
                }
            }
            return l;
        }

        public static void print(long[] arr) {
            System.out.println(Arrays.toString(arr));
        }

        public static void print(int[] arr) {
            System.out.println(Arrays.toString(arr));
        }

        public static void print(char[] arr) {
            System.out.println(Arrays.toString(arr));
        }
    }

    static class Tuple implements Comparable<Tuple> {
        int a;
        int b;

        public Tuple(int a, int b) {
            this.a = a;
            this.b = b;
        }

        public int getA() {
            return a;
        }

        public int getB() {
            return b;
        }

        public int compareTo(Tuple other) {
            if (this.a == other.a) return Integer.compare(this.b, other.b);
            return Integer.compare(this.a, other.a);
        }

        @Override
        public int hashCode() {
            return Arrays.deepHashCode(new Integer[]{a, b});
        }

        @Override
        public boolean equals(Object o) {
            if (!(o instanceof Tuple)) return false;
            Tuple pairo = (Tuple) o;
            return (this.a == pairo.a && this.b == pairo.b);
        }

        @Override
        public String toString() {
            return String.format("%d,%d  ", this.a, this.b);
        }
    }
}


