package GoogleKickStart.Year_2020.Year_2020_B;

import java.util.Scanner;

public class RoundB_BusRoutes {
    static long solve(long[] days, long lastDay) {
        for (int i = days.length-1; i >= 0; i--) {
            lastDay = lastDay - (lastDay % days[i]);
        }
        return lastDay;
    }

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        int tests = sc.nextInt();
        for (int t = 0; t < tests; t++) {
            int n = sc.nextInt();
            long lastDay = sc.nextLong();
            long[] nums = new long[n];
            for (int i = 0; i < n; i++) nums[i] = sc.nextLong();
            long result = solve(nums, lastDay);
            System.out.printf("Case #%d: %d\n", t+1, result);
        }
    }
}

// System.out.println(solve(new int[]{11, 10, 5, 50}, 100));
//        System.out.println(solve(new long[]{11, 10000000000000L, 5, 50}, 100));