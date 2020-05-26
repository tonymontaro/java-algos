package ArtCoder.ABC155;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.InputMismatchException;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.function.LongPredicate;
import java.util.stream.LongStream;

public class Pairs_kengo {
    static void solve(MyScanner in, MyWriter out) {
        int n = in.nextInt();
        long k = in.nextLong();
        long[] a = in.nextLongArray(n);
        long[] plus = LongStream.of(a).filter(e -> e > 0).sorted().toArray();
        long[] minus = LongStream.of(a).filter(e -> e < 0).sorted().toArray();
        long zeros = LongStream.of(a).filter(e -> e == 0).count();
        long zeroCount = ((long)plus.length + minus.length) * zeros
                + (zeros == 0 ? 0 : (zeros * (zeros - 1)) / 2);
        long minusCount = (long)plus.length * minus.length;
        if (k <= minusCount) {
            long ans = lowerBound((long)-1e18, 0L, x -> {
                long count = 0;
                for (int i = 0, j = 0; i < minus.length; i++) {
                    while (j < plus.length && minus[i] * plus[j] > x) {
                        j++;
                    }
                    count += plus.length - j;
                }
                return count >= k;
            });
            out.println(ans);
        } else if (minusCount < k && k <= minusCount + zeroCount) {
            out.println(0);
        } else {
            reverse(plus);
            long[] absMinus = LongStream.of(minus).map(Math::abs).toArray();
            long ans = lowerBound(1L, (long)1e18 + 1, x -> {
                long count = 0;
                count += countLessThan(plus, x);
                count += countLessThan(absMinus, x);
                return minusCount + zeroCount + count >= k;
            });
            out.println(ans);
        }
    }
    private static long countLessThan(long[] a, long x) {
        long result = 0;
        for (int i = 0, j = a.length - 1; i < a.length - 1; i++) {
            while (i < j && a[i] * a[j] <= x) {
                j--;
            }
            if (i < j) {
                result += a.length - (j + 1);
            } else {
                result += a.length - (i + 1);
            }
        }
        return result;
    }
    private static void reverse(long[] a) {
        reverse(a, 0, a.length);
    }
    private static void reverse(long[] a, int fromIndex, int toIndex) {
        if (fromIndex < 0 || toIndex > a.length || fromIndex > toIndex) {
            throw new IndexOutOfBoundsException();
        }
        for (int i = fromIndex, j = toIndex - 1; i < j; i++, j--) {
            long tmp = a[i];
            a[i] = a[j];
            a[j] = tmp;
        }
    }
    private static long lowerBound(long from, long to, LongPredicate p) {
        if (from == Long.MIN_VALUE || from > to) {
            throw new IllegalArgumentException();
        }
        long low = from;
        long high = to - 1;
        while (low <= high) {
            long mid = low + ((high - low) >>> 1);
            if (p.test(mid)) {
                high = mid - 1;
            } else {
                low = mid + 1;
            }
        }
        return low;
    }
    public static void main(String[] args) {
        MyWriter w = new MyWriter(System.out);
        solve(new MyScanner(System.in), w);
        w.flush();
    }
    static final class MyScanner {
        static final int BUFFER_SIZE = 8192;
        private final InputStream in;
        private final byte[] buffer = new byte[BUFFER_SIZE];
        private int point;
        private int readLength;

