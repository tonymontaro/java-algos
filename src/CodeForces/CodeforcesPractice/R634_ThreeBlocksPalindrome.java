package CodeForces.CodeforcesPractice;

import java.util.*;

public class R634_ThreeBlocksPalindrome {

    public static int getThreeBlockPalindrome(int b, char item, List<Character> str) {
        int best = 0;
        int pairs = 0;

        int left = 0;
        int right = str.size() - 1;
        while (left < right) {
            char l = str.get(left);
            char r = str.get(right);
            if (l == item && r == item) {
                pairs += 2;
                best = Math.max(best, pairs + b);
                left++;
                right--;
            }
            if (l != item) {
                b--;
                left++;
            }
            if (r != item) {
                b--;
                right--;
            }
        }

        return best;
    }

    static int solve(int[] nums) {
        int maxCount = 0;
        List<Integer> unique = new ArrayList<>();
        HashMap<Integer, Integer> counts = new HashMap<>();
        for (int num: nums) {
            if (!counts.containsKey(num)) unique.add(num);
            counts.merge(num, 1, Integer::sum);
            maxCount = Math.max(maxCount, counts.get(num));
        }

        for (int i = 0; i < unique.size(); i++) {
            for (int j = i+1; j < unique.size(); j++) {
                int num1 = unique.get(i);
                int num2 = unique.get(j);
                List<Character> str = new ArrayList<>();
                for (int num: nums) {
                    if (num == num1) str.add('1');
                    else if (num == num2) str.add('0');
                }
                if (str.size() > maxCount) {
                    int currentMax = Math.max(
                            getThreeBlockPalindrome(counts.get(num2), '1', str),
                            getThreeBlockPalindrome(counts.get(num1), '0', str)
                    );
                    maxCount = Math.max(maxCount, currentMax);
                }
            }
        }

        return maxCount;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int tests = sc.nextInt();
        for (int t = 0; t < tests; t++) {
            int n = sc.nextInt();
            int[] nums = new int[n];
            for (int i=0; i < n; i++) nums[i] = sc.nextInt();
            System.out.println(solve(nums));
        }
    }
}

/*
System.out.println(solve(new int[]{1, 1, 2, 2, 3, 2, 1, 1}));
System.out.println(solve(new int[]{1, 2, 1, 3, 2, 1})); // 4
 */