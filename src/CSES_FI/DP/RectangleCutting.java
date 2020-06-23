package CSES_FI.DP;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

public class RectangleCutting {
    static PrintWriter out;
    static CF_Reader in;

    public static void main(String[] args) throws IOException {
        out = new PrintWriter(new OutputStreamWriter(System.out));
        in = new CF_Reader();

        int row = in.intNext(), col = in.intNext();
        if (row > col) {
            int temp = col;
            col =row;
            row = temp;
        }
        out.println(getMinCuts(row, col));

        out.close();
    }

    static int getMinCuts(int row, int col) {
        int[][] bestCuts = new int[row + 1][col + 1];
        for (int r = 1; r <= row; r++) {
            Arrays.fill(bestCuts[r], Integer.MAX_VALUE);
            for (int c = 1; c <= col; c++) {
                if (r > c) bestCuts[r][c] = bestCuts[c][r];
                else if (r == c) bestCuts[r][c] = 0;
                else {
                    for (int j = 1; j < c; j++) {
                        int v1 = bestCuts[r][j];
                        int v2 = bestCuts[r][c - j];
                        bestCuts[r][c] = Math.min(1 + v1 + v2, bestCuts[r][c]);
                    }
                    for (int j = 1; j < r; j++) {
                        int v1 = bestCuts[j][c];
                        int v2 = bestCuts[r - j][c];
                        bestCuts[r][c] = Math.min(1 + v1 + v2, bestCuts[r][c]);
                    }
                }
            }
        }
        return bestCuts[row][col];
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


