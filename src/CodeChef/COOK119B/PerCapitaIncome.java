package CodeChef.COOK119B;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.*;


public class PerCapitaIncome {
    static PrintWriter out;
    static CF_Reader in;
    static HashMap<Integer, Integer> countCache;

    public static void main(String[] args) throws IOException {
        out = new PrintWriter(new OutputStreamWriter(System.out));
        in = new CF_Reader();

        int cases = in.intNext();

        for (int t = 0; t < cases; t++) {
            int n = in.intNext(), M = in.intNext();

            double[] income = in.nextDoubleArray(n);
            double[] population = in.nextDoubleArray(n);
            double[] perCapital = new double[n];

            double maxPerCapital = 0;
            for (int i = 0; i < n; i++) {
                perCapital[i] = (income[i] / population[i]);
                maxPerCapital = Math.max(maxPerCapital, perCapital[i]);
            }

            HashSet<Integer> valid = new HashSet<>();
            for (int i = 1; i <= n; i++) if (perCapital[i-1] == maxPerCapital) valid.add(i);

            UnionFind unionFind = new UnionFind(n);

            HashMap<Integer, Stack<Integer>> mapping = new HashMap<>();
            for (int num: valid) mapping.put(num, new Stack<>());
            for (int i = 0; i < M; i++) {
                int a = in.intNext();
                int b = in.intNext();
                if (valid.contains(a) && valid.contains(b)) {
                    unionFind.unify(a-1, b-1);
                }
            }
            int maxSize = 0;
            int parent = 0;
            for (int v: valid) {
                int size = unionFind.getSize(v-1);
                if (size > maxSize) {
                    maxSize = size;
                    parent = unionFind.find(v-1);
                }
            }

            StringBuilder result = new StringBuilder();

            for (int v: valid) if (unionFind.find(v-1) == parent) result.append(v).append(" ");
            out.println(maxSize);
            out.println(result);
        }

        out.close();
    }

    static class UnionFind {

        private final int size;
        private int groups;
        private final int[] sizes;
        private final int[] parents;

        public int getSize() {
            return size;
        }

        public int getGroups() {
            return groups;
        }

        public int getSize(int p) {
            return sizes[find(p)];
        }

        public UnionFind(int n) {
            this.size = this.groups = n;
            sizes = new int[n];
            parents = new int[n];

            for (int i = 0; i < n; i++) {
                parents[i] = i;
                sizes[i] = 1;
            }
        }

        public int find(int p) {
            int root = p;
            while (root != parents[root]) root = parents[root];

            while (p != root) {
                int temp = parents[p];
                parents[p] = root;
                p = temp;
            }
            return root;
        }

        public boolean connected(int p, int q) {
            return find(p) == find(q);
        }

        public void unify(int p, int q) {
            int root1 = find(p);
            int root2 = find(q);
            if (root1 == root2) return;

            if (sizes[root1] < sizes[root2]) {
                parents[root1] = root2;
                sizes[root2] += sizes[root1];
            } else {
                parents[root2] = root1;
                sizes[root1] += sizes[root2];
            }
            groups--;
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

        public double[] nextDoubleArray(final int n) throws IOException {
            final double[] a = new double[n];
            for (int i = 0; i < n; i++)
                a[i] = doubleNext();
            return a;
        }

        String line() throws IOException {
            return br.readLine().trim();
        }
    }
}


