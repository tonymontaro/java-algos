package CodeForces.CF_1370;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.StringTokenizer;

public class OddEvenSubsequence_binary_search {
    static PrintWriter out;
    static CF_Reader in;

    public static void main(String[] args) throws IOException {
        out = new PrintWriter(new OutputStreamWriter(System.out));
        in = new CF_Reader();

        int n = in.intNext();
        int k = in.intNext();
        long[] arr = in.nextLongArray(n);
        out.println(solve(k, arr));

        out.close();
    }

    static long solve(int k, long[] arr) {
        long low = 1;
        long high = 1000000000;
        long res = high;
        while (low <= high) {
            long mid = (high + low) / 2;
            if (canFormOdd(mid, k, arr) || canFormEven(mid, k, arr)) {
                res = mid;
                high = mid - 1;
            } else {
                low = mid + 1;
            }
        }
        return res;
    }

    static boolean canFormOdd(long mid, int k, long[] arr) {
        int count = 0;
        int i = 0;
        while (i < arr.length) {
            if (count % 2 == 0 && arr[i] <= mid) {
                count++;
                i++;
                if (i < arr.length) count++;
                if (count >= k) return true;
            }
            i++;
        }
        return false;
    }

    static boolean canFormEven(long mid, int k, long[] arr) {
        int count = 1;
        int i = 1;
        while (i < arr.length) {
            if (count % 2 == 1 && arr[i] <= mid) {
                count++;
                i++;
                if (i < arr.length) count++;
                if (count >= k) return true;
            }
            i++;
        }
        return false;
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


