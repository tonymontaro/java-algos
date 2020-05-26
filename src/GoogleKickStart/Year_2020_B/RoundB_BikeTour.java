package GoogleKickStart.Year_2020_B;

import java.util.*;

public class RoundB_BikeTour {
    static int solve(int[] nums) {
        int peaks = 0;
        for (int i = 1; i < nums.length-1; i++) {
            if (nums[i] > nums[i-1] && nums[i] > nums[i+1]) peaks++;
        }
        return peaks;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int tests = sc.nextInt();
        for (int t = 0; t < tests; t++) {
            int n = sc.nextInt();
            int[] nums = new int[n];
            for (int i = 0; i < n; i++) nums[i] = sc.nextInt();
            int peaks = solve(nums);
            System.out.printf("Case #%d: %d\n", t+1, peaks);
        }
    }
}
