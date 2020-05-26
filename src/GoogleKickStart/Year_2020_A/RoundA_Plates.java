package GoogleKickStart.Year_2020_A;

import java.util.*;

public class RoundA_Plates {
    static void solve(int rows, int cols, int take, int[][] grid, int caseNum) {
        int[] prev = new int[take+1];

        for (int r = 0; r < rows; r++) {
            int[] curr = new int[take+1];
            int[] gridRow = grid[r];

            for (int c = 1; c < cols+1; c++) {
                for (int i = 0; i < take+1; i++) {
                    if (i >= c) {
                        int temp = Math.max(prev[i], gridRow[c-1] + prev[i - c]);
                        curr[i] = Math.max(curr[i], temp);
                    }
                }
            }
            prev = curr;
        }
        System.out.printf("Case #%d: %d\n", caseNum, prev[take]);
    }


    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int tests = sc.nextInt();
        for (int i = 0; i < tests; i++) {
            int rows = sc.nextInt();
            int cols = sc.nextInt();
            int take = sc.nextInt();
            int[][] grid = new int[rows][cols];
            for (int r = 0; r < rows; r++) {
                int total = 0;
                for (int c = 0; c < cols; c++) {
                    total += sc.nextInt();
                    grid[r][c] = total;
                }
            }
            solve(rows, cols, take, grid, i+1);
        }
    }
}
//        solve(2, 4, 5, new int[][]{{10, 20, 120, 150}, {80, 130, 140, 190}}, 1);
//        solve(3, 2, 3, new int[][]{{80, 80}, {15, 50}, {20, 10}}, 2);
//        2
//        2 4 5
//        10 10 100 30
//        80 50 10 50

//        3 2 3
//        80 80
//        15 50
//        20 10
//
//
//        Case #1: 250
//        Case #2: 180


//public class RoundA_Plates {
//
//    static class Avg {
//        double average;
//        int total;
//        int row;
//        int items;
//        boolean updated = false;
//
//        public Avg (int total, int row, int items) {
//            this.average = (double) total/items;
//            this.row = row;
//            this.items = items;
//            this.total = total;
//        }
//        public Avg() {
//            this.average = Double.MIN_EXPONENT;
//        }
//
//        public double getAverage() {
//            return this.average;
//        }
//
//        public void update(int total, int items) {
//            this.total -= total;
//            this.items -= items;
//            this.average = (double) this.total/this.items;
//            this.updated = true;
//        }
//
//        @Override
//        public String toString() {
//            return String.format("%d - %d -> %f\n", this.row, this.items, this.average);
//        }
//    }
//
//    static void solve(int rows, int cols, int take, int[][] grid, int caseNum) {
//        // O(nlogn) time | O(n) space
//        List<List<Avg>> averages = new ArrayList<>();
//        for (int row = 0; row < rows; row++) {
//            int[] nums = grid[row];
//            int n = 0;
//            int total = 0;
//            List<Avg> avgs = new ArrayList<>();
//
//            for (int num: nums) {
//                n += 1;
//                total += num;
//                Avg avg = new Avg(total, row, n);
//                avgs.add(avg);
//            };
//            avgs.sort(Comparator.comparing(Avg::getAverage).reversed());
//            averages.add(avgs);
//        }
//
//
//        int result = 0;
//        int[] loc = new int[rows];
//        int[] added = new int[rows];
//        int[] totals = new int[rows];
//        while (take > 0) {
//            Avg best = new Avg();
//            for (int i = 0; i < rows; i++) {
//                int idx = loc[i];
//                while (idx < cols) {
//                    Avg nextItem = averages.get(i).get(idx);
//                    if (!nextItem.updated && nextItem.items < added[i]) {
//                        idx++;
//                        continue;
//                    }
//                    if (!nextItem.updated) nextItem.update(totals[i], added[i]);
//                    if (nextItem.items > take) {
//                        idx++;
//                    } else {
//                        if (nextItem.average > best.average) best = nextItem;
//                        break;
//                    };
//                }
//                loc[i] = idx;
//            }
//            int r = best.row;
//            take -= best.items;
//            result += best.total;
//            added[r] += best.items;
//            totals[r] += best.total;
//            loc[r]++;
//        }
//        System.out.printf("Case #%d: %d\n", caseNum, result);
//    }
//
//
//}


