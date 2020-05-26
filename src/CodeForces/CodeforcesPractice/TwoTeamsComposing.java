package CodeForces.CodeforcesPractice;

import java.util.*;

public class TwoTeamsComposing {

    static int solve(int[] nums) {
        int unique = 0;
        int maxSame = 0;
        HashMap<Integer, Integer> seen = new HashMap<>();
        for (int num: nums) {
            if (!seen.containsKey(num)) unique += 1;
            seen.merge(num, 1, Integer::sum);
            maxSame = Math.max(maxSame, seen.get(num));
        }
        if (unique == maxSame) return unique - 1;
        return Math.min(unique, maxSame);
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int tests = sc.nextInt();
        for (int i = 0; i < tests; i++) {
            int n = sc.nextInt();
            int[] nums = new int[n];
            for (int j = 0; j < n; j++) nums[j] = sc.nextInt();
            System.out.println(solve(nums));
        }
    }
}

/*
4
7
4 2 4 1 4 3 4
5
2 1 5 4 3
1
1
4
1 1 1 3
===> 3, 1, 0, 2
 */