package CodeNCode.DynamicProgramming;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.StringTokenizer;

public class EasyTiling {
    static PrintWriter out;
    static CF_Reader in;
    static long MOD = 1000000007L;

    public static void main(String[] args) throws IOException {
        out = new PrintWriter(new OutputStreamWriter(System.out));
        in = new CF_Reader();

        StringBuilder result = new StringBuilder();
        int tests = in.intNext();
        HashMap<Long, Long> seen = new HashMap<>();
        seen.put(1L, 1L);
        seen.put(2L, 2L);
        for (int t = 0; t < tests; t++) {
            long n = in.longNext();
            if (n <= 2) result.append(n).append("\n");
            else {
                MatrixExp matrixExp = new MatrixExp();
                long[][] multiplier = matrixExp.matrixPower(new long[][]{{0, 1}, {1, 1}}, n - 1);
                long[][] res = matrixExp.multiplyMatrix(new long[][]{{1, 1}}, multiplier);
                result.append(res[0][1]).append("\n");
            }
        }
        out.println(result);

        out.close();
    }

    static class MatrixExp {
        long[][] matrixPower(long [][] base, long pow)	{
            int N = base.length;
            long [][] ans = new long [N][N];
            for (int i = 0; i < N; i++)	ans[i][i] = 1;

            while ( pow != 0 )	{
                if	( (pow&1) != 0 )	ans = multiplyMatrix(ans, base);
                base = multiplyMatrix(base, base);
                pow >>= 1;
            }
            return	ans;
        }

        long [][] multiplyMatrix(long [][] m, long [][] m2)	{
            long rows = m.length;
            long cols = m2[0].length;
            long kLen = m[0].length;
            long [][] ans = new long [(int) rows][(int) cols];

            for (int r = 0; r < rows; r++)	for (int c = 0; c < cols; c++)	{
                ans[r][c] = 0;
                for (int k = 0; k < kLen; k++)	{
                    ans[r][c] = (ans[r][c] + (m[r][k] * m2[k][c]) % MOD) % MOD;
                }
            }
            return	ans;
        }
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
