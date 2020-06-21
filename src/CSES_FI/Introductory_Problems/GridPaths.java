package CSES_FI.Introductory_Problems;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Stack;
import java.util.StringTokenizer;

public class GridPaths {
    static PrintWriter out;
    static CF_Reader in;

    public static void main(String[] args) throws IOException {
        out = new PrintWriter(new OutputStreamWriter(System.out));
        in = new CF_Reader();

        char[] arr = "??????R??????U??????????????????????????LD????D?".toCharArray();

        long startTime = System.nanoTime();

        Solver solver = new Solver(arr);
        out.println(solver.res);

        long timeElapsed = System.nanoTime() - startTime;
        System.out.println("Execution time in milliseconds: " + timeElapsed / 1000000);

        out.close();
    }

    static class Solver {
        char[] directions;
        long res = 0;
        int steps = 0;
        int n = 7;
        boolean[][] grid;

        public Solver(char[] directions) {
            this.directions = directions;

            grid = new boolean[7][7];
            this.res = solve(0, 0, 0);
        }

        long solve(int idx, int row, int col) {
            if (row == 6 && col == 0 && this.steps == 48) {
                return 1;
            }
            else if (row < 0 || row >= n || col < 0 || col >= n || grid[row][col] || idx >= directions.length) return 0;
            else {
                long res = 0;
                this.steps++;
                grid[row][col] = true;
                char ch = directions[idx];
                if ((ch == '?' || ch == 'R') && row != 6) res += solve(idx + 1, row, col + 1);
                if ((ch == '?' || ch == 'L') && row != 0) res += solve(idx + 1, row, col - 1);
                if ((ch == '?' || ch == 'U') && col != 6 && col != 0) res += solve(idx + 1, row - 1, col);
                if ((ch == '?' || ch == 'D')) res += solve(idx + 1, row + 1, col);
                grid[row][col] = false;
                this.steps--;
                return res;
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


