package CodeForces.CodeForceEdu_R86;

import java.util.Scanner;

public class RoadToZero {

    static long solve(long x, long y, long cost_a, long cost_b) {
        long diff = Math.abs(x - y);
        long first = diff * cost_a + Math.min(x, y) * cost_b;
        long second = (x + y) * cost_a;
        return Math.min(first, second);
    }


    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        int tests = sc.nextInt();
        for (int t=0; t < tests; t++) {
            System.out.println(solve(sc.nextLong(), sc.nextLong(), sc.nextLong(), sc.nextLong()));
        }
    }
}
// System.out.println(solve(1, 3, 391, 555));