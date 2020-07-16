package GoogleKickStart.Year_2020.Year_2020_B;

import java.util.*;

public class RoundB_WanderingRobot {

    static class Solver {
        int cols;
        int rows;
        int topC;
        int topR;
        int bottomC;
        int bottomR;

        public Solver (int cols, int rows, int topC, int topR, int bottomC, int bottomR) {
            this.cols = cols;
            this.rows = rows;
            this.topC = topC;
            this.topR = topR;
            this.bottomC = bottomC;
            this.bottomR = bottomR;
        }

        double solve() {
            // O(R * C) time | O(C) space | R -> rows, C -> cols
            double[] prev = new double[this.cols+1];

            for (int r = this.rows; r > 0; r--) {
                double[] current = new double[this.cols+1];
                for (int c = this.cols; c > 0; c--) {
                    double result;
                    if ((r >= topR && r <= bottomR) && (c >= topC && c <= bottomC)) result = 0;
                    else if (r == this.rows && c == this.cols) result = 1;
                    else if (r == this.rows) result = current[c+1];
                    else if (c == this.cols) result = prev[c];
                    else result = 0.5 * current[c+1] + 0.5 * prev[c];
                    current[c] = result;
                }
                prev = current;
            }

            return prev[1];
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int tests = sc.nextInt();
        for (int t = 0; t < tests; t++) {
            Solver solver = new Solver(sc.nextInt(), sc.nextInt(), sc.nextInt(), sc.nextInt(), sc.nextInt(), sc.nextInt());
            System.out.printf("Case #%d: %f\n", t+1, solver.solve());
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