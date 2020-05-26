package CodeForces.CodeforcesPractice;

import java.util.*;

public class CandiesAndTwoSisters {
    static int solve(int candies) {
        return (int) Math.ceil((double) candies/2 - 1);
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        for (int i = 0; i < n; i++) System.out.println(solve(sc.nextInt()));
    }
}
