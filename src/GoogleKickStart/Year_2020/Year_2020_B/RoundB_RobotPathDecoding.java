package GoogleKickStart.Year_2020.Year_2020_B;

import java.math.BigInteger;
import java.util.*;

public class RoundB_RobotPathDecoding {
    static char[] directions = new char[]{'N', 'S', 'E', 'W'};
    static BigInteger gridMax = new BigInteger("1000000000");

    static class Res {
        HashMap<Character, BigInteger> result;
        int idx;
        public Res (HashMap<Character, BigInteger> result, int idx) {
            this.result = result;
            this.idx = idx;
        }
    }

    static Res decode(String s, int idx) {
        HashMap<Character, BigInteger> result = new HashMap<>();
        int i = idx;
        while (i < s.length()) {
            if (Character.isDigit(s.charAt(i))) {
                BigInteger multiplier = new BigInteger(String.valueOf(s.charAt(i)));
                Res tempResult = decode(s, i + 2);
                i = tempResult.idx;
                for (char d: directions) {
                    if (tempResult.result.containsKey(d)) {
                        BigInteger rs = tempResult.result.get(d).multiply(multiplier);
                        if (result.containsKey(d)) rs = result.get(d).add(rs);
                        result.put(d, rs.mod(gridMax));
                    };
                }
            } else if (s.charAt(i) == ')') {
                return new Res(result, i);
            }
            else {
                result.merge(s.charAt(i), new BigInteger("1"), BigInteger::add);
            }
            i++;
        }
        return new Res(result, i);
    }

    static BigInteger[] solve(String s) {
        // O(n) time | O(1) space
        Res decoded = decode(s, 0);

        BigInteger[] result = new BigInteger[]{new BigInteger("1"), new BigInteger("1")};
        for (char k: decoded.result.keySet()) {
            if (k == 'N') result[1] = result[1].subtract(decoded.result.get(k));
            else if (k == 'S') result[1] = result[1].add(decoded.result.get(k));
            else if (k == 'E') result[0] = result[0].add(decoded.result.get(k));
            else if (k == 'W') result[0] = result[0].subtract(decoded.result.get(k));
        }
        for (int i = 0; i < 2; i++) {
            if (result[i].compareTo(BigInteger.ZERO) <= 0) result[i] =  result[i].add(gridMax);
        }
        return result;
    }


    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int tests = sc.nextInt();
        for (int t = 0; t < tests; t++) {
            String word = sc.next();
            BigInteger[] result = solve(word);
            System.out.println("Case #" + String.valueOf(t+1) + ": "+ result[0].toString() + " " + result[1].toString());
        }
    }
}
// 2(3(NW)2(W2(EE)W))
//System.out.println(Arrays.toString(solve("SSSEEE")));
//System.out.println(Arrays.toString(solve("N")));
//System.out.println(Arrays.toString(solve("N3(S)N2(E)N")));
//System.out.println(Arrays.toString(solve("2(3(NW)2(W2(EE)W))")));