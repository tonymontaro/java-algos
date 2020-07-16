package GoogleKickStart.Year_2019.Year_2019_A;

import java.util.*;

public class RoundA_Training {
    static int[] generateCumulativeAreas(int[] nums) {
        int maxVal = nums[nums.length-1];
        int[] result = new int[nums.length+1];
        for (int i = 1; i < nums.length+1; i++) {
            result[i] = (maxVal - nums[i-1]) + result[i-1];
        }
        return result;
    }

    static int solve(int[] nums, int p) {
        Arrays.sort(nums);
        int[] cummuativeAreas = generateCumulativeAreas(nums);

        int lastMax = nums[nums.length - 1];
        int best = Integer.MAX_VALUE;
        for (int i = p; i < nums.length+1; i++) {
            int maxVal = nums[i-1];
            int volume = (cummuativeAreas[i] - cummuativeAreas[i-p]) - (lastMax - maxVal) * p;
            best = Math.min(best, volume);
        }
        return best;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int tests = sc.nextInt();
        for (int t = 0; t < tests; t++) {
            int n = sc.nextInt();
            int p = sc.nextInt();
            int[] nums = new int[n];
            for (int i=0; i < n; i++) nums[i] = sc.nextInt();
            System.out.printf("Case #%d: %d\n", t+1, solve(nums, p));
        }
    }
}

//        System.out.println(solve(new int[]{1, 5, 5, 5, 5, 20, 20, 20, 20, 23, 100}, 5));