package CodeNCode.DynamicProgramming;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.HashMap;
import java.util.StringTokenizer;

public class ChefGotRecipes {
    static PrintWriter out;
    static CF_Reader in;

    public static void main(String[] args) throws IOException {
        out = new PrintWriter(new OutputStreamWriter(System.out));
        in = new CF_Reader();

        HashMap<Character, Integer> mapping = new HashMap<>();
        mapping.put('a', 1);
        mapping.put('e', 2);
        mapping.put('i', 4);
        mapping.put('o', 8);
        mapping.put('u', 16);
        int allFilled = (1 << 5) - 1;

        int tests = in.intNext();
        StringBuilder res = new StringBuilder();
        for (int t = 0; t < tests; t++) {
            int n = in.intNext();
            HashMap<Integer, Integer> nums = new HashMap<>();
            for (int j = 0; j < n; j++) {
                String recipe = in.next();
                int num = 0;
                for (int i = 0; i < recipe.length(); i++) {
                    num = num | mapping.get(recipe.charAt(i));
                }
                nums.merge(num, 1, Integer::sum);
            }


            int[] keys = new int[nums.size()];
            int idx = 0;
            for (int num: nums.keySet()) keys[idx++] = num;
            long total = 0;
            for (int i = 0; i < keys.length; i++) {
                if (keys[i] == 31) {
                    int tt = nums.get(31);
                    total += (tt * (tt - 1)) / 2;
                }
                for (int j = i + 1; j < keys.length; j++) {
                    if ((keys[i] | keys[j]) == allFilled) total += nums.get(keys[i]) * nums.get(keys[j]);
                }
            }
            res.append(total).append("\n");
        }
        out.println(res);

        out.close();
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
