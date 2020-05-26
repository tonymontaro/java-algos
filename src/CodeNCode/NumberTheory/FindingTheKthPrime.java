package CodeNCode.NumberTheory;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.StringTokenizer;

// https://www.spoj.com/problems/TDKPRIME/
public class FindingTheKthPrime {
    static PrintWriter out;
    static CF_Reader in;
    static int maxNum = Integer.MIN_VALUE;
    static ArrayList<Integer> primes = new ArrayList<>();

    public static void main(String[] args) throws IOException {
        out = new PrintWriter(new OutputStreamWriter(System.out));
        in = new CF_Reader();

        int tests = in.intNext();
        int[] cases = new int[tests];
        for (int t = 0; t < tests; t++) {
            cases[t] = in.intNext();
            maxNum = Math.max(maxNum, cases[t]);
        }
//        long startTime = System.nanoTime();
        generatePrimes(maxNum);
        StringBuilder res = new StringBuilder();
        for (int n: cases) res.append(primes.get(n - 1)).append("\n");
        out.println(res);
//        long timeElapsed = System.nanoTime() - startTime;
//        System.out.println("Execution time in milliseconds: " + timeElapsed / 1000000);

        out.close();
    }

    static void generatePrimes(int maxNum) {
        int num = 2;
        boolean[] nonPrimes = new boolean[87000009];
        while (primes.size() < maxNum) {
            if (!nonPrimes[num]) primes.add(num);
            for (int p: primes) {
                int mult = p * num;
                if (mult > 87000008) break;
                nonPrimes[mult] = true;
            }
            num++;
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
