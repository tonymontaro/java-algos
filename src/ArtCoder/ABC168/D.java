package ArtCoder.ABC168;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.*;

public class D {
    static PrintWriter out;
    static CF_Reader in;

    public static void main(String[] args) throws IOException {
        out = new PrintWriter(new OutputStreamWriter(System.out));
        in = new CF_Reader();

        int n = in.intNext();
        int m = in.intNext();

        ArrayList<Integer>[] adjacency = new ArrayList[n+1];
        for (int i = 1; i <= n; i++) adjacency[i] = new ArrayList<>();
        Integer[] parents = new Integer[n+1];
        Arrays.fill(parents, null);
        parents[1] = 0;

        for (int i = 0; i < m; i++) {
            int a = in.intNext(), b = in.intNext();

            adjacency[a].add(b);
            adjacency[b].add(a);
        }

        Deque<Integer> deque = new LinkedList<>();
        deque.add(1);
        while (deque.size() > 0) {
            int node = deque.pollFirst();
            for (int child: adjacency[node]) {
                if (parents[child] == null) {
                    parents[child] = node;
                    deque.add(child);
                }
            }
        }
        out.println(getResult(n, parents));

        out.close();
    }

    static StringBuilder getResult(int n, Integer[] parents) {
        StringBuilder res = new StringBuilder("Yes\n");
        for (int i = 2; i <= n; i++) {
            if (parents[i] == null) return new StringBuilder("No\n");
            res.append(parents[i]).append("\n");
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


