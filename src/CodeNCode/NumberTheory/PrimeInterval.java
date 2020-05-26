package CodeNCode.NumberTheory;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

public class PrimeInterval {
    static PrintWriter out;
    static CF_Reader in;
    static ArrayList<Integer> primes = new ArrayList<>();
    static boolean[] nonPrimes;

    public static void main(String[] args) throws IOException {
        out = new PrintWriter(new OutputStreamWriter(System.out));
        in = new CF_Reader();

        int left = in.intNext();
        int right = in.intNext();
        generatePrimes(right);
        StringBuilder res = new StringBuilder();
        for (int i = left; i <= right; i++) {
            if (!nonPrimes[i]) res.append(i).append(" ");
        }
        out.println(res);

        out.close();
    }

    static void generatePrimes(int maxNum) {
        int num = 2;
        nonPrimes = new boolean[maxNum + 1];
        nonPrimes[0] = true;
        nonPrimes[1] = true;
        while (num <= maxNum) {
            if (!nonPrimes[num]) primes.add(num);
            for (int p: primes) {
                int mult = p * num;
                if (mult > maxNum) break;
                nonPrimes[mult] = true;
            }
            num++;
        }
    }
}

class CF_Reader {
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
