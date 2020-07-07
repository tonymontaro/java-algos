package ArtCoder.ABC173;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.*;

public class E {
    static PrintWriter out;
    static CF_Reader in;
    static long MOD = 1000000007;
    static ArrayList<Long> negs;
    static ArrayList<Long> pos;

    public static void main(String[] args) throws IOException {
        out = new PrintWriter(new OutputStreamWriter(System.out));
        in = new CF_Reader();

        int n = in.intNext(), k = in.intNext();
        negs = new ArrayList<>();
        pos = new ArrayList<>();
        int zeros = 0;
        for (int i = 0; i < n; i++) {
            long num = in.longNext();
            if (num < 0) negs.add(num);
            else if (num > 0) pos.add(num);
            else zeros++;
        }
        out.println(solve(k, zeros));


        out.close();
    }

    static long solve(int k, int zeros) {
        boolean isNeg = (pos.size() == 0 && k % 2 == 1);
        if ((isNeg && zeros > 0) || k > (pos.size() + negs.size())) return 0;
        Collections.sort(negs);
        Collections.sort(pos);
        if (isNeg || k == (pos.size() + negs.size())) return minimize(k);
        return maximize(k);
    }

    static long maximize(int k) {
        long res = 1;
        int count = 0;
        int p = pos.size()-1;
        if (k % 2 == 1) {
            res = pos.get(p);
            p--;
            count++;
        }
        int n = 0;
        while (count < k) {
            if (p < 1 || ((n < negs.size() - 1) && (negs.get(n)*negs.get(n+1) > pos.get(p)*pos.get(p-1)))) {
                res = (res * ((negs.get(n)*negs.get(n+1)) % MOD)) % MOD;
                n += 2;
            } else {
                res = (res * ((pos.get(p)*pos.get(p-1)) % MOD)) % MOD;
                p -= 2;
            }
            count += 2;
        }
        return (res + MOD) % MOD;
    }


    static long minimize(int k) {
        long res = 1;
        int n = negs.size()-1;
        for (int i = 0; i < k; i++) {
            if (n == -1) break;
            res = (((res * negs.get(n)) % MOD) + MOD) % MOD;
            n--;
        }

        for (int i = 0; i < (k - negs.size()); i++) {
            res = (res * pos.get(i)) % MOD;
        }
        return (res + MOD) % MOD;
    }


    static class CF_Reader {
        BufferedReader br;
        StringTokenizer st;

        public CF_Reader() throws IOException {
            br = new BufferedReader(new InputStreamReader(System.in));
        }

        public ArrayList<Integer>[] adjacencyList(int n, int m) throws IOException {
            ArrayList<Integer>[] adj = new ArrayList[n + 1];
            for (int i = 1; i <= n; i++) adj[i] = new ArrayList<>();
            for (int i = 0; i < m; i++) {
                int a = intNext(), b = intNext();
                adj[a].add(b);
                adj[b].add(a);
            }
            return adj;
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

    static class util {
        public static int upperBound(long[] array, long obj) {
            int l = 0, r = array.length - 1;
            while (r - l >= 0) {
                int c = (l + r) / 2;
                if (obj < array[c]) {
                    r = c - 1;
                } else {
                    l = c + 1;
                }
            }
            return l;
        }

        public static int upperBound(ArrayList<Long> array, long obj) {
            int l = 0, r = array.size() - 1;
            while (r - l >= 0) {
                int c = (l + r) / 2;
                if (obj < array.get(c)) {
                    r = c - 1;
                } else {
                    l = c + 1;
                }
            }
            return l;
        }

        public static int lowerBound(long[] array, long obj) {
            int l = 0, r = array.length - 1;
            while (r - l >= 0) {
                int c = (l + r) / 2;
                if (obj <= array[c]) {
                    r = c - 1;
                } else {
                    l = c + 1;
                }
            }
            return l;
        }

        public static int lowerBound(ArrayList<Long> array, long obj) {
            int l = 0, r = array.size() - 1;
            while (r - l >= 0) {
                int c = (l + r) / 2;
                if (obj <= array.get(c)) {
                    r = c - 1;
                } else {
                    l = c + 1;
                }
            }
            return l;
        }

        public static void print(long[] arr) {
            System.out.println(Arrays.toString(arr));
        }

        public static void print(int[] arr) {
            System.out.println(Arrays.toString(arr));
        }

        public static void print(char[] arr) {
            System.out.println(Arrays.toString(arr));
        }
    }

    static class Tuple implements Comparable<Tuple> {
        int a;
        int b;

        public Tuple(int a, int b) {
            this.a = a;
            this.b = b;
        }

        public int getA() {
            return a;
        }

        public int getB() {
            return b;
        }

        public int compareTo(Tuple other) {
            if (this.a == other.a) return Integer.compare(this.b, other.b);
            return Integer.compare(this.a, other.a);
        }

        @Override
        public int hashCode() {
            return Arrays.deepHashCode(new Integer[]{a, b});
        }

        @Override
        public boolean equals(Object o) {
            if (!(o instanceof Tuple)) return false;
            Tuple pairo = (Tuple) o;
            return (this.a == pairo.a && this.b == pairo.b);
        }

        @Override
        public String toString() {
            return String.format("%d,%d  ", this.a, this.b);
        }
    }
}


