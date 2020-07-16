package GoogleKickStart.Year_2019.Year_2019_Practice;

import java.util.*;

public class PracticeRound_Mural {

    static int mural(int[] nums) {
        int regionLen = nums.length - nums.length/2;
        int best = 0;
        int total = 0;
        for (int i = 0; i < nums.length; i++) {
            total += nums[i];
            if (i >= regionLen) total -= nums[i - regionLen];
            best = Math.max(best, total);
        }
        return best;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int tests = sc.nextInt();
        for (int t = 0; t < tests; t++) {
            int n = sc.nextInt();
            String string = sc.next();
            int[] nums = new int[n];
            for (int i = 0; i < n; i++) nums[i] = string.charAt(i) - '0';
            System.out.printf("Case #%d: %d\n", t+1, mural(nums));
        }
    }
}
/*
4
4
1332
4
9583
3
616
10
1029384756
====>
Case #1: 6
Case #2: 14
Case #3: 7
Case #4: 31
//        System.out.println(mural(new int[]{1,0,2,9,3,8,4,7,5,6}));
 */
