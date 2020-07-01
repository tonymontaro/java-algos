
import java.io.*;
import java.util.*;

public class SlidingCost {
    static PrintWriter out;
    static CF_Reader in;
    static SortedSet<Tuple> maxHeap;
    static SortedSet<Tuple> minHeap;
    static long minHeapTotal = 0;
    static long maxHeapTotal = 0;

    public static void main(String[] args) throws IOException {
        out = new PrintWriter(new OutputStreamWriter(System.out));
        in = new CF_Reader();

        int n = in.intNext(), windowSize = in.intNext();
        int[] arr = new int[n];

        StringBuilder res = new StringBuilder();
        maxHeap = new TreeSet<>();
        minHeap = new TreeSet<>();
        for (int i = 0; i < n; i++) {
            arr[i] = in.intNext();
            Tuple tuple = new Tuple(arr[i], i);
            add(tuple);

            if (i >= windowSize - 1) {
                res.append(getMinEqualityCost()).append(" ");
                int idx = i - (windowSize - 1);
                Tuple tp = new Tuple(arr[idx], idx);
                remove(tp);
            }
        }
        out.println(res);


        out.close();
    }

    static void add(Tuple num) {
        if (minHeap.size() > 0 && num.a > minHeap.first().a) {
            minHeap.add(num);
            minHeapTotal += num.a;
        } else {
            maxHeap.add(num);
            maxHeapTotal += num.a;
        }
        balance();
    }
    static void balance() {
        if (maxHeap.size() - minHeap.size() > 1) {
            Tuple tp = maxHeap.last();
            minHeap.add(tp);
            maxHeap.remove(tp);
            minHeapTotal += tp.a;
            maxHeapTotal -= tp.a;
        } else if (minHeap.size() - maxHeap.size() > 1) {
            Tuple tp = minHeap.first();
            maxHeap.add(tp);
            minHeap.remove(tp);
            minHeapTotal -= tp.a;
            maxHeapTotal += tp.a;
        }
    }
    static void remove(Tuple num) {
        int mi = minHeap.size();
        minHeap.remove(num);
        if (mi > minHeap.size()) minHeapTotal -= num.a;

        int ma = maxHeap.size();
        maxHeap.remove(num);
        if (ma > maxHeap.size()) maxHeapTotal -= num.a;
        balance();
    }

    static long getMinEqualityCost() {
        if (maxHeap.size() > 0 && (maxHeap.size() > minHeap.size() || maxHeap.size() == minHeap.size())) {
            long num = maxHeap.last().a;
            long cost = 0;
            if (maxHeap.size() > 1) cost += Math.abs((maxHeapTotal - num) - num * (maxHeap.size() - 1));
            if (minHeap.size() > 0) cost += Math.abs(minHeapTotal - num * minHeap.size());
            return cost;
        } else if (minHeap.size() > 0) {
            long num = minHeap.first().a;
            long cost = 0;
            if (minHeap.size() > 1) cost += Math.abs((minHeapTotal - num) - num * (minHeap.size() - 1));
            if (maxHeap.size() > 0) cost += Math.abs(maxHeapTotal - num * maxHeap.size());
            return cost;
        }
        return -1;
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
        public int hashCode() { return Arrays.deepHashCode(new Integer[]{a, b});}

        @Override
        public boolean equals(Object o) {
            if (!(o instanceof Tuple)) return false;
            Tuple pairo = (Tuple) o;
            return (this.a == pairo.a && this.b == pairo.b);
        }

        @Override
        public String toString () {
            return String.format("%d,%d  ", this.a, this.b);
        }
    }
}


