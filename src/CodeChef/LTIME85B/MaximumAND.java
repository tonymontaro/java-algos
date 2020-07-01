package CodeChef.LTIME85B;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.*;

public class MaximumAND {
    static PrintWriter out;
    static CF_Reader in;
    static int n = 30;
    static int[] prices;
    static Item[][] cache;
    static int[] twos;

    public static void main(String[] args) throws IOException {
        out = new PrintWriter(new OutputStreamWriter(System.out));
        in = new CF_Reader();

        int cases = in.intNext();

        twos = new int[30];
        for (int i = 0; i < 30; i++) twos[i] = 1 << i;

        for (int t = 0; t < cases; t++) {
            int nu = in.intNext(), k = in.intNext();
            int[] arr = in.nextIntArray(nu);
            prices = new int[30];
            for (int num: arr) setBitSum(num);

            cache = new Item[31][k + 1];
            StringBuilder ans;
            Item res = solve(0, k);
            ans = res.w.reverse();

            out.println(getAns(ans, k));
        }

        out.close();
    }


    static int getAns(StringBuilder res, int k) {
        int count = 0;
        char[] chars = res.toString().toCharArray();
        for (char c: chars) if (c == '1') count++;
        if (count == k) return Integer.parseInt(res.toString(), 2);
        int i = 29;
        while (count < k) {
            if (prices[i] == '0') {
                chars[i] = '1';
                count++;
            }
            i--;
        }
        res = new StringBuilder();
        for (char c: chars) res.append(c);
        return Integer.parseInt(res.toString(), 2);
    }


    static Item solve(int idx, int k) {
        if (cache[idx][k] == null) {
            Item result;
            if (idx >= n) result = new Item();
            else if (k <= 0) {
                Item res = solve(idx + 1, k);
                result = new Item(res.sum, res.w, '0');
            } else {
                // choose
                Item choose = solve(idx + 1, k - 1);
                choose = new Item(choose.sum + prices[idx], choose.w, '1');

                // do not choose
                Item skip = solve(idx + 1, k);
                skip = new Item(skip.sum, skip.w, '0');
                result = (choose.sum >= skip.sum) ? choose : skip;
            }
            cache[idx][k] = result;
        }
        return cache[idx][k];
    }

    static class Item {
        StringBuilder w;
        long sum;

        public Item() {
            w = new StringBuilder();
            sum = 0;
        }

        public Item(long sum1, StringBuilder w1, char ch) {
            w = new StringBuilder();
            w.append(ch).append(w1);
            sum = sum1;
        }
    }

    static void printBitRepr(int n) {
        StringBuilder res = new StringBuilder();
        for (int i = 0; i < 30; i++) {
            int mask = (1 << i);
            res.append((mask & n) == 0 ? "0" : "1");
        }
//        out.println(res);
        out.println(res.reverse());
    }
    static void setBitSum(int n) {
        StringBuilder res = new StringBuilder();
        for (int i = 0; i < 30; i++) {
            int mask = (1 << i);
            if ((mask & n) != 0) prices[i] += twos[i];
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


