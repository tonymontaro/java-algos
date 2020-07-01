package CSES_FI.SortingAndSearching;

import java.io.*;
import java.util.*;

public class ConcertTickets_slow {
    static PrintWriter out;
    static CF_Reader in;
    static int[] prices;
    static HashMap<Integer, Integer> priceCount;
    static int n;
    static int m;

    public static void main(String[] args) throws IOException {
        out = new PrintWriter(new OutputStreamWriter(System.out));
        in = new CF_Reader();

        n = in.intNext();
        m = in.intNext();
        priceCount = new HashMap<>();
        for (int i = 0; i < n; i++) priceCount.merge(in.intNext(), 1, Integer::sum);
        prices = new int[priceCount.size()];
        int pIdx = 0;
        for (int p: priceCount.keySet()) {
            prices[pIdx] = p;
            pIdx++;
        }
        Arrays.sort(prices);
        StringBuilder res = new StringBuilder();
        for (int i = 0; i < m; i++) {
            int num = in.intNext();
            res.append(search(num, 0, priceCount.size() - 1)).append("\n");
        }
        out.println(res);

        out.close();
    }

    static int search(int price, int lo, int hi) {
        if (lo > hi) return -1;
        int valid = -1;
        while (lo <= hi) {
            int mid = (hi + lo) / 2;
            if (prices[mid] <= price) {
                valid = mid;
                lo = mid + 1;
            } else hi = mid - 1;
        }
        if (valid == -1) return -1;
        int key = prices[valid];
        if (priceCount.get(key) > 0) {
            priceCount.merge(key, -1, Integer::sum);
            return prices[valid];
        }

        return search(price, 0, valid - 1);
    }

    static void mergeSort(int[] a, int n) {
        if (n < 2) {
            return;
        }
        int mid = n / 2;
        int[] l = new int[mid];
        int[] r = new int[n - mid];

        for (int i = 0; i < mid; i++) {
            l[i] = a[i];
        }
        for (int i = mid; i < n; i++) {
            r[i - mid] = a[i];
        }
        mergeSort(l, mid);
        mergeSort(r, n - mid);

        merge(a, l, r, mid, n - mid);
    }
    static void merge(
            int[] a, int[] l, int[] r, int left, int right) {

        int i = 0, j = 0, k = 0;
        while (i < left && j < right) {
            if (l[i] <= r[j]) {
                a[k++] = l[i++];
            }
            else {
                a[k++] = r[j++];
            }
        }
        while (i < left) {
            a[k++] = l[i++];
        }
        while (j < right) {
            a[k++] = r[j++];
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
 