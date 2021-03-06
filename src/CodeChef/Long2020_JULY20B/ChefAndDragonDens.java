/*
    Author: Anthony Ngene
    Created: 08/07/2020 - 18:42
*/
package CodeChef.Long2020_JULY20B;

import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.*;

public class ChefAndDragonDens {
    private static final FastReader in = new FastReader();
    private static final FastWriter out = new FastWriter();
    static ArrayList<Integer>[] adj;

    public static void main(String[] args) throws IOException {

        int n = in.intNext(), queries = in.intNext();
        long[] heights = in.nextLongArray(n);
        long[] spices = in.nextLongArray(n);
        LongSegTree segTree = new LongSegTree(heights, spices);
        for (int i = 0; i < queries; i++) {
            if (in.intNext() == 2) {
                int start = in.intNext()-1, end = in.intNext()-1;
                if (start > end) out.println(segTree.rangeSpiceQuery(end, start, 0));
                else out.println(segTree.rangeSpiceQuery(start, end, 1));
            } else {
                segTree.update(in.intNext()-1, in.longNext());
            }
        }

        out.close();
    }


    static class Node {
        int lo;
        int hi;
        long spices;
        public Node(int lo, int hi, long spices) {
            this.lo = lo;
            this.hi = hi;
            this.spices = spices;
        }

        @Override
        public String toString() {
            return "{" + lo + ", " + hi + ", " + spices + '}';
        }
    }

    static class LongSegTree {
        long[] heights;
        long[] spices;
        Long[][][] segTree;

        public LongSegTree(long[] hts, long[] spices) {
            heights = new long[hts.length + 1];
            System.arraycopy(hts, 0, heights, 0, hts.length);
            heights[hts.length] = Integer.MIN_VALUE;
            this.spices = spices;
            segTree = new Long[hts.length * 4][2][3];
            construct(0, 0, hts.length - 1, 0);
//            out.println(Arrays.deepToString(this.segTree));
            construct(0, 0, hts.length - 1, 1);
//            out.println(Arrays.deepToString(this.segTree));

//            out.println(rangeSpiceQuery(2-1, 5-1, 0));
//            out.println(rangeSpiceQuery(2-1, 4-1, 0));
        }

        Long[] construct(int idx, int start, int end, int dir) {
            if (start == end) {
                segTree[idx][dir] = new Long[]{(long)start, (long)start, spices[start]};
            } else {
                int mid = (start + end) / 2;
                if (dir == 0) {
                    Long[] left = construct(2 * idx + 1, start, mid, dir);
                    construct(2 * idx + 2, mid + 1, end, dir);
                    Long[] right = rangeSpiceQuery(2 * idx + 2, mid + 1, end, mid + 1, end, dir, (int)(long)left[1]);
                    if (right == null) segTree[idx][dir] = new Long[]{left[0], left[1], left[2]};
                    else segTree[idx][dir] = new Long[]{left[0], right[1], left[2] + right[2]};
                } else {
                    Long[] right = construct(2 * idx + 2, mid + 1, end, dir);
                    construct(2 * idx + 1, start, mid, dir);
                    Long[] left = rangeSpiceQuery(2 * idx + 1, start, mid, start, mid, dir, (int)(long)right[1]);
                    if (left == null) segTree[idx][dir] = new Long[]{right[0], right[1], right[2]};
                    else segTree[idx][dir] = new Long[]{right[0], left[1], left[2] + right[2]};
                }
            }
            return segTree[idx][dir];
        }

        Long[] rangeSpiceQuery(int idx, int start, int end, int qStart, int qEnd, int dir, int min) {
            if (start >= qStart && end <= qEnd && heights[(int)(long)segTree[idx][dir][0]] > heights[min]) return segTree[idx][dir];
            else if (start > qEnd || end < qStart || start == end) return null;
            int mid = (start + end) / 2;
            if (dir == 0) {
                Long[] left = rangeSpiceQuery(2 * idx + 1, start, mid, qStart, qEnd, dir, min);
                if (left == null) return rangeSpiceQuery(2 * idx + 2, mid + 1, end, qStart, qEnd, dir, min);
                Long[] right = rangeSpiceQuery(2 * idx + 2, mid + 1, end, qStart, qEnd, dir, (int)(long)left[1]);
                if (right == null) return left;
                return new Long[]{left[0], right[1], left[2] + right[2]};
            } else {
                Long[] right = rangeSpiceQuery(2 * idx + 2, mid + 1, end, qStart, qEnd, dir, min);
                if (right == null) return rangeSpiceQuery(2 * idx + 1, start, mid, qStart, qEnd, dir, min);
                Long[] left = rangeSpiceQuery(2 * idx + 1, start, mid, qStart, qEnd, dir, (int)(long)right[1]);
                if (left == null) return right;
                return new Long[]{right[0], left[1], left[2] + right[2]};
            }
        }

        String rangeSpiceQuery(int qStart, int qEnd, int dir) {
            // Queries are zero indexed
            Long[] res = rangeSpiceQuery(0, 0, heights.length - 2, qStart, qEnd, dir, heights.length - 1);
            if ((dir == 0 && res[1] != qEnd) || (dir == 1 && res[1] != qStart)) return "-1";
            return String.valueOf(res[2]);
        }



