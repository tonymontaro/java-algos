package GoogleKickStart.Year_2020.Year_2020_A;

import java.util.*;

public class RoundA_Workout {

    static class Diff implements Comparable<Diff> {
        double val;
        int num = 1;
        int original;

        public Diff(int original) {
            this.original = original;
            this.val = original;
        }

        void add() {
            this.num++;
            this.val = Math.ceil((double) this.original/this.num);
        }

        @Override
        public int compareTo(Diff o) {
            return Double.compare(o.val, this.val);
        }
    }

    static void solve(int caseNum, int[] nums, int k) {

        PriorityQueue<Diff> queue = new PriorityQueue<>();
        for (int i = 1; i < nums.length; i++) {
            queue.add(new Diff(nums[i] - nums[i-1]));
        }

        while (k > 0 && queue.peek().val > 1) {
            Diff first = queue.poll();
            while (k > 0 && first.val > 1.0 && (queue.size() == 0 || first.val >= queue.peek().val)) {
                first.add();
                k--;
            }
            queue.add(first);
        }

        System.out.printf("Case #%d: %d\n", caseNum, (int) queue.peek().val);
    }

    public static void main(String[] args) {
        solve(3, new int[]{4, 10}, 1);
//        Scanner sc = new Scanner(System.in);
//        int tests = sc.nextInt();
//        for (int t=0; t < tests; t++) {
//            int n = sc.nextInt();
//            int k = sc.nextInt();
//            int[] nums = new int[n];
//            for (int i=0; i < n; i++) nums[i] = sc.nextInt();
//            solve(t+1, nums, k);
//        }
    }
}
