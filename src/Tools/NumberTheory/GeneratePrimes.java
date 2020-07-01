package Tools.NumberTheory;

import java.util.*;

public class GeneratePrimes {
    public static void main(String[] args) {
        System.out.println(generatePrimes(101));
    }

    static ArrayList<Integer> generatePrimes(int n) {
        int[] lp = new int[n + 1];
        ArrayList<Integer> pr = new ArrayList<>();

        for (int i = 2; i <= n; ++i) {
            if (lp[i] == 0) {
                lp[i] = i;
                pr.add(i);
            }
            for (int j = 0; j < pr.size() && pr.get(j) <= lp[i] && i * pr.get(j) <= n; ++j) {
                lp[i * pr.get(j)] = pr.get(j);
            }
        }
        return pr;
    }
}