        void update(int queryIdx, long value) {
            long newVal = value - spices[queryIdx];
            spices[queryIdx] = value;
//            out.println(Arrays.deepToString(segTree));
            update(0, 0, spices.length - 1, queryIdx, newVal, 0);
            update(0, 0, spices.length - 1, queryIdx, newVal, 1);
        }
        Long[] update(int idx, int start, int end, int qIdx, long newVal, int dir) {
            if (start > qIdx || end < qIdx) return segTree[idx][dir];

            if (start == end) {
                segTree[idx][dir] = new Long[]{(long)start, (long)start, spices[start]};
            } else {
                int mid = (start + end) / 2;
                if (dir == 0) {
                    Long[] left = update(2 * idx + 1, start, mid, qIdx, newVal, dir);
                    update(2 * idx + 2, mid + 1, end, qIdx, newVal, dir);
                    Long[] right = rangeSpiceQuery(2 * idx + 2, mid + 1, end, mid + 1, end, dir, (int)(long)left[1]);
                    if (right == null) segTree[idx][dir] = new Long[]{left[0], left[1], left[2]};
                    else segTree[idx][dir] = new Long[]{left[0], right[1], left[2] + right[2]};
                } else {
                    Long[] right = update(2 * idx + 2, mid + 1, end, qIdx, newVal, dir);
                    update(2 * idx + 1, start, mid, qIdx, newVal, dir);
                    Long[] left = rangeSpiceQuery(2 * idx + 1, start, mid, start, mid, dir, (int)(long)right[1]);
                    if (left == null) segTree[idx][dir] = new Long[]{right[0], right[1], right[2]};
                    else segTree[idx][dir] = new Long[]{right[0], left[1], left[2] + right[2]};
                }
            }
            return segTree[idx][dir];

//            if (qIdx >= start && qIdx <= end) {
//                if (qIdx >= segTree[idx][0][0] && qIdx <= segTree[idx][0][1]) {
//                    segTree[idx][0][2] += newVal;
//                }
//                if (qIdx >= segTree[idx][1][0] && qIdx <= segTree[idx][1][1]) {
//                    segTree[idx][1][2] += newVal;
//                }
//                if (start == end) {
//                    return;
//                }
//                int mid = (start + end) / 2;
//                update(2 * idx + 1, start, mid, qIdx, newVal);
//                update(2 * idx + 2, mid + 1, end, qIdx, newVal);
//            }
        }

    }



    static class FastWriter {
        private static final int IO_BUFFERS = 128 * 1024;
        private final StringBuilder out;

        public FastWriter() {
            out = new StringBuilder(IO_BUFFERS);
        }

        public FastWriter print(Object object) {
            out.append(object);
            return this;
        }

        public FastWriter print(String format, Object... args) {
            out.append(String.format(format, args));
            return this;
        }

        public FastWriter println(Object object) {
            out.append(object).append("\n");
            return this;
        }

        public void close() throws IOException {
            System.out.print(out);
        }
    }

    static class FastReader {
        private static final int IO_BUFFERS = 128 * 1024;
        private DataInputStream din;
        private byte[] buffer;
        private int bufferPointer, bytesRead;

        public FastReader() {
            din = new DataInputStream(System.in);
            buffer = new byte[IO_BUFFERS];
            bufferPointer = bytesRead = 0;
        }

        public FastReader(String file_name) throws IOException {
            din = new DataInputStream(new FileInputStream(file_name));
            buffer = new byte[IO_BUFFERS];
            bufferPointer = bytesRead = 0;
        }

        public String readLine() throws IOException {
            byte[] buf = new byte[64]; // line length
            int cnt = 0, c;
            while ((c = read()) != -1) {
                if (c == '\n') break;
                buf[cnt++] = (byte) c;
            }
            return new String(buf, 0, cnt);
        }

        public int intNext() throws IOException {
            int ret = 0;
            byte c = read();
            while (c <= ' ') c = read();
            boolean neg = (c == '-');
            if (neg) c = read();
            do {
                ret = ret * 10 + c - '0';
            } while ((c = read()) >= '0' && c <= '9');

            if (neg) return -ret;
            return ret;
        }

        public long longNext() throws IOException {
            long ret = 0;
            byte c = read();
            while (c <= ' ') c = read();
            boolean neg = (c == '-');
            if (neg) c = read();
            do {
                ret = ret * 10 + c - '0';
            } while ((c = read()) >= '0' && c <= '9');
            if (neg) return -ret;
            return ret;
        }

        public double doubleNext() throws IOException {
            double ret = 0, div = 1;
            byte c = read();
            while (c <= ' ') c = read();
            boolean neg = (c == '-');
            if (neg) c = read();

            do {
                ret = ret * 10 + c - '0';
            } while ((c = read()) >= '0' && c <= '9');

            if (c == '.') {
                while ((c = read()) >= '0' && c <= '9') {
                    ret += (c - '0') / (div *= 10);
                }
            }

            if (neg) return -ret;
            return ret;
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

        private void fillBuffer() throws IOException {
            bytesRead = din.read(buffer, bufferPointer = 0, IO_BUFFERS);
            if (bytesRead == -1) buffer[0] = -1;
        }

        private byte read() throws IOException {
            if (bufferPointer == bytesRead) fillBuffer();
            return buffer[bufferPointer++];
        }

        public void close() throws IOException {
            if (din == null) return;
            din.close();
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
