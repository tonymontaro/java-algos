package ArtCoder.ABC162;

import java.util.Scanner;

public class Lucky7 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String word = sc.next();
        String contains7 = "No";
        for (int i = 0; i < word.length(); i++) if (word.charAt(i) == '7') contains7 = "Yes";
        System.out.println(contains7);
    }
}
