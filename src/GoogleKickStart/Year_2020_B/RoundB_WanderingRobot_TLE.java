package GoogleKickStart.Year_2020_B;

import java.util.*;

public class RoundB_WanderingRobot_TLE {

    static class Solver {
        int cols;
        int rows;
        int topC;
        int topR;
        int bottomC;
        int bottomR;
        HashMap<String, Double> cache = new HashMap<>();

        public Solver (int cols, int rows, int topC, int topR, int bottomC, int bottomR) {
            this.cols = cols;
            this.rows = rows;
            this.topC = topC;
            this.topR = topR;
            this.bottomC = bottomC;
            this.bottomR = bottomR;
        }

        double solve(int col, int row) {
            String key = col + "," + row;
            if (this.cache.containsKey(key)) return this.cache.get(key);
            double result;
            if (row == this.rows && col == this.cols) result = 1;
            else if ((row >= topR && row <= bottomR) && (col >= topC && col <= bottomC)) result = 0;
            else if (row == this.rows) result = solve(col+1, row);
            else if (col == this.cols) result = solve(col, row+1);
            else result = 0.5 * solve(col+1, row) + 0.5 * solve(col, row+1);
            this.cache.put(key, result);
            return result;
        }
    }



    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int tests = sc.nextInt();
        for (int t = 0; t < tests; t++) {
            Solver solver = new Solver(sc.nextInt(), sc.nextInt(), sc.nextInt(), sc.nextInt(), sc.nextInt(), sc.nextInt());
            System.out.printf("Case #%d: %f\n", t+1, solver.solve(1, 1));
        }
    }
}


/*
4
3 3 2 2 2 2
5 3 1 2 4 2
1 10 1 3 1 5
6 4 1 3 3 4

        Solver sv = new Solver(3, 3, 2, 2, 2, 2);
        System.out.println(sv.solve(1, 1));

        Solver sv2 = new Solver(5, 3, 1, 2, 4, 2);
        System.out.println(sv2.solve(1, 1));
 */