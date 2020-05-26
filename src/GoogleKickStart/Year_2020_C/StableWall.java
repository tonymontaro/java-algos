package GoogleKickStart.Year_2020_C;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.HashMap;
import java.util.StringTokenizer;

public class StableWall {
    static PrintWriter out;
    static CF_Reader in;
    static HashMap<Character, Boolean> seenChars;
    static int rows;
    static int cols;

    public static void main(String[] args) throws IOException {
        out = new PrintWriter(new OutputStreamWriter(System.out));
        in = new CF_Reader();

        int tests = in.intNext();
        for (int t = 1; t <= tests; t++) {
            rows = in.intNext();
            cols = in.intNext();
            seenChars = new HashMap<>();
            char[][] grid = new char[rows][cols];
            for (int i = 0; i < rows; i++) {
                String line = in.line();
                for (int j = 0; j < cols; j++) {
                    char ch = line.charAt(j);
                    grid[i][j] = ch;
                    if (!seenChars.containsKey(ch)) seenChars.put(ch, false);
                }
            }
            char[][] wall = new char[rows][cols];
            for (char[] w: wall) Arrays.fill(w, '*');

            out.printf("Case #%d: %s\n", t, solve(wall, grid));
        }

        out.close();
    }

    static StringBuilder solve(char[][] wall, char[][] grid) {
        StringBuilder res = new StringBuilder();
        int k = seenChars.size();
        int prev = k;
        while (k > 0) {
            for (char ch: seenChars.keySet()) {
                if ((!seenChars.get(ch)) && check(wall, grid, ch)) {
                    res.append(ch);
                    k--;
                }
            }
            if (k == prev) break;
            prev = k;
        }

        return (k == 0) ? res : new StringBuilder("-1");
    }

    static boolean check(char[][] wall, char[][] grid, char ch) {
        for (int r = rows-1; r >= 0; r--) {
            for (int c = 0; c < cols; c++) {
                if (grid[r][c] != ch) continue;
                if (r != rows - 1 && wall[r + 1][c] == '*') {
                    reverse(wall, grid, ch);
                    return false;
                }
                wall[r][c] = ch;
            }
        }
        seenChars.put(ch, true);
        return true;
    }

    static void reverse(char[][] wall, char[][] grid, char ch) {
        for (int r = rows-1; r >= 0; r--) {
            for (int c = 0; c < cols; c++) {
                if (grid[r][c] == ch) wall[r][c] = '*';
            }
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
