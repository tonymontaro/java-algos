package ArtCoder.ABC163;

import java.util.Scanner;

public class Management {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int[] subordinates = new int[n];

        for (int i = 1; i < n; i++) subordinates[sc.nextInt() - 1]++;
        for (int num: subordinates) System.out.println(num);
        sc.close();
    }
}

/*
5
1 1 2 2
 */