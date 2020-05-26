package ArtCoder.ABC157;

import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.NoSuchElementException;

class FriendSuggestions_pot8os {

    static class Solver {
        final FastScanner sc;
        final PrintWriter writer;

        Solver(final FastScanner sc, final PrintWriter writer) {
            this.sc = sc;
            this.writer = writer;
        }

        void run() {
            int n = sc.nextInt();
            int m = sc.nextInt();
            int k = sc.nextInt();
            UnionFind uf = new UnionFind(n);
            int[] friendCount = new int[n + 1];
            int[] suggestedCount = new int[n + 1];
            for (int i = 0; i < m; i++) {
                int[] input = sc.nextIntArray(2);
                uf.unite(input[0], input[1]);
                friendCount[input[0]]++;
                friendCount[input[1]]++;
            }
            for (int i = 1; i <= n; i++) {
                suggestedCount[i] = uf.size(i) - friendCount[i] - 1;
            }
            for (int i = 0; i < k; i++) {
                int[] input = sc.nextIntArray(2);
                if (uf.connected(input[0], input[1])) {
                    suggestedCount[input[0]]--;
                    suggestedCount[input[1]]--;
                }
            }
            StringBuilder sb = new StringBuilder();
            for (int i = 1; i <= n; i++) {
                sb.append(suggestedCount[i]).append(" ");
            }
            writer.println(sb.toString().trim());
        }
    }

    public static void main(final String[] args) {
        final FastScanner sc = new FastScanner();
        try (final PrintWriter w = new PrintWriter(System.out)) {
            new Solver(sc, w).run();
            w.flush();
        }
    }

    static class UnionFind {
        int[] parents;
        int[] size;

        public UnionFind(int n) {
            this.parents = new int[n + 1];
            this.size = new int[n + 1];
            for (int i = 0; i <= n; i++) {
                parents[i] = i;
            }
            Arrays.fill(size, 1);
        }

        public void unite(int x, int y) {
            int rx = find(x);
            int ry = find(y);
            if (rx == ry) {
                return;
            }
            if (size[rx] < size[ry]) {
                size[ry] += size[rx];
                parents[rx] = ry;
            } else {
                size[rx] += size[ry];
                parents[ry] = rx;
            }
        }

        public int find(int x) {
            int root = x;
            while (root != parents[root]) {
                root = parents[root];
            }
            return root;
        }

        public int size(int x) {
            return size[find(x)];
        }

        public boolean connected(int x, int y) {
            return find(x) == find(y);
        }
    }

    // FastScanner
    static class FastScanner {
        private final InputStream in = System.in;
        private final byte[] buffer = new byte[1024];
        private int ptr = 0;
        private int buflen = 0;

        public FastScanner() {
        }

        private boolean hasNextByte() {
            if (ptr < buflen) {
                return true;
            } else {
                ptr = 0;
                try {
                    buflen = in.read(buffer);
                } catch (final IOException e) {
                    e.printStackTrace();
                }
                if (buflen <= 0) {
                    return false;
                }
            }
            return true;
        }

        private int readByte() {
            if (hasNextByte())
                return buffer[ptr++];
            else
                return -1;
        }

        private boolean isPrintableChar(final int c) {
            return 33 <= c && c <= 126;
        }

        public boolean hasNext() {
            while (hasNextByte() && !isPrintableChar(buffer[ptr]))
                ptr++;
            return hasNextByte();
        }

        public String next() {
            if (!hasNext())
                throw new NoSuchElementException();
            final StringBuilder sb = new StringBuilder();
            int b = readByte();
            while (isPrintableChar(b)) {
                sb.appendCodePoint(b);
                b = readByte();
            }
            return sb.toString();
        }

        public long nextLong() {
            if (!hasNext())
                throw new NoSuchElementException();
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
            final long nl = nextLong();
            if (nl < Integer.MIN_VALUE || nl > Integer.MAX_VALUE)
                throw new NumberFormatException();
            return (int) nl;
        }

        public double nextDouble() {
            return Double.parseDouble(next());
        }

        public int[] nextIntArray(final int n) {
            final int[] a = new int[n];
            for (int i = 0; i < n; i++)
                a[i] = nextInt();
            return a;
        }

        public long[] nextLongArray(final int n) {
            final long[] a = new long[n];
            for (int i = 0; i < n; i++)
                a[i] = nextLong();
            return a;
        }
    }
}
