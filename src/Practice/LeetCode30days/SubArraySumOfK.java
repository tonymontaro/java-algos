package Practice.LeetCode30days;

import java.util.HashMap;

public class SubArraySumOfK {

    static int subarraySum(int[] nums, int k) {
        // O(n) time and space
        int res = 0;
        HashMap<Integer, Integer> seenSum = new HashMap<>();
        seenSum.put(0, 1);
        int cummulative = 0;
        for (int num: nums) {
            cummulative += num;
            if (seenSum.containsKey(cummulative - k)) res += seenSum.get(cummulative - k);
            seenSum.merge(cummulative, 1, Integer::sum);
        }
        return res;
    }

    public static void main(String[] args) {
        System.out.println(subarraySum(new int[]{1, 1, 1}, 2));
    }
}
