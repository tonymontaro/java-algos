package Tools;

public class BinaryExponentiation {
    long binpow(long a, long b) {
        long res = 1;
        while (b > 0) {
            if (b % 2 == 1) res = res * a;
            a = a * a;
            b /= 2; // or: b >>= 1;
        }
        return res;
    }

    final long MOD = 1000000007;
    long binpow_withMod(long a, long b, long MOD) {
        long res = 1;
        while (b > 0) {
            if (b % 2 == 1) res = (res * a) % MOD;
            a = (a * a) % MOD;
            b /= 2;
        }
        return res;
    }

    static long binoPow(long base, long pow) {
        long res = 1;
        while (pow > 0) {
            if (pow % 2 == 1) {
                pow--;
                res *= base;
            }
            pow = pow/2;
            base = base * base;
        }
        return res;
    }

    public static void main(String[] args) {
        System.out.println(binoPow(20, 5));
    }
}
