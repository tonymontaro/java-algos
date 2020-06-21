package CodeForces.CF_1370;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.StringTokenizer;

public class NumberGame {
    static PrintWriter out;
    static CF_Reader in;

    public static void main(String[] args) throws IOException {
        out = new PrintWriter(new OutputStreamWriter(System.out));
        in = new CF_Reader();

        int cases = in.intNext();
        StringBuilder result = new StringBuilder();
        for (int t = 0; t < cases; t++) {
            long n = in.longNext();
            HashMap<String, String> mapping = new HashMap<>();
            mapping.put("Ashishgup", "FastestFinger");
            mapping.put("FastestFinger", "Ashishgup");
            String currentPlayer = "Ashishgup";
            int status = canWin(n);
            result.append((status == 1) ? currentPlayer : mapping.get(currentPlayer)).append("\n");
        }
        out.println(result);
//        out.println(countOddDivisors(42));

        out.close();
    }

    static int canWin(long n) {
        if (n == 1) return 0;
        if (n == 2) return 1;
        if (n % 2 == 1) return 1;
        if (isPowerOfTwo(n)) return 0;
        if (n % 4 == 0) return 1;
        return (countOddDivisors(n / 2) > 1) ? 1 : 0;
    }

    static int countOddDivisors(long n) {
        long num = n;
        int odds = 0;
        long i = 3;
        while (i * i <= n) {
            if (num % i == 0) {
                return 2;
            }
            i += 2;
        }
        return odds;
    }

    static boolean isPowerOfTwo(long n) {
        return (n & (n - 1)) == 0;
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


