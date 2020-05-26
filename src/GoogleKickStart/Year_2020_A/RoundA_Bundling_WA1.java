package GoogleKickStart.Year_2020_A;

import java.util.*;


public class RoundA_Bundling_WA1 {

    static int getPrefix(String a, String b) {
        int i = 0;
        while (i < a.length() && i < b.length()) {
            if (a.charAt(i) != b.charAt(i)) return i;
            i++;
        }
        return i;
    }

    static int solve(String[] arr, int group) {
        // O(n * w + nlogn) time
        Arrays.sort(arr);
        int ln = arr.length;
        int[] prefixes = new int[ln];
        for (int i = 1; i < ln; i++) prefixes[ln - i -1] = getPrefix(arr[i], arr[i-1]);

        int minPrefix = 0;
        int count = 0;
        int result = 0;
        for (int i = 0; i < ln; i++) {
            if (count == group - 1) {
                count = 0;
                result += minPrefix;
            }
            else if (prefixes[i] == 0) count = 0;
            else {
                count += 1;
                minPrefix = prefixes[i];
            }
        }
        return result;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int tests = sc.nextInt();
        for (int caseNum = 1; caseNum < tests+1; caseNum++) {
            int n = sc.nextInt();
            int group = sc.nextInt();
            String[] arr = new String[n];
            for (int i = 0; i < n; i++) arr[i] = sc.next();
            int result = solve(arr, group);
            System.out.printf("Case #%d: %d\n", caseNum, result);
        }
    }
}
/*

//        String[] arr = new String[]{"RAINBOW", "FIREBALL", "RANK", "RANDOM", "FIREWALL", "FIREFIGHTER", "AT", "ZT", "GAD"};

//        Arrays.sort(arr);
//        System.out.println(Arrays.toString(arr));

//        System.out.println(solve(arr, 3));

from flask import Flask, flash, render_template,
from flask import session, redirect, request, url_for

1
3 3
AAAA
AAAA
AAAA
 */