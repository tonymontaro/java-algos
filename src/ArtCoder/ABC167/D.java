package ArtCoder.ABC167;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.StringTokenizer;

public class D {
    static PrintWriter out;
    static CF_Reader in;

    public static void main(String[] args) throws IOException {
        out = new PrintWriter(new OutputStreamWriter(System.out));
        in = new CF_Reader();

        int n = in.intNext();
        long k = in.longNext();
        int[] nums = in.nextIntArray(n);
        out.println(solve(k, nums));

        out.close();
    }

    static int solve(long k, int[] nums) {
        HashSet<Integer> seen = new HashSet<>();
        long moves = 0;
        int num = 1;
        while (moves < k) {
            if (seen.contains(num)) break;
            seen.add(num);
            num = nums[num - 1];
            moves += 1;
        }
        ArrayList<Integer> cycle = new ArrayList<>();
        if (moves == k) return num;
        int cursor = num;
        long loop = 0;
        cycle.add(num);
        while (true) {
            cursor = nums[cursor - 1];
            loop++;
            if (cursor == num) break;
            cycle.add(cursor);
        }
        return cycle.get((int) ((k - moves) % loop));
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
