package ArtCoder.ABC155;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.StringTokenizer;
import java.util.stream.LongStream;

public class Pairs {
    static PrintWriter out;
    static int negLen = 0;
    static int posLen = 0;
    static long zeros = 0;
    static long zeroCount;

    public static void main(String[] args) throws IOException {
        out = new PrintWriter(new OutputStreamWriter(System.out));
        CF_Reader in = new CF_Reader();

        int n = in.intNext();
        long k = in.longNext();
        long[] a = in.nextLongArray(n);
        long[] pos = LongStream.of(a).filter(e -> e > 0).sorted().toArray();
        long[] negR = LongStream.of(a).filter(e -> e < 0).sorted().toArray();
        long[] neg = new long[negR.length];
        for (int i = 0; i < neg.length; i++) neg[i] = negR[neg.length - i - 1];
        negLen = neg.length;
        posLen = pos.length;
        zeros = n - negLen - posLen;
        zeroCount = (posLen + negLen) * zeros + ((zeros * (zeros - 1)) / 2);
        long left = -1000000000L * 1000000000L;
        long right = 1000000000L * 1000000000L;

        long mid = -1;
        while (right - left > 1) {
            mid = (left + right) / 2;
            long numOfMidOrLess = midOrLess(pos, neg, mid);
            if (numOfMidOrLess >= k) right = mid;
            else left = mid;
        }
        out.println(right);

        out.close();
    }

    static long midOrLess(long[] pos, long[] neg, long mid) {
        long forPos = getNumOfLessOrEqual(pos, mid);
        long forNeg = getNumOfLessOrEqual(neg, mid);
        long forBoth = getNumOfLessOrEqual(pos, neg, mid);
        long total = forBoth + forNeg + forPos;
        if (mid >= 0) total += zeroCount;
        return total;
    }

    static long getNumOfLessOrEqual(long[] nums, long k) {
        long res = 0;
        long right = nums.length - 1;
        for (long left = 0; left < nums.length; left++) {
            if (left >= right) break;
            while (right > left && nums[(int)left] * nums[(int)right] > k) right--;
            res += right - left;
        }
        return res;
    }

    static long getNumOfLessOrEqual(long[] pos, long[] neg, long k) {
        long res = 0;
        long negPointer = 0;

        for (int pointer = pos.length - 1; pointer >= 0; pointer--) {
            if (negPointer >= neg.length) break;
            while (negPointer < neg.length && pos[pointer] * neg[(int) negPointer] > k) negPointer++;
            res += neg.length - negPointer;
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
