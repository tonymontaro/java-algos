package CodeForces.CF_1370;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.Stack;
import java.util.StringTokenizer;

public class GCDCompression {
    static PrintWriter out;
    static CF_Reader in;

    public static void main(String[] args) throws IOException {
        out = new PrintWriter(new OutputStreamWriter(System.out));
        in = new CF_Reader();

        int cases = in.intNext();

        StringBuilder res = new StringBuilder();
        for (int t = 0; t < cases; t++) {
            int n = in.intNext();
            Stack<Integer> even = new Stack<>();
            Stack<Integer> odd = new Stack<>();
            for (int i = 1; i <= n*2; i++) {
                int num = in.intNext();
                if (num % 2 == 0) even.push(i);
                else odd.push(i);
            }
            if (even.size() % 2 == 0) {
                if (even.size() > 1) {
                    even.pop();
                    even.pop();
                } else {
                    odd.pop();
                    odd.pop();
                }
            }
            while (even.size() > 1) {
                res.append(even.pop()).append(" ").append(even.pop()).append("\n");
            }
            while (odd.size() > 1) {
                res.append(odd.pop()).append(" ").append(odd.pop()).append("\n");
            }
        }
        out.println(res);

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
}


