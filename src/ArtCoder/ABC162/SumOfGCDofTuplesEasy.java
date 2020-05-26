package ArtCoder.ABC162;

import java.util.*;

public class SumOfGCDofTuplesEasy {

    static int gcd (int a, int b) {
        return b == 0 ? a : gcd (b, a % b);
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        long total = 0;
        int k = sc.nextInt();

        for (int x = 1; x < k + 1; x++) {
            total += x;
            for (int y = x+1; y < k + 1; y++) {
                int first = gcd(x, y);
                total += 2 * 3 * first;
                for (int z = y+1; z < k + 1; z++) {
                    total += 6 * gcd(first, z);
                }
            }
        }
        System.out.println(total);
    }
}

// 200 => 10813692