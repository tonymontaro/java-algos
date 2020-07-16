//package CSES_FI;

import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.*;

public class test {
    private static final int IO_BUFFERS = 128 * 1024;
    private static FastReader in = new FastReader();
    private static FastWriter out = new FastWriter();
    public static void main(String[] args) throws IOException
    {
        int n = in.intNext(), numCus = in.intNext();

        TreeSet<Tuple> prices = new TreeSet<>();
        for (int i = 0; i < n; i++) {
            prices.add(new Tuple(in.intNext(), i));
        }
        StringBuilder res = new StringBuilder();
        for (int i = 0; i < numCus; i++) {
            Tuple maxPrice = new Tuple(in.intNext(), n + 1);
            Tuple bestPrice = prices.lower(maxPrice);
            int best = -1;
            if (bestPrice != null) {
                best = bestPrice.a;
                prices.remove(bestPrice);
            }
            res.append(best).append("\n");
        }
        out.println(res);
        out.close();
    }

    /** Output processing class for competitions */
    static class FastWriter {
        private final StringBuilder out;

        public FastWriter() {
            out = new StringBuilder(IO_BUFFERS);
        }

        public FastWriter print(Object object) {
            out.append(object);
            return this;
        }

        public FastWriter print(String format, Object... args) {
            out.append(String.format(format, args));
            return this;
        }

        public FastWriter println(Object object) {
            out.append(object).append("\n");
            return this;
        }

        public void close() throws IOException {
            System.out.print(out);
        }
    }

    /** Input processing class for competitions */
    static class FastReader {
        private DataInputStream din;
        private byte[] buffer;
        private int bufferPointer, bytesRead;

        public FastReader() {
            din = new DataInputStream(System.in);
            buffer = new byte[IO_BUFFERS];
            bufferPointer = bytesRead = 0;
        }

        public FastReader(String file_name) throws IOException {
            din = new DataInputStream(new FileInputStream(file_name));
            buffer = new byte[IO_BUFFERS];
            bufferPointer = bytesRead = 0;
        }

        public String readLine() throws IOException {
            byte[] buf = new byte[64]; // line length
            int cnt = 0, c;
            while ((c = read()) != -1) {
                if (c == '\n') break;
                buf[cnt++] = (byte) c;
            }
            return new String(buf, 0, cnt);
        }

        public int intNext() throws IOException {
            int ret = 0;
            byte c = read();
            while (c <= ' ') c = read();
            boolean neg = (c == '-');
            if (neg) c = read();
            do {
                ret = ret * 10 + c - '0';
            } while ((c = read()) >= '0' && c <= '9');

            if (neg) return -ret;
            return ret;
        }

        public long longNext() throws IOException {
            long ret = 0;
            byte c = read();
            while (c <= ' ') c = read();
            boolean neg = (c == '-');
            if (neg) c = read();
            do {
                ret = ret * 10 + c - '0';
            } while ((c = read()) >= '0' && c <= '9');
            if (neg) return -ret;
            return ret;
        }

        public double doubleNext() throws IOException {
            double ret = 0, div = 1;
            byte c = read();
            while (c <= ' ') c = read();
            boolean neg = (c == '-');
            if (neg) c = read();

            do {
                ret = ret * 10 + c - '0';
            } while ((c = read()) >= '0' && c <= '9');

            if (c == '.') {
                while ((c = read()) >= '0' && c <= '9') {
                    ret += (c - '0') / (div *= 10);
                }
            }

            if (neg) return -ret;
            return ret;
        }

        public int[] nextIntArray(final int n) throws IOException {
            final int[] a = new int[n];
            for (int i = 0; i < n; i++)
                a[i] = intNext();
            return a;
        }

        public long[] nextLongArray(final int n) throws IOException {
            final long[] a = new long[n];
            for (int i = 0; i < n; i++)
                a[i] = longNext();
            return a;
        }

        public ArrayList<Integer>[] adjacencyList(int n, int m) throws IOException {
            ArrayList<Integer>[] adj = new ArrayList[n+1];
            for (int i = 1; i <= n; i++) adj[i] = new ArrayList<>();
            for (int i = 0; i < m; i++) {
                int a = intNext(), b = intNext();
                adj[a].add(b);
                adj[b].add(a);
            }
            return adj;
        }

        private void fillBuffer() throws IOException {
            bytesRead = din.read(buffer, bufferPointer = 0, IO_BUFFERS);
            if (bytesRead == -1) buffer[0] = -1;
        }

        private byte read() throws IOException {
            if (bufferPointer == bytesRead) fillBuffer();
            return buffer[bufferPointer++];
        }

        public void close() throws IOException {
            if (din == null) return;
            din.close();
        }
    }


    static class Tuple implements Comparable<Tuple> {
        int a;
        int b;

        public Tuple(int a, int b) {
            this.a = a;
            this.b = b;
        }

        public int getA() {
            return a;
        }

        public int getB() {
            return b;
        }

        public int compareTo(Tuple other) {
            if (this.a == other.a) return Integer.compare(this.b, other.b);
            return Integer.compare(this.a, other.a);
        }

        @Override
        public int hashCode() { return Arrays.deepHashCode(new Integer[]{a, b});}

        @Override
        public boolean equals(Object o) {
            if (!(o instanceof Tuple)) return false;
            Tuple pairo = (Tuple) o;
            return (this.a == pairo.a && this.b == pairo.b);
        }

        @Override
        public String toString () {
            return String.format("%d,%d  ", this.a, this.b);
        }
    }
} 