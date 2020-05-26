package ArtCoder.ABC162;

import java.util.Scanner;

public class FizzBuzzSum {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        long n = sc.nextLong();
        long total = 0;
        for (long i = 1; i < n + 1; i++) if (i % 5 != 0 && i % 3 != 0) total += i;
        System.out.println(total);
    }
}

// 1000000 => 266666333332