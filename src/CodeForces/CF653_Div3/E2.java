package CodeForces.CF653_Div3;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.*;

public class E2 {
    static PrintWriter out;
    static CF_Reader in;

    public static void main(String[] args) throws IOException {
        out = new PrintWriter(new OutputStreamWriter(System.out));
        in = new CF_Reader();

        int cases = 1;
        StringBuilder result = new StringBuilder();
        for (int t = 0; t < cases; t++) {
            int n = in.intNext(), m = in.intNext(), k = in.intNext();
            ArrayList<long[]> first = new ArrayList<>();
            ArrayList<long[]> second = new ArrayList<>();
            ArrayList<long[]> both = new ArrayList<>();
            ArrayList<long[]> zeros = new ArrayList<>();


            for (int i = 1; i < n+1; i++) {
                long cost = in.longNext(), a = in.longNext(), b = in.longNext();
                if (a == 1 && b == 1) both.add(new long[]{cost, i});
                else if (a == 1) first.add(new long[]{cost, i});
                else if (b == 1) second.add(new long[]{cost, i});
                else zeros.add(new long[]{cost, i});
            }
            result.append(solve(k, m, both, first, second, zeros)).append("\n");
        }
        out.println(result);

        out.close();
    }

    static StringBuilder solve(int k, int m, ArrayList<long[]> both, ArrayList<long[]> first, ArrayList<long[]> second, ArrayList<long[]> zeros) {
        if (both.size() + first.size() < k || both.size() + second.size() < k) return new StringBuilder("-1");
        if (m > both.size() + first.size() + second.size()) return new StringBuilder("-1");
        first.sort(Comparator.comparingLong(o -> o[0]));
        second.sort(Comparator.comparingLong(o -> o[0]));
        both.sort(Comparator.comparingLong(o -> o[0]));
        long read = 0;
        int count = 0;
        int chosen = 0;
        int bothIdx = 0;
        int sepIdx = 0;
        StringBuilder bookIndices = new StringBuilder();
        while (count < k) {
            if (bothIdx >= both.size()) {
                read += first.get(sepIdx)[0] + second.get(sepIdx)[0];
                bookIndices.append(first.get(sepIdx)[1]).append(" ").append(second.get(sepIdx)[1]).append(" ");
                sepIdx++;
                chosen += 2;
            } else if (sepIdx >= first.size() || sepIdx >= second.size()) {
                read += both.get(bothIdx)[0];
                bookIndices.append(both.get(bothIdx)[1]).append(" ");
                bothIdx++;
                chosen++;
            } else {
                long bothSum = both.get(bothIdx)[0];
                long sepSum = first.get(sepIdx)[0] + second.get(sepIdx)[0];
                if (bothSum <= sepSum) {
                    read += bothSum;
                    bookIndices.append(both.get(bothIdx)[1]).append(" ");
                    bothIdx++;
                    chosen++;
                } else {
                    read += sepSum;
                    bookIndices.append(first.get(sepIdx)[1]).append(" ").append(second.get(sepIdx)[1]).append(" ");
                    sepIdx++;
                    chosen += 2;
                }
            }
            count++;
        }
        out.printf("%d -> %d\n", chosen, read);
        int aIdx = sepIdx;
        int bIdx = sepIdx;
        int zIdx = 0;
        while (chosen < m) {
            long choice = Long.MAX_VALUE;
            int selected = 0;
            if (aIdx < first.size()) choice = first.get(aIdx)[0];
            if (bIdx < second.size() && second.get(bIdx)[0] < choice) {
                choice = second.get(bIdx)[0];
                selected = 1;
            }
            if (bothIdx < both.size() && both.get(bothIdx)[0] < choice) {
                choice = both.get(bothIdx)[0];
                selected = 2;
            }
            if (zIdx < zeros.size() && zeros.get(zIdx)[0] < choice) {
                choice = zeros.get(zIdx)[0];
                selected = 3;
            }

            if (selected == 0) {
                bookIndices.append(first.get(aIdx)[1]).append(" ");
                aIdx++;
            }
            else if (selected == 1) {
                bookIndices.append(second.get(bIdx)[1]).append(" ");
                bIdx++;
            } else if (selected == 2) {
                bookIndices.append(both.get(bothIdx)[1]).append(" ");
                bothIdx++;
            } else {
                bookIndices.append(zeros.get(zIdx)[1]).append(" ");
                zIdx++;
            }
            read += choice;
            chosen++;
        }
        StringBuilder res = new StringBuilder();
        res.append(read).append("\n").append(bookIndices);
        return res;
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


