package Tools.NumberTheory;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.StringTokenizer;

public class nCr_nPr_Combination_Permutation {


    static long nPr(int n, int r) {
        long res = 1;
        for (int i = (n - r + 1); i <= n; i++) {
            res = (res * i);
        }
        return res;
    }

    static long nCr(int n, int r) {
        return nPr(n, r) / nPr(r, r);
    }

    public static void main(String[] args) {
//        System.out.println(nPr(10, 4));
//        System.out.println(nCr(10, 5));
        System.out.println(nCr_withMod(10, 5, MOD));
    }



    static final long MOD = 1000000007;

    static long binpow_withMod(long a, long b, long MOD) {
        long res = 1;
        while (b > 0) {
            if (b % 2 == 1) res = (res * a) % MOD;
            a = (a * a) % MOD;
            b /= 2;
        }
        return res;
    }

    static long nPr_withMod(int n, int r, long MOD) {
        long res = 1;
        for (int i = (n - r + 1); i <= n; i++) {
            res = (res * i) % MOD;
        }
        return res;
    }

    static long nCr_withMod(int n, int r, long MOD) {
        long r_factorial = nPr_withMod(r, r, MOD);
        long first = nPr_withMod(n, r, MOD);
        long second = binpow_withMod(r_factorial, MOD-2, MOD);
        return  (first * second) % MOD;
    }
}
