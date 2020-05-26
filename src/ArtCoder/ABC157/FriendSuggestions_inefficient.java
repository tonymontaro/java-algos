package ArtCoder.ABC157;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

public class FriendSuggestions_inefficient {
    static PrintWriter out;

    public static void main(String[] args) throws IOException {
        out = new PrintWriter(new OutputStreamWriter(System.out));
        CF_Reader in = new CF_Reader();

        int n = in.intNext();
        int m = in.intNext();
        int k = in.intNext();

        Map<Integer, Node> nodes = new HashMap<>();
        for (int i = 1; i < n + 1; i++) nodes.put(i, new Node(i));
        for (int i = 0; i < m; i++) {
            Node a = nodes.get(in.intNext());
            Node b = nodes.get(in.intNext());
            a.friend(b);
            b.friend(a);
        }
        for (int i = 0; i < k; i++) {
            Node a = nodes.get(in.intNext());
            Node b = nodes.get(in.intNext());
            a.block(b);
            b.block(a);
        }
        int gNum = 1;
        for (Node node: nodes.values()) {
            if (node.group == null) setGroup(node, new Group(gNum++));
        }

        for (int i = 1; i < n + 1; i++) {
            Node node = nodes.get(i);
            int groupNum = node.group.num;
            int outBlocks = 0;
            for (Node nd: node.blocks.values()) if (nd.group.num != groupNum) outBlocks++;
            out.printf("%d ", node.group.total - 1 - (node.blocks.size() - outBlocks) - node.friends.size());
        }

        out.close();
    }

    static void setGroup(Node node, Group group) {
        group.add(node);
        for (Node nd: node.friends.values()) {
            if (nd.group == null) setGroup(nd, group);
        }
    }

    static class Node {
        int val;
        Map<Integer, Node> friends = new HashMap<>();
        Map<Integer, Node> blocks = new HashMap<>();
        Group group = null;
        public Node(int val) {
            this.val = val;
        }
        void block(Node node) {
            blocks.put(node.val, node);
        }
        void friend(Node node) {
            friends.put(node.val, node);
        }
    }

    static class Group {
        int total;
        int num;

        public Group(int num){
            this.num = num;
        }

        void add(Node node) {
            node.group = this;
            total++;
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

        public int[] nextIntArray (final int n) throws IOException {
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
