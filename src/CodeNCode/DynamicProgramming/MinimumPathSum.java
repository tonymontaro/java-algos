package CodeNCode.DynamicProgramming;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.StringTokenizer;


// https://leetcode.com/problems/minimum-path-sum/
// https://leetcode.com/problems/minimum-falling-path-sum/
public class MinimumPathSum {
    static PrintWriter out;
    static CF_Reader in;

    public static void main(String[] args) throws IOException {
        out = new PrintWriter(new OutputStreamWriter(System.out));
        in = new CF_Reader();

        int[][] grid = new int[][]{
                {1, 3, 1},
                {1, 5, 1},
                {4, 2, 1}
        };
//        out.println(minPathSum(grid));
        out.println(minFallingPathSum(new int[][]{
                {5, 1, 2, 6},
                {9, 9, 7, 5},
                {3, 1, 4, 8}
        }));
        out.println(minFallingPathSum(new int[][]{
                {1, 2, 3},
                {4, 5, 6},
                {7, 8, 9}
        }));

        out.close();
    }

    static int minPathSum(int[][] grid) {
        int rowLen = grid.length;
        int colLen = grid[0].length;
        if (colLen == 0) return 0;
        for (int row = 0; row < rowLen; row++) {
            for (int col = 0; col < colLen; col++) {
                int prev = 0;
                if (col > 0 && row > 0) prev = Math.min(grid[row-1][col], grid[row][col-1]);
                else if (col > 0) prev = grid[row][col-1];
                else if (row > 0) prev = grid[row-1][col];
                grid[row][col] += prev;
            }
        }
        for (int[] r: grid) out.println(Arrays.toString(r));

        return grid[rowLen-1][colLen-1];
    }

    static int minFallingPathSum(int[][] grid) {
        int rowLen = grid.length;
        int colLen = grid[0].length;
        if (colLen == 0) return 0;

        for (int row = 1; row < rowLen; row++) {
            for (int col = 0; col < colLen; col++) {
                int prev = grid[row-1][col];
                if (col > 0) prev = Math.min(prev, grid[row-1][col-1]);
                if (col < colLen-1) prev = Math.min(prev, grid[row-1][col+1]);
                grid[row][col] += prev;
            }
        }
        for (int[] r: grid) out.println(Arrays.toString(r));
        int minVal = grid[rowLen - 1][0];
        for (int num: grid[rowLen - 1]) if (num < minVal) minVal = num;
        return minVal;
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
