package CodeChef.LTIME85B;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

public class test {
    static PrintWriter out;
    static CF_Reader in;
    static int n;
    static int[] prices;
    static Item[][] cache;

    public static void main(String[] args) throws IOException {
        out = new PrintWriter(new OutputStreamWriter(System.out));
        in = new CF_Reader();

//        int n = in.intNext(), maxPrice = in.intNext();
//        int[] prices = in.nextIntArray(n);
//        int[] pages = in.nextIntArray(n);
//
//        out.println(maxPages(n, maxPrice, prices, pages));
        int k = 2;
        prices = new int[]{ 10, 6, 4 };
        n = prices.length;
        cache = new Item[n + 1][k + 1];
        Item res = solve(0, k);
        out.println(res.w);
        out.println(res.sum);

        out.close();
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

    static int maxPages(int n, int maxPrice, int[] prices, int[] pages) {
        int[] selectedPages = new int[maxPrice + 1];
        for (int book = 0; book < n; book++) {
            int[] currentPages = new int[maxPrice + 1];
            int bookPrice = prices[book];
            int bookPages = pages[book];
            for (int price = 1; price <= maxPrice; price++) {
                if (price < bookPrice) currentPages[price] = selectedPages[price];
                else currentPages[price] = Math.max(selectedPages[price], bookPages + selectedPages[price - bookPrice]);
            }
            selectedPages = currentPages;
        }
        return selectedPages[maxPrice];
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

    static class Tuple implements Comparable<MaximumAND.Tuple> {
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

        public int compareTo(MaximumAND.Tuple other) {
            if (this.a == other.a) return Integer.compare(this.b, other.b);
            return Integer.compare(this.a, other.a);
        }

        @Override
        public int hashCode() {
            return Arrays.deepHashCode(new Integer[]{a, b});
        }

        @Override
        public boolean equals(Object o) {
            if (!(o instanceof MaximumAND.Tuple)) return false;
            MaximumAND.Tuple pairo = (MaximumAND.Tuple) o;
            return (this.a == pairo.a && this.b == pairo.b);
        }

        @Override
        public String toString() {
            return String.format("%d,%d  ", this.a, this.b);
        }
    }
}
 