package CSES_FI.Book.Cp2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.StringTokenizer;

public class Backtracking_N_Queens {
    static PrintWriter out;
    static CF_Reader in;

    public static void main(String[] args) throws IOException {
        out = new PrintWriter(new OutputStreamWriter(System.out));
        in = new CF_Reader();

        /*
           Problem: Place n queens in a board with none of them attacking another queen
         */
        int size = 4; // 4 * 4 board

        /*
         diagonal1 generation produces (for illustration, not part of solution)
            0  1  2  3
            1  2  3  4
            2  3  4  5
            3  4  5  6
         */
        for (int row = 0; row < size; row++) {
            for (int col = 0; col < size; col++) {
                out.printf("%d  ", row + col);
            }
            out.println();
        }
        out.println("=============");

        /*
        diagonal2 generation produces (for illustration, not part of solution)
            3  4  5  6
            2  3  4  5
            1  2  3  4
            0  1  2  3
         */
        for (int row = 0; row < size; row++) {
            for (int col = 0; col < size; col++) {
                out.printf("%d  ", (size - row - 1) + col);
            }
            out.println();
        }
        out.println("=============");


        // Solution
        Solver solver = new Solver(size);
        out.println(solver.result);

        out.close();
    }

    static class Solver {
        boolean[] diagonal1;
        boolean[] diagonal2;
        int[] rows;
        boolean[] cols;
        int size;
        int result = 0;

        public Solver(int n) {
            size = n;
            cols = new boolean[n];
            diagonal1 = new boolean[n * 2 - 1];
            diagonal2 = new boolean[n * 2 - 1];
            solve(0);
        }

        void solve(int row) {
            if (row == size) result++;
            else {
                for (int col = 0; col < size; col++) {
                    if (!cols[col] && !diagonal1[row + col] && !diagonal2[(size - row - 1) + col]) {
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


