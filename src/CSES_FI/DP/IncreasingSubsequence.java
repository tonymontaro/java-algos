package CSES_FI.DP;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.*;

public class IncreasingSubsequence {
    static PrintWriter out;
    static CF_Reader in;

    public static void main(String[] args) throws IOException {
        out = new PrintWriter(new OutputStreamWriter(System.out));
        in = new CF_Reader();

        int n = in.intNext();
        long[] arr = in.nextLongArray(n);
        out.println(longestIncreasing(n, arr));

        out.close();
    }

    static int longestIncreasing(int n, long[] arr) {
        ArrayList<Long> binaryArr = new ArrayList<>();
        Collections.fill(binaryArr, Long.MAX_VALUE);
        binaryArr.add(arr[0]);

        for (int i = 1; i < n; i++) {
            long num = arr[i];
            int lo = ArrUtil.lowerBound(binaryArr, num);

            if (lo >= binaryArr.size()) binaryArr.add(num);
            else if (num < binaryArr.get(lo)) binaryArr.set(lo, num);
        }
        return binaryArr.size();
    }

    static int longestIncreasingOld(int n, int[] arr) {
        int[] binaryArr = new int[n + 1];
        Arrays.fill(binaryArr, Integer.MAX_VALUE);
        binaryArr[1] = arr[0];
        int high = 1;
        int low = 1;
        for (int i = 1; i < n; i++) {
            int num = arr[i];
            int hi = high;
            int lo = low;
            while (lo <= hi) {
                int mid = (lo + hi) / 2;
                if (num > binaryArr[mid]) lo = mid + 1;
                else hi = mid - 1;
            }
            if (num < binaryArr[lo]) {
                binaryArr[lo] = num;
                high = Math.max(high, lo);
            }
        }

        return high;
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

    static class ArrUtil {
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
}


