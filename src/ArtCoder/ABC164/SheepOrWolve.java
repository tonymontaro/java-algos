package ArtCoder.ABC164;

import java.util.Scanner;

public class SheepOrWolve {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int sheeps = sc.nextInt();
        int wolves = sc.nextInt();
        System.out.println(wolves >= sheeps ? "unsafe" : "safe");
    }
}
