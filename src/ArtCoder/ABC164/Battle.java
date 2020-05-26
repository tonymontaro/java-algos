package ArtCoder.ABC164;

import java.util.Scanner;

public class Battle {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int aHealth = sc.nextInt();
        int aAttack = sc.nextInt();
        int bHealth = sc.nextInt();
        int bAttack = sc.nextInt();
        int aWin = (int) Math.ceil((double) bHealth/aAttack);
        int bWin = (int) Math.ceil((double) aHealth/bAttack);
        System.out.println(aWin <= bWin ? "Yes" : "No");
    }
}
