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

public class GeneratePermutations {
    static PrintWriter out;
    static CF_Reader in;

    public static void main(String[] args) throws IOException {
        out = new PrintWriter(new OutputStreamWriter(System.out));
        in = new CF_Reader();

        int[] arr = new int[]{1, 2, 3};
        boolean[] chosen = new boolean[arr.length];
        Stack<Integer> selectArr = new Stack<>();
        StringBuilder res = new StringBuilder();
        recursive(arr, chosen, selectArr, res);
        out.println(res);

        out.close();
    }

    static StringBuilder recursive(
            int[] arr,
            boolean[] chosen,
            Stack<Integer> permutation,
            StringBuilder res
    ) {
        if (permutation.size() >= arr.length) {
            res.append(permutation.toString()).append("\n");
            return res;
        }

        for (int i = 0; i < arr.length; i++) {
            if (!chosen[i]) {
                chosen[i] = true;
                permutation.push(arr[i]);
                recursive(arr, chosen, permutation, res);
                chosen[i] = false;
                permutation.pop();
            }
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


