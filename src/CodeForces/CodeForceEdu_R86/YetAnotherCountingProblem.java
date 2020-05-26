package CodeForces.CodeForceEdu_R86;

import java.math.BigInteger;
import java.util.Scanner;

public class YetAnotherCountingProblem {
    static void hcf(int n1, int n2) {
        int gcd = 1;

        for(int i = 1; i <= n1 && i <= n2; ++i) {
            // Checks if i is factor of both integers
            if(n1 % i==0 && n2 % i==0)
                gcd = i;
        }

        System.out.printf("G.C.D of %d and %d is %d", n1, n2, gcd);
    }

    static int lcm(int number1, int number2) {
        if (number1 == 0 || number2 == 0) {
            return 0;
        }
        int absNumber1 = Math.abs(number1);
        int absNumber2 = Math.abs(number2);
        int absHigherNumber = Math.max(absNumber1, absNumber2);
        int absLowerNumber = Math.min(absNumber1, absNumber2);
        int lcm = absHigherNumber;
        while (lcm % absLowerNumber != 0) {
            lcm += absHigherNumber;
        }
        return lcm;
    }

    static int[] countDiff(int a, int b, int lcm_num) {
        int num = 0;
        int[] nums = new int[lcm_num];
        for (int i = 0; i < lcm_num; i++) {
            if ((i % a) % b != (i % b) % a) {
                num++;
                nums[i] = num;
            };
        }
        return nums;
    }

    static BigInteger toBig(int a) {
        return new BigInteger(String.valueOf(a));
    }

    static  int toInt(BigInteger a) {
        return Integer.parseInt(a.toString());
    }

    static BigInteger solver(int a, int b,  BigInteger end) {
        int lcm_num = lcm(a, b);
        int[] diffs = countDiff(a, b, lcm_num);
        BigInteger bigLcm = toBig(lcm_num);
        if (end.compareTo(bigLcm) < 0) {
            int newEnd = toInt(end);
            return toBig(diffs[newEnd]);
        };
        BigInteger totalDiff = toBig(diffs[lcm_num-1]);
        BigInteger startEnder = end.mod(bigLcm);
        int starter = diffs[toInt(startEnder)];
        BigInteger multiple = end.subtract(startEnder);
        BigInteger ender = multiple.divide(bigLcm);
        return ender.multiply(totalDiff).add(toBig(starter));
    }
    static BigInteger solve(int a, int b,  BigInteger start, BigInteger end) {
        BigInteger first = solver(a, b, start.subtract(BigInteger.ONE));
        BigInteger second = solver(a, b, end);
        return second.subtract(first);
    }

    public static void main(String[] args) {
        // new BigInteger("3")
//        BigInteger rs = solve(4, 6, new BigInteger("7"), new BigInteger("26"));
//        System.out.println("=====");
//        System.out.println(rs);

        Scanner sc = new Scanner(System.in);
        int tests = sc.nextInt();
        for (int t=0; t < tests; t++) {
            int a = sc.nextInt();
            int b = sc.nextInt();
            int q = sc.nextInt();
            sc.nextLine();
            for (int i = 0; i < q; i++) {
                String[] strs = sc.nextLine().split(" ");
                BigInteger rs = solve(a, b, new BigInteger(strs[0]), new BigInteger(strs[1]));
                if (i == q-1)   System.out.printf("%d\n", rs);
                else  System.out.printf("%d ", rs);
            }
        }
    }
}


/*
     int start = 1;
    int end = 7;
    int a = 4;
    int b = 6;
    int lcm_num = lcm(a, b);
    int diff = countDiff(a, b, 0, lcm_num);
    int first = countDiff(a, b, start % lcm_num, lcm_num);
    int lastNum = end - start % lcm_num;
    int mid = (lastNum / lcm_num) * diff;
    int last = countDiff(a, b, 0, (lastNum) % lcm_num);
    System.out.println(first + mid + last);
 */