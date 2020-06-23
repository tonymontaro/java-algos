
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.Comparator;
import java.util.StringTokenizer;

public class Projects {
    static PrintWriter out;
    static CF_Reader in;
    static int n;
    static long[][] projects;
    static long[] cache;

    public static void main(String[] args) throws IOException {
        out = new PrintWriter(new OutputStreamWriter(System.out));
        in = new CF_Reader();

        n = in.intNext();
        projects = new long[n][3];
        for (int i = 0; i < n; i++) {
            projects[i] = new long[]{in.longNext(), in.longNext(), in.longNext()};
        }
        Arrays.sort(projects, Comparator.comparingLong(o -> o[1]));

        out.println(getMaxAmount());

        out.close();
    }

    static long getMaxAmount() {
        // O(N * logN)
        cache = new long[n + 1];
        long[] ends = new long[n + 1];
        int low = 0, high = 0;

        for (int i = 0; i < n; i++) {
            ends[i + 1] = projects[i][1];
            long start = projects[i][0];
            int h = high, l = low;
            int valid = 0;
            while (l <= h) {
                int mid = (h + l) / 2;
                if (start > ends[mid]) {
                    valid = mid;
                    l = mid + 1;
                } else h = mid - 1;
            }
            high++;
            long addItem = projects[i][2] + cache[valid];
            cache[i + 1] = Math.max(cache[i], addItem);
        }
        return cache[n];
    }

//    static long getMaxAmountRecursive(int idx, long end) {
//        // O(N^2)
//        if (idx >= n) return 0;
//        if (projects[idx][0] <= end) return getMaxAmountRecursive(idx + 1, end);
//
//        if (cache[idx] == null) {
//            long addItem = projects[idx][2] + getMaxAmountRecursive(idx + 1, projects[idx][1]);
//            long skipItem = getMaxAmountRecursive(idx + 1, end);
//
//            cache[idx] = Math.max(addItem, skipItem);
//        }
//        return cache[idx];
//    }

    static class CF_Reader {
        BufferedReader br;
        StringTokenizer st;

        public CF_Reader() throws IOException {
            br = new BufferedReader(new InputStreamReader(System.in));
        }

        String next() throws IOException {
            while (st == null || !st.hasMoreTokens())
                st = new StringTokenizer(br.readLine().trim());
            return st.nextToken();
        }

        long longNext() throws IOException {
            return Long.parseLong(next());
        }

        int intNext() throws IOException {
            return Integer.parseInt(next());
        }

        double doubleNext() throws IOException {
            return Double.parseDouble(next());
        }

        char charNext() throws IOException {
            return next().charAt(0);
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

        String line() throws IOException {
            return br.readLine().trim();
        }
    }
}


