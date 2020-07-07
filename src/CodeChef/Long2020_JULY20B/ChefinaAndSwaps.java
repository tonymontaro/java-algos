package CodeChef.Long2020_JULY20B;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.*;

public class ChefinaAndSwaps {
    static PrintWriter out;
    static CF_Reader in;
    static HashMap<Long, Long> counts1;
    static HashMap<Long, Long> counts2;

    public static void main(String[] args) throws IOException {
        out = new PrintWriter(new OutputStreamWriter(System.out));
        in = new CF_Reader();

        int cases = in.intNext();
        StringBuilder result = new StringBuilder();

        for (int t = 0; t < cases; t++) {
            int n = in.intNext();
            counts1 = new HashMap<>();
            counts2 = new HashMap<>();
            for (int i = 0; i < n; i++) {
                counts1.merge(in.longNext(), 1L, Long::sum);
            }
            for (int i = 0; i < n; i++) {
                counts2.merge(in.longNext(), 1L, Long::sum);
            }
            result.append(solve()).append("\n");
        }

        out.println(result);

        out.close();
    }

    static long solve() {
        ArrayList<Long> keys1 = new ArrayList<>();
        ArrayList<Long> keys2 = new ArrayList<>();
        long minKey = 1000000001;
        for (long k : counts1.keySet()) {
            long a = counts1.get(k);
            long b = counts2.getOrDefault(k, 0l);
            if ((a + b) % 2 == 1) return - 1;
            if (a > b) keys1.add(k);
            minKey = Math.min(minKey, k);
        }
        for (long k : counts2.keySet()) {
            long a = counts2.get(k);
            long b = counts1.getOrDefault(k, 0l);
            if ((a + b) % 2 == 1) return - 1;
            if (a > b) keys2.add(k);
            minKey = Math.min(minKey, k);
        }

        Collections.sort(keys1);
        Collections.sort(keys2);
//        out.println(keys1);
//        out.println(keys2);
        boolean useMinKey = false;
        int l1 = 0, h1 = keys1.size() - 1;
        int l2 = 0, h2 = keys2.size() - 1;
        long total = 0;

        while (l1 <= h1) {
            if (minKey * 2 < Math.min(keys1.get(l1), keys2.get(l2))) {
                useMinKey = true;
                break;
            }
            if (keys1.get(l1) <= keys2.get(l2)) {
                long k1 = keys1.get(l1);
                long k2 = keys2.get(h2);
                long diff1 = counts1.get(k1) - counts2.getOrDefault(k1, 0l);
                long diff2 = counts2.get(k2) - counts1.getOrDefault(k2, 0l);
                long swaps = Math.min(diff1, diff2) / 2;
                total += k1 * swaps;
                if (diff1 == diff2) {
                    l1++; h2--;
                } else if (diff1 > diff2) {
                    h2--;
                    counts1.merge(k1, -swaps*2, Long::sum);
                } else {
                    l1++;
                    counts2.merge(k2, -swaps*2, Long::sum);
                }
            } else {
                long k1 = keys1.get(h1);
                long k2 = keys2.get(l2);
                long diff1 = counts1.get(k1) - counts2.getOrDefault(k1, 0l);
                long diff2 = counts2.get(k2) - counts1.getOrDefault(k2, 0l);
                long swaps = Math.min(diff1, diff2) / 2;
                total += k2 * swaps;
                if (diff1 == diff2) {
                    l2++; h1--;
                } else if (diff1 > diff2) {
                    l2++;
                    counts1.merge(k1, -swaps*2, Long::sum);
                } else {
                    h1--;
                    counts2.merge(k2, -swaps*2, Long::sum);
                }
            }
        }
        if (useMinKey) {
//            out.printf("minK: %d\n", minKey);
            for (int i = l1; i <= h1; i++) {
                long k = keys1.get(i);
                long diff = counts1.get(k) - counts2.getOrDefault(k, 0l);
                long swaps = diff / 2;
                total += 2 * minKey * swaps;
            }
        }

        return total;
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


