package CSES_FI.SortingAndSearching;

import java.io.*;
import java.util.*;

public class SlidingMedian {
    static PrintWriter out;
    static CF_Reader in;
    static SortedSet<Tuple> maxHeap;
    static SortedSet<Tuple> minHeap;

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
                res.append(getMedian()).append(" ");
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
        } else {
            maxHeap.add(num);
        }
        balance();
    }
    static void balance() {
        if (maxHeap.size() - minHeap.size() > 1) {
            minHeap.add(maxHeap.last());
            maxHeap.remove(maxHeap.last());
        } else if (minHeap.size() - maxHeap.size() > 1) {
            maxHeap.add(minHeap.first());
            minHeap.remove(minHeap.first());
        }
    }
    static void remove(Tuple num) {
        minHeap.remove(num);
        maxHeap.remove(num);
        balance();
    }

    static int getMedian() {
        if (maxHeap.size() > 0 && (maxHeap.size() > minHeap.size() || maxHeap.size() == minHeap.size()))
            return maxHeap.last().a;
        else if (minHeap.size() > 0) return minHeap.first().a;
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


