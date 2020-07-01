package CSES_FI.SortingAndSearching;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.*;

public class RestaurantCustomers {
    static PrintWriter out;
    static CF_Reader in;

    public static void main(String[] args) throws IOException {
        out = new PrintWriter(new OutputStreamWriter(System.out));
        in = new CF_Reader();

        int n = in.intNext();
        out.println(solution2_withSortedMap(n));

        out.close();
    }

    static int solution2_withSortedMap(int n) throws IOException {
        HashMap<Integer, Integer> counts = new HashMap<>();
        for (int i = 0; i < n; i++) {
            int enter = in.intNext();
            int leave = in.intNext();
            counts.merge(enter, 1, Integer::sum);
            counts.merge(leave+1, -1, Integer::sum);
        }
        int[] keys = new int[counts.size()];
        int i = 0;
        for (int k : counts.keySet()) {
            keys[i] = k;
            i++;
        }
        Arrays.sort(keys);
        int res = 0;
        int current = 0;
        for (int k: keys) {
            current += counts.get(k);
            res = Math.max(res, current);
        }
        return res;
    }

    static int solution1(int n) throws IOException {
        int[] starts = new int[n];
        int[] ends = new int[n];
        for (int i = 0; i < n; i++) {
            starts[i] = in.intNext();
            ends[i] = in.intNext();
        }
        Arrays.sort(starts);
        Arrays.sort(ends);
//        out.println(Arrays.toString(starts));
//        out.println(Arrays.toString(ends));
        int maxCustomers = 0;
        int current = 0;
        int s = 0;
        int e = 0;
        while (e < n) {
            if (s < n && starts[s] <= ends[e]) {
                s++;
                current++;
                maxCustomers = Math.max(maxCustomers, current);
            } else {
                e++;
                current--;
            }
        }
        return maxCustomers;
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


