package CSES_FI.Graphs;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.*;

public class Labyrinth {
    static PrintWriter out;
    static CF_Reader in;
    static char[][] grid;
    static int rowLen;
    static int colLen;

    public static void main(String[] args) throws IOException {
        out = new PrintWriter(new OutputStreamWriter(System.out));
        in = new CF_Reader();

        rowLen = in.intNext();
        colLen = in.intNext();
        grid = new char[rowLen][colLen];
        for (int i = 0; i < rowLen; i++) {
            grid[i] = in.next().toCharArray();
        }
        out.println(solve());

        out.close();
    }

    static StringBuilder solve() {
        for (int r = 0; r < rowLen; r++) {
            for (int c = 0; c < colLen; c++) {
                if (grid[r][c] == 'A') {
                    return bfs(r, c);
                }
            }
        }
        return new StringBuilder();
    }

    static class Node{
        int dist;
        String prevDir;
        Node parent;
        int r;
        int c;

        public Node(int d, String pDir, Node p, int row, int col) {
            this.dist = d;
            this.prevDir = pDir;
            this.parent = p;
            this.r = row;
            this.c = col;
        }
    }

    static StringBuilder bfs(int row, int col) {
        String[][] dirs = new String[][]{{"", "U"}, {"L", "", "R"}, {"", "D"}};
        Deque<Node> deque = new LinkedList<>();
        Node first = new Node(0, "", null, row, col);
        deque.add(first);
        while (deque.size() > 0) {
            Node node = deque.pollFirst();
            if (grid[node.r][node.c] == '#') continue;
            if (grid[node.r][node.c] == 'B') return createAns(node);
            grid[node.r][node.c] = '#';
            for (int[] dir : new int[][]{{1, 2}, {1, 0}, {2, 1}, {0, 1}}) {
                int r = node.r + dir[0]-1;
                int c = node.c + dir[1]-1;
                if (r >= 0 && c >= 0 && r < rowLen && c < colLen && grid[r][c] != '#') {
                    Node child = new Node(node.dist + 1, dirs[dir[0]][dir[1]], node, r, c);
                    deque.add(child);
                }
            }
        }
        return new StringBuilder("NO");
    }

    static StringBuilder createAns(Node node) {
        StringBuilder res = new StringBuilder();
        res.append("YES\n").append(node.dist).append("\n");
        StringBuilder pattern = new StringBuilder();
        while (node != null) {
            pattern.append(node.prevDir);
            node = node.parent;
        }
        return res.append(pattern.reverse());
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


