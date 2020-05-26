package CodeForces.CodeforcesPractice;

import java.util.*;

public class AntiSudoku {

    static int[][] solve(int[][] grid) {
        int[] coord = new int[2];

        for (int r = 0; r < 3; r++) {
            coord[0] = r * 3;
            coord[1] = r;

            for (int c = 0; c < 3; c++) {
                if (c > 0) {
                    coord[0] += 1;
                    coord[1] += 3;
                }
                int row = coord[0];
                int col = coord[1];
                if (row == 8) grid[row][col] = grid[row][col-1];
                else grid[row][col] = grid[row][col+1];

                String converted = Arrays.toString(grid[row]);
                System.out.print(converted.substring(1, converted.length()-1).replace(", ", "") + "\n");
            }
        }
        return grid;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int tests = sc.nextInt();
        for (int t = 0; t < tests; t++) {
            int[][] grid = new int[9][9];
            for (int r = 0; r < 9; r++) {
                String line = sc.next();
                for (int c = 0; c < 9; c++) grid[r][c] = Integer.parseInt(String.valueOf(line.charAt(c)));
            }
            solve(grid);
        }
    }
}
