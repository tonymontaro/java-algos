package GoogleKickStart.Year_2020_A;

import java.util.*;
import Tools.CountingSort;

// Solution
public class RoundA_Allocation {

//    static int[] countingSort(int[] nums) {
//        HashMap<Integer, Integer> counts = new HashMap<>();
//        int maxNum = Integer.MIN_VALUE;
//        for (int num: nums) {
//            maxNum = Math.max(maxNum, num);
//            counts.merge(num, 1, Integer::sum);
//        };
//        int prev = 0;
//        for (int i = 0; i <= maxNum; i++) {
//            if (counts.containsKey(i)) {
//                int tmp = prev;
//                prev += counts.get(i);
//                counts.put(i, tmp);
//            }
//        }
//        int[] result = new int[nums.length];
//        for (int num: nums) {
//            result[counts.get(num)] = num;
//            counts.merge(num, 1, Integer::sum);
//        }
//        return result;
//    }
//

    static int allocation(int n, int budget, int[] nums) {
        // O(n) time and space
        CountingSort.countingSort(nums);
        int result = 0;
        for (int i = 0; i < n; i++) {
            if (nums[i] > budget) break;
            budget -= nums[i];
            result++;
        }
        return result;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int testCases = sc.nextInt();
        for (int i = 0; i < testCases; i++) {
            int n = sc.nextInt();
            int budget = sc.nextInt();
            int[] nums = new int[n];
            for (int j=0; j < n; j++) nums[j] = sc.nextInt();
            System.out.printf("Case #%d: %d\n", i+1, allocation(n, budget, nums));
        }
    }
}



//        3
//        4 100
//        20 90 40 90  | Case #1: 2
//        4 50
//        30 30 10 10  | Case #2: 3
//        3 300
//        999 999 999  | Case #3: 0