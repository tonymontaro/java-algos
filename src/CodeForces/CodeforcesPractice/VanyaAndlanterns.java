package CodeForces.CodeforcesPractice;
// https://codeforces.com/problemset/problem/492/B

import java.util.*;

public class VanyaAndlanterns {
    static double solve(int length, int[] nums) {
        Arrays.sort(nums);
        double best = Math.max(nums[0], length - nums[nums.length - 1]);

        for (int i = 1; i < nums.length; i++) best = Math.max(best, (double) (nums[i] - nums[i-1])/2);

        return best;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int length = sc.nextInt();
        int[] nums = new int[n];
        for (int i = 0; i < n; i++) nums[i] = sc.nextInt();
        System.out.println(solve(length, nums));
    }
}
/*
7 15
15 5 3 7 9 14 0
==> 2.5

2 5
2 5
==> 2

1 20
0
==> 20
 */