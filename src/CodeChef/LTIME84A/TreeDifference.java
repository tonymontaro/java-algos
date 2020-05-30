package CodeChef.LTIME84A;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.*;

public class TreeDifference {
    static PrintWriter out;
    static CF_Reader in;

    public static void main(String[] args) throws IOException {
        out = new PrintWriter(new OutputStreamWriter(System.out));
        in = new CF_Reader();

        int tests = in.intNext();
        for (int t = 0; t < tests; t++) {
            int n = in.intNext();
            int queries = in.intNext();
            HashMap<Integer, Node> cache = new HashMap<>();
            for (int i = 0; i < n; i++) {
                cache.put(i+1, new Node(i+i, in.intNext()));
            }
            cache.get(1).depth = 0;
            for (int i = 0; i < n-1; i++) {
                Node a = cache.get(in.intNext());
                Node b = cache.get(in.intNext());
                a.children.add(b);
                b.parent = a;
            }

            updateDepths(cache.get(1));

            StringBuilder res = new StringBuilder();
            for (int i = 0; i < queries; i++) {
                Node a = cache.get(in.intNext());
                Node b = cache.get(in.intNext());
                ArrayList<Integer> arr = new ArrayList<>();
                if (a.depth > b.depth) {
                    Node tmp = b;
                    b = a;
                    a = tmp;
                }
                while (b.depth > a.depth) {
                    arr.add(b.val);
                    b = b.parent;
                }
                while (a.idx != b.idx) {
                    arr.add(a.val);
                    arr.add(b.val);
                    a = a.parent;
                    b = b.parent;
                }
                arr.add(a.val);

                Collections.sort(arr);
                int minVal = Integer.MAX_VALUE;
                for (int j = 1; j < arr.size(); j++) minVal = Math.min(minVal, arr.get(j) - arr.get(j-1));
                res.append(minVal).append("\n");
            }
            out.println(res);
        }

        out.close();
    }

    static void updateDepths(Node node) {
        for (Node child: node.children) {
            child.depth = node.depth + 1;
            updateDepths(child);
        }
    }

    static class Node {
        Node parent = null;
        int val;
        int idx;
        int depth;
        ArrayList<Node> children = new ArrayList<>();

        public Node(int idx, int val) {
            this.val = val;
            this.idx = idx;
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
