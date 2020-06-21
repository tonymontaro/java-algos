package CSES_FI.Introductory_Problems;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.*;

public class CreatingStringsI {
    static PrintWriter out;
    static CF_Reader in;

    public static void main(String[] args) throws IOException {
        out = new PrintWriter(new OutputStreamWriter(System.out));
        in = new CF_Reader();

        char[] arr = in.next().toCharArray();
        Arrays.sort(arr);

        Solver solver = new Solver(arr);
        out.println(solver.count);
        out.println(solver.res);

        out.close();
    }

    static class Solver {
        char[] arr;
        Stack<Character> stack;
        StringBuilder res;
        long count = 0;
        boolean[] seen;
        HashSet<String> duplicates = new HashSet<>();

        public Solver(char[] arr) {
            this.arr = arr;
            stack = new Stack<>();
            res = new StringBuilder();
            seen = new boolean[arr.length];

            solve(0);
        }

        void solve(int idx) {
            if (idx >= arr.length) {
                StringBuilder temp = new StringBuilder();
                for (char ch: stack) temp.append(ch);
                temp.append("\n");
                if (!duplicates.contains(temp.toString())) {
                    count++;
                    duplicates.add(temp.toString());
                    res.append(temp);
                }
            } else {
                for (int i = 0; i < arr.length; i++) {
                    if (!seen[i]) {
                        seen[i] = true;
                        stack.push(arr[i]);
                        solve(idx + 1);
                        seen[i] = false;
                        stack.pop();
                    }
                }
            }
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


