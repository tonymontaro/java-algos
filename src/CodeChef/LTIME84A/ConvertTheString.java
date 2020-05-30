package CodeChef.LTIME84A;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.StringTokenizer;

public class ConvertTheString {
    static PrintWriter out;
    static CF_Reader in;

    public static void main(String[] args) throws IOException {
        out = new PrintWriter(new OutputStreamWriter(System.out));
        in = new CF_Reader();

        int tests = in.intNext();

        for (int t = 0; t < tests; t++) {
            int n = in.intNext();
            char[] arrA = in.next().toCharArray();
            char[] arrB = in.next().toCharArray();
            out.print(solve(arrA, arrB));
        }

        out.close();
    }

    static StringBuilder solve(char[] arrA, char[] arrB) {
        HashMap<Character, ArrayList<Integer>> seen = new HashMap<>();
        for (int i = 0; i < arrA.length; i++) {
            char a = arrA[i];
            if (!seen.containsKey(a)) {
                seen.put(a, new ArrayList<>());
                seen.get(a).add(i);
            }
        }
        HashSet<Character> change = new HashSet<>();
        char[] alphas = "abcdefghijklmnopqrstuvwxyz".toCharArray();
        for (int i = 0; i < arrA.length; i++) {
            char b = arrB[i];
            char a = arrA[i];
            if (!seen.containsKey(b) || b > a) return new StringBuilder("-1\n");
            if (a != b) {
                seen.get(b).add(i);
                change.add(b);
            }
        }

        int k = 0;
        StringBuilder res = new StringBuilder();
        for (int i = alphas.length-1; i >= 0; i--) {
            char ch = alphas[i];
            if (change.contains(ch)) {
                ArrayList<Integer> rs = seen.get(ch);
                k++;
                res.append(rs.size()).append(" ");
                for (int n: seen.get(ch)) res.append(n).append(" ");
                res.append("\n");
            }
        }
        StringBuilder result = new StringBuilder();
        result.append(k).append("\n");
        result.append(res);
        return result;
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
