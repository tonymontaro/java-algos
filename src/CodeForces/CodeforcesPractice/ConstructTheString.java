package CodeForces.CodeforcesPractice;

import java.util.Scanner;

public class ConstructTheString {
    static String st = "abcdefghijklmnopqrstuvwxyz";

    static String solve(int ln, int distinct) {
        String dictinct_letters = st.substring(0, distinct);
        int rem = ln % distinct;
        int num = (ln - rem)/distinct;
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < num; i++) result.append(dictinct_letters);
        result.append(dictinct_letters.substring(0, rem));
        return result.toString();
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        for (int i = 0; i < n; i++) {
            int ln = sc.nextInt();
            int substring_len = sc.nextInt();
            int distinct = sc.nextInt();
            System.out.println(solve(ln, distinct));
        };
    }
}
