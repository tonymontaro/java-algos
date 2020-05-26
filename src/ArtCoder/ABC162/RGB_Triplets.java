package ArtCoder.ABC162;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class RGB_Triplets {
    static Map<Character, Integer> mapping = new HashMap<>();

    static long[][] generatePrefix(String word) {
        long[][] res = new long[word.length() + 1][3];

        for (int i = word.length()-1; i >= 0; i--) {
            res[i][0] = res[i + 1][0];
            res[i][1] = res[i + 1][1];
            res[i][2] = res[i + 1][2];
            res[i][mapping.get(word.charAt(i))]++;
        }
        return res;
    }

    static long search(char a, char b, long[][] prefix, int idx, String word) {
        long res = 0;
        long ln = word.length();
        for (int i = idx; i < ln; i++) {
            char ch = word.charAt(i);
            int diff = i + (i - (idx - 1));
            if (ch == a) {
                res += prefix[i][mapping.get(b)];
                if (diff < ln && word.charAt(diff) == b) res--;
            }
            if (ch == b) {
                res += prefix[i][mapping.get(a)];
                if (diff < ln && word.charAt(diff) == a) res--;
            }
        }
        return res;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        String word = sc.next();
        mapping.put('R', 0);
        mapping.put('G', 1);
        mapping.put('B', 2);
        long[][] prefix = generatePrefix(word);
        long res = 0;
        for (int i = 0; i < n; i++) {
            char a = word.charAt(i);
            if (a == 'R') res += search('G', 'B', prefix, i + 1, word);
            else if (a == 'G') res += search('R', 'B', prefix, i + 1, word);
            else res += search('R', 'G', prefix, i + 1, word);
        }
        System.out.print(res);
    }
}

// better idea
//static class DRGBTriplets {
//    public void solve(int testNumber, LightScanner in, LightWriter out) {
//        int n = in.ints();
//        char[] s = in.chars();
//        long r = 0, g = 0, b = 0;
//        for (int i = 0; i < n; i++) {
//            if (s[i] == 'R') r++;
//            else if (s[i] == 'G') g++;
//            else b++;
//        }
//        long ans = r * g * b;
//        for (int i = 0; i < n; i++) {
//            for (int j = i + 1; 2 * j - i < n; j++) {
//                if (s[i] == s[j]) continue;
//                if (s[i] == s[2 * j - i]) continue;
//                if (s[j] == s[2 * j - i]) continue;
//                ans--;
//            }
//        }
//        out.ans(ans).ln();
//    }