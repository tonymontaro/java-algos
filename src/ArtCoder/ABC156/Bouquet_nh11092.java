package ArtCoder.ABC156;

import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.NoSuchElementException;

public class Bouquet_nh11092 {
    static FastScanner sc = new FastScanner();
    static PrintWriter out = new PrintWriter(System.out);
    static int n;
    static final int mod = 1_000_000_007;

    public static void main(String[] args) {
        n = sc.nextInt();

        int a = sc.nextInt();
        int b = sc.nextInt();

        long ans = pow(2, n)-1+mod-nCr(n, a)- nCr(n, b)+mod;

        out.println(ans%mod);

        out.flush();
    }

    public static long factorial(int j, int lim) {
        long tmp = 1;
        for (int i=j; i>lim; i--) {
            tmp = (tmp*i)%mod;
        }
        return tmp;
    }

    public static long pow (long a, int j) {
        long res=1;
        while (j>0) {
            if ((j-j/2*2)==1) {
                res=(res*a)%mod;
            }
            a=(a*a)%mod;
            j>>=1;
        }
        return res;
    }

    public static long modinv(long j) {
        return pow(j, mod-2);
    }

    public static long nCr(int j, int r) {
        int lim = Math.max(j-r, r);
        int min = Math.min(j-r, r);

        return factorial(j, lim) * modinv(factorial(min, 0)) % mod ;
    }

    static class FastScanner {
        private final InputStream in = System.in;
        private final byte[] buffer = new byte[1024];
        private int ptr = 0;
        private int buflen = 0;

        private boolean hasNextByte() {
            if (ptr < buflen) {
                return true;
            } else {
                ptr = 0;
                try {
                    buflen = in.read(buffer);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                if (buflen <= 0) {
                    return false;
                }
            }
            return true;
        }

        private int readByte() {
            if (hasNextByte()) return buffer[ptr++];
            else return -1;
        }

        private static boolean isPrintableChar(int c) {
            return 33 <= c && c <= 126;
        }

        private void skipUnprintable() {
            while (hasNextByte() && !isPrintableChar(buffer[ptr])) ptr++;
        }

        public boolean hasNext() {
            skipUnprintable();
            return hasNextByte();
        }

        public String next() {
            if (!hasNext()) throw new NoSuchElementException();
            StringBuilder sb = new StringBuilder();
            int b = readByte();
            while (isPrintableChar(b)) {
                sb.appendCodePoint(b);
                b = readByte();
            }
            return sb.toString();
        }

        public long nextLong() {
            if (!hasNext()) throw new NoSuchElementException();
            long n = 0;
            boolean minus = false;
            int b = readByte();
            if (b == '-') {
                minus = true;
                b = readByte();
            }
            if (b < '0' || '9' < b) {
                throw new NumberFormatException();
            }
            while (true) {
                if ('0' <= b && b <= '9') {
                    n *= 10;
                    n += b - '0';
                } else if (b == -1 || !isPrintableChar(b)) {
                    return minus ? -n : n;
                } else {
                    throw new NumberFormatException();
                }
                b = readByte();
            }
        }

        public int nextInt() {
            return (int) nextLong();
        }

        public double nextDouble(){
            return Double.parseDouble(next());
        }

        public int[] nextIntArray(int N, boolean oneBased) {
            if (oneBased) {
                int[] array = new int[N + 1];
                for (int i = 1; i <= N; i++) {
                    array[i] = sc.nextInt();
                }
                return array;
            } else {
                int[] array = new int[N];
                for (int i = 0; i < N; i++) {
                    array[i] = sc.nextInt();
                }
                return array;
            }
        }

        public long[] nextLongArray(int N, boolean oneBased) {
            if (oneBased) {
                long[] array = new long[N + 1];
                for (int i = 1; i <= N; i++) {
                    array[i] = sc.nextLong();
                }
                return array;
            } else {
                long[] array = new long[N];
                for (int i = 0; i < N; i++) {
                    array[i] = sc.nextLong();
                }
                return array;
            }
        }
    }

}



