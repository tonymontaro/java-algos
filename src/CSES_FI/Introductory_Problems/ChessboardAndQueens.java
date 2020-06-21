package CSES_FI.Introductory_Problems;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.StringTokenizer;

public class ChessboardAndQueens {
    static PrintWriter out;
    static CF_Reader in;

    public static void main(String[] args) throws IOException {
        out = new PrintWriter(new OutputStreamWriter(System.out));
        in = new CF_Reader();

        boolean[][] invalid = new boolean[8][8];
        for (int r = 0; r < 8; r++) {
            char[] row = in.next().toCharArray();
            for (int c = 0; c < 8; c++) {
                if (row[c] == '*') invalid[r][c] = true;
            }
        }

        Solver solver = new Solver(8, invalid);
        out.println(solver.result);

        out.close();
    }

    static class Solver {
        boolean[] diagonal1;
        boolean[] diagonal2;
        boolean[] cols;
        int size;
        int result = 0;
        boolean[][] invalid;

        public Solver(int n, boolean[][] invalid) {
            size = n;
            cols = new boolean[n];
            diagonal1 = new boolean[n * 2 - 1];
            diagonal2 = new boolean[n * 2 - 1];
            this.invalid = invalid;
            solve(0);
        }

        void solve(int row) {
            if (row == size) result++;
            else {
                for (int col = 0; col < size; col++) {
                    if (!cols[col] && !diagonal1[row + col] && !diagonal2[(size - row - 1) + col] && !invalid[row][col]) {
                        cols[col] = true;
                        diagonal1[row + col] = true;
                        diagonal2[(size - row - 1) + col] = true;
                        solve(row + 1);
                        cols[col] = false;
                        diagonal1[row + col] = false;
                        diagonal2[(size - row - 1) + col] = false;
                    }
                }
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


