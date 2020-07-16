package GoogleKickStart.Year_2019.Year_2019_E;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.HashSet;
import java.util.StringTokenizer;

public class CherriesMesh {
    static PrintWriter out;
    static CF_Reader in;

    public static void main(String[] args) throws IOException {
        out = new PrintWriter(new OutputStreamWriter(System.out));
        in = new CF_Reader();

        int tests = in.intNext();
        for (int t = 0; t < tests; t++) {
            out.printf("Case #%d: %d\n", t+1, solve(in.intNext(), in.intNext()));
        }

        out.close();
    }

    static int solve(int n, int blackEdges) throws IOException {
        UnionFind disjoint = new UnionFind(n);
        for (int i = 0; i < blackEdges; i++) {
            int p = in.intNext() - 1;
            int q = in.intNext() - 1;
            disjoint.unify(p, q);
        }
        int reds = (disjoint.getGroups() - 1) * 2;
        int blacks = 0;
        HashSet<Integer> seen = new HashSet<>();
        for (int i = 0; i < n; i++) {
            int parent = disjoint.find(i);
            if (!seen.contains(parent)) {
                seen.add(parent);
                blacks += disjoint.getSize(parent) - 1;
            }
        }
        return reds + blacks;
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

        String line() throws IOException {
            return br.readLine().trim();
        }
    }
}
