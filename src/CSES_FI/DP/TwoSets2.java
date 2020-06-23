
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.StringTokenizer;

public class TwoSets2 {
    static PrintWriter out;
    static CF_Reader in;

    public static void main(String[] args) throws IOException {
        out = new PrintWriter(new OutputStreamWriter(System.out));
        in = new CF_Reader();

        int n = in.intNext();
        out.println(solve(n));

        out.close();
    }

    static int solve(int n) {
        int MOD = 1000000007;
        int total = (n * (1 + n)) / 2;

        if (total % 2 == 1) return 0;

        int half = total / 2;
        int[] prev = new int[half + 1];

        for (int i = 1; i <= n; i++) {
            int[] ways = new int[half + 1];
            for (int j = 1; j <= half; j++) {
                if (j < i) ways[j] = prev[j];
                else if (j == i) ways[j] = (prev[j] + 1) % MOD;
                else if (j > i && prev[j - i] > 0) ways[j] = (prev[j] + prev[j - i]) % MOD;
            }
            prev = ways;
//            out.println(Arrays.toString(ways));
        }
        if (prev[half] % 2 == 1) prev[half] += MOD;
        return prev[half] / 2;
    }

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