        MyScanner(InputStream in) {
            this.in = in;
        }
        private byte readByte() {
            if (point < readLength) {
                byte result = buffer[point];
                point += 1;
                return result;
            }
            try {
                readLength = in.read(buffer);
            } catch (IOException e) {
                throw new AssertionError(null, e);
            }
            if (readLength == -1) {
                return -1;
            }
            point = 1;
            return buffer[0];
        }
        private static boolean isPrintableCharExceptSpace(byte c) {
            return 33 <= c && c <= 126;
        }
        public char nextChar() {
            byte c = readByte();
            while (!(c == -1 || isPrintableCharExceptSpace(c))) {
                c = readByte();
            }
            if (c == -1) {
                throw new NoSuchElementException();
            }
            return (char)c;
        }
        public String next() {
            return next(16);
        }
        public String next(int n) {
            byte c = readByte();
            while (!(c == -1 || isPrintableCharExceptSpace(c))) {
                c = readByte();
            }
            if (c == -1) {
                throw new NoSuchElementException();
            }
            StringBuilder b = new StringBuilder(n);
            do {
                b.append((char)c);
                c = readByte();
            } while (c != -1 && isPrintableCharExceptSpace(c));
            return b.toString();
        }
        public long nextLong() {
            byte c = readByte();
            while (!(c == -1 || isPrintableCharExceptSpace(c))) {
                c = readByte();
            }
            if (c == -1) {
                throw new NoSuchElementException();
            }
            boolean minus = false;
            if (c == '-') {
                minus = true;
                c = readByte();
            }
            long result = 0L;
            do {
                if (c < '0' || c > '9') {
                    throw new InputMismatchException();
                }
                result = result * 10L + (c - '0');
                c = readByte();
            } while (c != -1 && isPrintableCharExceptSpace(c));
            return minus ? -result : result;
        }
        public int nextInt() {
            long n = nextLong();
            if (n < Integer.MIN_VALUE || n > Integer.MAX_VALUE) {
                throw new InputMismatchException();
            }
            return (int)n;
        }
        public double nextDouble() {
            return Double.parseDouble(next());
        }
        public int[] nextIntArray(int n) {
            int[] result = new int[n];
            for (int i = 0; i < n; i++) {
                result[i] = nextInt();
            }
            return result;
        }
        public long[] nextLongArray(int n) {
            long[] result = new long[n];
            for (int i = 0; i < n; i++) {
                result[i] = nextLong();
            }
            return result;
        }
        public char[] nextCharArray(int n) {
            char[] result = new char[n];
            for (int i = 0; i < n; i++) {
                result[i] = nextChar();
            }
            return result;
        }
        public char[][] next2dCharArray(int n, int m) {
            char[][] result = new char[n][m];
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < m; j++) {
                    result[i][j] = nextChar();
                }
            }
            return result;
        }
        public int[][] nextVerticalIntArrays(int arrayCount, int arrayLength) {
            int[][] result = new int[arrayCount][arrayLength];
            for (int i = 0; i < arrayLength; i++) {
                for (int j = 0; j < arrayCount; j++) {
                    result[j][i] = nextInt();
                }
            }
            return result;
        }
        public long[][] nextVerticalLongArrays(int arrayCount,
                                               int arrayLength) {
            long[][] result = new long[arrayCount][arrayLength];
            for (int i = 0; i < arrayLength; i++) {
                for (int j = 0; j < arrayCount; j++) {
                    result[j][i] = nextLong();
                }
            }
            return result;
        }
        public char[][] nextVerticalCharArrays(int arrayCount,
                                               int arrayLength) {
            char[][] result = new char[arrayCount][arrayLength];
            for (int i = 0; i < arrayLength; i++) {
                for (int j = 0; j < arrayCount; j++) {
                    result[j][i] = nextChar();
                }
            }
            return result;
        }
    }
    static final class MyWriter extends PrintWriter {
        MyWriter(OutputStream out) {
            super(out);
        }
        public void joinAndPrintln(int[] x) {
            joinAndPrintln(x, " ");
        }
        public void joinAndPrintln(int[] x, String delimiter) {
            StringBuilder b = new StringBuilder();
            if (x.length > 0) {
                b.append(x[0]);
                for (int i = 1; i < x.length; i++) {
                    b.append(delimiter).append(x[i]);
                }
            }
            println(b.toString());
        }
        public void joinAndPrintln(long[] x) {
            joinAndPrintln(x, " ");
        }
        public void joinAndPrintln(long[] x, String delimiter) {
            StringBuilder b = new StringBuilder();
            if (x.length > 0) {
                b.append(x[0]);
                for (int i = 1; i < x.length; i++) {
                    b.append(delimiter).append(x[i]);
                }
            }
            println(b.toString());
        }
        public void joinAndPrintln(Iterable<?> iterable) {
            joinAndPrintln(iterable, " ");
        }
        public void joinAndPrintln(Iterable<?> iterable, String delimiter) {
            StringBuilder b = new StringBuilder();
            for (Iterator<?> i = iterable.iterator(); i.hasNext();) {
                b.append(i.next());
                while (i.hasNext()) {
                    b.append(delimiter).append(i.next());
                }
            }
            println(b.toString());
        }
    }
}
