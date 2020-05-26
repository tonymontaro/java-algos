package CodeNCode.DynamicProgramming2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.*;

public class LongestRegularBracketSequence {
    static PrintWriter out;
    static CF_Reader in;

    public static void main(String[] args) throws IOException {
        out = new PrintWriter(new OutputStreamWriter(System.out));
        in = new CF_Reader();


        out.println(solve(in.next()));
        out.close();
    }

    static String solve(String word) {
        ArrayList<int[]> regular = new ArrayList<>();

        Stack<Integer> stack = new Stack<>();
        int minVal = -1;
        for (int i = 0; i < word.length(); i++) {
            char bracket = word.charAt(i);
            if (bracket == '(') {
                stack.push(i);
                if (minVal == -1) minVal = i;
            } else if (bracket == ')' && stack.size() > 0) {
                regular.add(new int[]{stack.pop(), i});
            }
        }
        if (regular.size() == 0) return "0 1";
        regular.sort(Comparator.comparingInt(o -> o[0]));

        int longest = 0;
        HashMap<Integer, Integer> ln = new HashMap<>();
        int start = regular.get(0)[0];
        int end = regular.get(0)[1];
        for (int[] pair: regular) {
            if (pair[0] - end > 1) {
                longest = Math.max(longest, end - start + 1);
                ln.merge(end - start + 1, 1, Integer::sum);
                start = pair[0];
                end = pair[1];
            } else {
                end = Math.max(end, pair[1]);
            }
        }
        longest = Math.max(longest, end - start + 1);
        ln.merge(end - start + 1, 1, Integer::sum);

        return String.format("%d %d", longest, ln.get(longest));
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
