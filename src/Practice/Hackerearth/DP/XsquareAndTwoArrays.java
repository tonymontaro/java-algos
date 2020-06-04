package Practice.Hackerearth.DP;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.StringTokenizer;

public class XsquareAndTwoArrays {
    static PrintWriter out;
    static CF_Reader in;

    public static void main(String[] args) throws IOException {
        // O(N) time and space
        out = new PrintWriter(new OutputStreamWriter(System.out));
        in = new CF_Reader();

        int n = in.intNext(), queries = in.intNext();
        long[] aNums = in.nextLongArray(n);
        long[] bNums = in.nextLongArray(n);
        long[] aSums = new long[n + 2];
        long[] bSums = new long[n + 2];
        for (int i = 2; i < n+2; i++) {
            aSums[i] = aSums[i-2] + aNums[i-2];
            bSums[i] = bSums[i-2] + bNums[i-2];
        }
        StringBuilder result = new StringBuilder();
        for (int q = 0; q < queries; q++) {
            int startArrIdx = in.intNext(), start = in.intNext()+1, end = in.intNext()+1;
            long[] startArr = (startArrIdx == 1) ? aSums : bSums;
            long[] endArr = (startArrIdx != 1) ? aSums : bSums;
            boolean isOdd = (end - start + 1) % 2 == 1;
            int e1 = isOdd ? end : end - 1;
            int e2 = isOdd ? end - 1 : end;
            int s1 = start - 2;
            int s2 = start - 1;
            result.append((startArr[e1] - startArr[s1]) + (endArr[e2] - endArr[s2])).append("\n");
        }
        out.print(result);

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


