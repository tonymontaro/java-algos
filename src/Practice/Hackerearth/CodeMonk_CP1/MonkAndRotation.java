package Practice.Hackerearth.CodeMonk_CP1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.StringTokenizer;

public class MonkAndRotation {
    static PrintWriter out;
    static CF_Reader in;

    public static void main(String[] args) throws IOException {
        out = new PrintWriter(new OutputStreamWriter(System.out));
        in = new CF_Reader();

        int tests = in.intNext();
        StringBuilder res = new StringBuilder();
        for (int t = 0; t < tests; t++) {
            int n = in.intNext(), k = in.intNext();
            int[] arr = in.nextIntArray(n);
            res.append(solve(arr, k)).append("\n");
        }
        out.println(res);

        out.close();
    }

    static StringBuilder solve(int[] arr, int k) {
        int ln = arr.length;
        int[] newArr = new int[ln];
        for (int i = 0; i < ln; i++) newArr[(i + k) % ln] = arr[i];
        StringBuilder res = new StringBuilder();
        for (int i = 0; i < ln; i++) res.append(newArr[i]).append(" ");
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


