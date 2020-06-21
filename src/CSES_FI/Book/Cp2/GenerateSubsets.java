package CSES_FI.Book.Cp2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Stack;
import java.util.StringTokenizer;

public class GenerateSubsets {
    static PrintWriter out;
    static CF_Reader in;

    public static void main(String[] args) throws IOException {
        out = new PrintWriter(new OutputStreamWriter(System.out));
        in = new CF_Reader();

        int[] arr = new int[]{1, 2, 3};
        Stack<Integer> current = new Stack<>();
        ArrayList<String> res = new ArrayList<>();
        recursive(0, arr, current, res);
        out.println(res);

        ArrayList<String> res2 = new ArrayList<>();
        usingBitmask(arr, res2);
        out.println(res2);

        out.close();
    }

    static ArrayList<String> recursive(int idx, int[] arr, Stack<Integer> current, ArrayList<String> res) {
        if (idx >= arr.length) {
            res.add(current.toString());
            return res;
        }

        // add idx
        current.add(arr[idx]);
        recursive(idx + 1, arr, current, res);
        current.pop();

        // skip idx
        recursive(idx + 1, arr, current, res);

        return res;
    }

    static ArrayList<String> usingBitmask(int[] arr, ArrayList<String> res) {
        int total = 1 << arr.length; // or 2^(arr.length)

        for (int row = 0; row < total; row++) {
            StringBuilder current = new StringBuilder("[");
            for (int j = 0; j < arr.length; j++) {
                int mask = 1 << j;
                if ((mask & row) != 0) {
                    current.append(arr[j]).append(',');
                }
            }
            current.append("]");
            res.add(current.toString().trim());
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


