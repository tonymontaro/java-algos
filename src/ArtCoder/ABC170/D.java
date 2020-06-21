package ArtCoder.ABC170;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.HashSet;
import java.util.StringTokenizer;

public class D {
    static PrintWriter out;
    static CF_Reader in;
    static int MAX = 1000000;
    static HashSet<Integer> duplicates;

    public static void main(String[] args) throws IOException {
        out = new PrintWriter(new OutputStreamWriter(System.out));
        in = new CF_Reader();


        int n = in.intNext();
        HashMap<Integer, Boolean> unique = new HashMap<>();
        duplicates = new HashSet<>();
        for (int i = 0; i < n; i++) {
            int num = in.intNext();
            if (unique.containsKey(num)) duplicates.add(num);
            else unique.put(num, true);
        }
        out.println(solve(unique));

        out.close();
    }

    static int solve(HashMap<Integer, Boolean> unique){
        int res = 0;
        for (int n: unique.keySet()) {
            if (unique.get(n)) {
                int num = n * 2;
                while (num <= MAX) {
                    if (unique.containsKey(num)) unique.put(num, false);
                    num += n;
                }
            }
        }
        for (int num: unique.keySet()) if (unique.get(num) && !duplicates.contains(num)) {
            res++;
        }
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
}


