package ArtCoder.ABC163;

import java.util.Scanner;

public class HomeWork {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int homeworks = sc.nextInt();
        int days = 0;
        for (int i = 0; i < homeworks; i++) days += sc.nextInt();
        System.out.println(Math.max(-1, n - days));
    }
}

/*
314 15
9 26 5 35 8 9 79 3 23 8 46 2 6 43 3
 */