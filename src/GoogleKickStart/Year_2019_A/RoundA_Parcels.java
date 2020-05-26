package GoogleKickStart.Year_2019_A;

import java.util.*;

public class RoundA_Parcels {

    static int computeDistance(int r, int c, int[][] grid, boolean shouldSetNode) {
        int rowLen = grid.length;
        int colLen = grid[0].length;
        int minval = 0;

        for (int row=0; row < rowLen; row++) {
            for (int col = 0; col < colLen; col++) {
                int diff = Math.abs(row - r) + Math.abs(col - c);
                if (diff < grid[row][col]) {
                    minval = Math.max(minval, diff);
                    if (shouldSetNode) grid[row][col] = diff;
                } else minval = Math.max(minval, grid[row][col]);
            }
        }
        return minval;
    }

    static int solve(int[][] grid) {
        // O(R*C) time and space
        int rowLen = grid.length;
        int colLen = grid[0].length;

        List<Integer[]> ones = new ArrayList<>();
        List<Integer[]> zeros = new ArrayList<>();

        for (int row=0; row < rowLen; row++) {
            for (int col = 0; col < colLen; col++) {
                if (grid[row][col] == 1) ones.add(new Integer[]{row, col});
                else zeros.add(new Integer[]{row, col});
            }
        }
        if (zeros.size() == 0) return 0;

        int[][] diffs = new int[rowLen][colLen];
        for (Integer[] zeroNode: zeros) {
            int currentBest = Integer.MAX_VALUE;
            for (Integer[] oneNode: ones) {
                int diff = Math.abs(zeroNode[0] - oneNode[0]) + Math.abs(zeroNode[1] - oneNode[1]);
                currentBest = Math.min(currentBest, diff);
            }
            diffs[zeroNode[0]][zeroNode[1]] = currentBest;
        }
//        for (int i = 0; i<rowLen; i++) System.out.println(Arrays.toString(diffs[i]));

        int result = Integer.MAX_VALUE;
        for (Integer[] setOne: zeros) {
            int best = 0;
            for (Integer[] zero: zeros) {
                int diff = Math.abs(zero[0] - setOne[0]) + Math.abs(zero[1] - setOne[1]);
                best = Math.max(best, Math.min(diff, diffs[zero[0]][zero[1]]));
            }
            result = Math.min(result, best);
        }

        return result;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int tests = sc.nextInt();
        for (int t = 1; t < tests+1; t++) {
            int row = sc.nextInt();
            int col = sc.nextInt();
            int[][] grid = new int[row][col];
            for (int r = 0; r < row; r++) {
                String line = sc.next();
                for (int c = 0; c < col; c++) grid[r][c] = line.charAt(c) - '0';
            }
            System.out.printf("Case #%d: %d\n", t, solve(grid));
        }
    }
}


/*
1 0 1
0 0 0
0 0 0 3 => 1
0 0 0
1 0 1
        int[][] grid = new int[][]{{1, 0, 1}, {0, 0, 0}, {0, 0, 0}, {0, 0, 0}, {1, 0, 1}};
        getComputedGrid(grid);

4
3 3
101
000
101
1 2
11
5 5
10001
00000
00000
00000
10001
1 3
101
 */