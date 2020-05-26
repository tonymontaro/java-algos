package ArtCoder.ABC157;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.StringTokenizer;

public class FriendSuggestions {
    static PrintWriter out;

    public static void main(String[] args) throws IOException {
        out = new PrintWriter(new OutputStreamWriter(System.out));
        CF_Reader in = new CF_Reader();

        int n = in.intNext();
        int m = in.intNext();
        int k = in.intNext();

        UnionFind unionFind = new UnionFind(n + 1);
        int[] friendsAndConnectedBlocks = new int[n + 1];

        for (int i = 0; i < m; i++) {
            int p = in.intNext();
            int q = in.intNext();
            unionFind.unify(p, q);
            friendsAndConnectedBlocks[p]++;
            friendsAndConnectedBlocks[q]++;
        }
        for (int i = 0; i < k; i++) {
            int p = in.intNext();
            int q = in.intNext();
            if (unionFind.connected(p, q)) {
                friendsAndConnectedBlocks[p]++;
                friendsAndConnectedBlocks[q]++;
            }
        }
        StringBuilder output = new StringBuilder();
        for (int i = 1; i < n + 1; i++)
            output.append(unionFind.getSize(i) - friendsAndConnectedBlocks[i] - 1).append(" ");
        out.println(output);

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

        String line() throws IOException {
            return br.readLine().trim();
        }
    }
}
