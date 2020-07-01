package CSES_FI.SortingAndSearching;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.*;

public class MovieFestival2 {
    static PrintWriter out;
    static CF_Reader in;

    public static void main(String[] args) throws IOException {
        // O(N) time and space
        out = new PrintWriter(new OutputStreamWriter(System.out));
        in = new CF_Reader();

        int n = in.intNext(), k = in.intNext();
        long[][] movies = new long[n][2];
        for (int i = 0; i < n; i++) {
            movies[i] = new long[]{in.intNext(), in.intNext()};
        }
        Arrays.sort(movies, Comparator.comparingLong(o -> o[1]));
        for (long[] m: movies) util.print(m);
        ArrayList<Long> watching = new ArrayList<>();
        int wIdx = 0;
        int watched = 0;
        for (int i = 0; i < n; i++) {
            long start = movies[i][0];
            if (watching.size() > 0 && watching.get(0) <= start) {
                int lo = util.lowerBound(watching, start);
                if (lo > 0 && start != watching.get(lo)) lo--;
            }
            if (watching.size() < k) {
                watching.add(movies[i][1]);
                watched++;
            }
        }
        out.println(watched);

        out.close();
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

        public static void sort2dArray(int[][] arr) {
            Arrays.sort(arr, new Comparator<int[]>() {
                public int compare(int[] a, int[] b) {
                    if (a[0] == b[0]) return Integer.compare(a[1], b[1]);
                    return Integer.compare(a[0], b[0]);
                }
            });
        }
    }
}


