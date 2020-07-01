package Others.AndelaCrackTheCode;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.*;

public class PalindromicGraph {
    static PrintWriter out;
    static CF_Reader in;


    public static void main(String[] args) throws IOException {
        out = new PrintWriter(new OutputStreamWriter(System.out));
        in = new CF_Reader();

//        int n = 5, m = 4;
//        List<Integer> gFrom = Arrays.asList(1, 1, 2, 2);
//        List<Integer> gTo = Arrays.asList(2, 3, 4, 5);
//        List<Integer> gWeight = Arrays.asList(1, 2, 1, 2);

        int n = in.intNext(), m = in.intNext();
        List<Integer> gFrom = new ArrayList<>();
        List<Integer> gTo = new ArrayList<>();
        List<Integer> gWeight = new ArrayList<>();
        for (int i = 0; i < m; i++) {
            gFrom.add(in.intNext());
            gTo.add(in.intNext());
            gWeight.add(in.intNext());
        }
        out.println(numNicePairs(n, gFrom, gTo, gWeight));

        out.close();
    }

    static boolean[] exploring;
    static ArrayList<Integer[]>[] adj;
    static long count = 0;
    public static long numNicePairs(int n, List<Integer> gFrom, List<Integer> gTo, List<Integer> gWeight) {
        int m = gFrom.size();
        exploring = new boolean[n + 1];
        adj = new ArrayList[n + 1];
        for (int i = 1; i <= n; i++) adj[i] = new ArrayList<>();
        for (int i = 0; i < m; i++) {
            adj[gFrom.get(i)].add(new Integer[]{gTo.get(i), gWeight.get(i)});
        }
        dfs(gFrom.get(0));
        return count;
    }

    static ArrayList<HashMap<Integer, Integer>> dfs(int node) {
        exploring[node] = true;
        ArrayList<HashMap<Integer, Integer>> res = new ArrayList<>();
        res.add(new HashMap<>());
        ArrayList<ArrayList<HashMap<Integer, Integer>>> lists =  new ArrayList<>();
        int localCount = 0;
        for (Integer[] child : adj[node]) {
            if (!exploring[child[0]]) {
                ArrayList<HashMap<Integer, Integer>> tmp = dfs(child[0]);
                for (HashMap<Integer, Integer> mp: tmp) {
                    mp.merge(child[1], 1, Integer::sum);
                    localCount += countHash(mp);
                }
                lists.add(tmp);
            }
        }
//        out.printf("%d -> %d\n", node, localCount);
        countRes(lists);

        for (ArrayList<HashMap<Integer, Integer>> row: lists) res.addAll(row);
//        out.println(count);

        return res;
    }

    static int countHash(HashMap<Integer, Integer> mp) {
        int odds = 0;
        for (int v : mp.values()) {
            if (v % 2 == 1) odds++;
            if (odds > 1) return 0;
        }
        count++;
        return 1;
    }
    static int countHash(HashMap<Integer, Integer> mp1, HashMap<Integer, Integer> mp2) {
        int odds = 0;
        HashSet<Integer> seen = new HashSet<>();
        for (int k : mp1.keySet()) {
            seen.add(k);
            if ((mp1.get(k) + mp2.getOrDefault(k, 0)) % 2 == 1) odds++;
            if (odds > 1) return 0;
        }
        for (int k : mp2.keySet()) {
            if (!seen.contains(k) && (mp2.get(k) + mp1.getOrDefault(k, 0)) % 2 == 1) odds++;
            if (odds > 1) return 0;
        }
        count++;
        return 1;
    }

    static void countRes(ArrayList<ArrayList<HashMap<Integer, Integer>>> lists) {
        int n = lists.size();
        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                countResHelper(lists.get(i), lists.get(j));
            }
        }
    }

    static void countResHelper(ArrayList<HashMap<Integer, Integer>> pair1, ArrayList<HashMap<Integer, Integer>> pair2) {
//        out.printf("p1s: %d\n p2s: %d\n", pair1.size(), pair2.size());
        for (HashMap<Integer, Integer> mp1 : pair1) {
            for (HashMap<Integer, Integer> mp2: pair2) countHash(mp1, mp2);
        }
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


