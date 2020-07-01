package CodeForces.CF1369;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.Stack;
import java.util.StringTokenizer;

public class AccurateLee {
    static PrintWriter out;
    static CF_Reader in;

    public static void main(String[] args) throws IOException {
        out = new PrintWriter(new OutputStreamWriter(System.out));
        in = new CF_Reader();

        int cases = in.intNext();
        StringBuilder result = new StringBuilder();
        for (int t = 0; t < cases; t++) {
            int n = in.intNext();
            char[] arr = in.next().toCharArray();
            result.append(solve(arr)).append("\n");
        }
        out.println(result);

        out.close();
    }

    static StringBuilder solve(char[] arr) {
        Stack<Character> stack = new Stack<>();
        int firstZeroIdx = Integer.MAX_VALUE;
        for (int i = arr.length - 1; i >= 0; i--) {
            if (arr[i] == '0') {
                firstZeroIdx = Math.min(firstZeroIdx, (arr.length - i - 1));
                stack.push('0');
            } else if (stack.size() < firstZeroIdx) stack.push('1');
            else {
                while (stack.size() > firstZeroIdx + 1) stack.pop();
            }
        }
        StringBuilder res = new StringBuilder();
        for (int i = stack.size() - 1; i >= 0; i--) res.append(stack.get(i));
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


