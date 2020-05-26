package ArtCoder.ABC157;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

public class Bingo {
    static PrintWriter out;

    public static void main(String[] args) throws IOException {
        out = new PrintWriter(new OutputStreamWriter(System.out));
        CF_Reader in = new CF_Reader();

        int[][] grid = new int[3][3];
        for (int r = 0; r < 3; r++) for (int c = 0; c < 3; c++) grid[r][c] = in.intNext();
        int n = in.intNext();
        Map<Integer, Boolean> seen = new HashMap<>();
        for (int t = 0; t < n; t++) seen.put(in.intNext(), true);

        out.println(solve(grid, seen));

        out.close();
    }

    static String solve(int[][] grid, Map<Integer, Boolean> seen) {
        for (int r = 0; r < 3; r++) {
            if (seen.containsKey(grid[r][0]) && seen.containsKey(grid[r][1]) && seen.containsKey(grid[r][2])) return "Yes";
            if (seen.containsKey(grid[0][r]) && seen.containsKey(grid[1][r]) && seen.containsKey(grid[2][r])) return "Yes";
        }
        if (seen.containsKey(grid[0][0]) && seen.containsKey(grid[1][1]) && seen.containsKey(grid[2][2])) return "Yes";
        if (seen.containsKey(grid[2][0]) && seen.containsKey(grid[1][1]) && seen.containsKey(grid[0][2])) return "Yes";
        return "No";
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

        String line() throws IOException {
            return br.readLine().trim();
        }
    }
}
