package Practice.LeetCode30days;

import java.util.*;

public class CountingElements {
    static int solve(int[] nums) {
        // O(n) time and space
        HashSet<Integer> seen = new HashSet<>();
        for (int num: nums) seen.add(num);

        int result = 0;
        for (int num: nums) {
            if (seen.contains(num + 1)) result++;
        }
        return result;
    }

    public static void main(String[] args) {
        System.out.println(solve(new int[]{1,3,2,3,5,0}));
        System.out.println(solve(new int[]{1,1,2,2}));
    }
}
