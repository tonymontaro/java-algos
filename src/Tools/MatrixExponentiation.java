package Tools;

import java.util.Arrays;

public class MatrixExponentiation {
    static class MatrixExp {
        long[][] matrixPower(long [][] base, long pow)	{
            int N = base.length;
            long [][] ans = new long [N][N];
            // generate identity matrix
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
                    ans[r][c] += m[r][k] * m2[k][c];
                }
            }
            return	ans;
        }

        long [][] multiplyMatrix_withMod(long [][] m, long [][] m2, long MOD)	{
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

    public static void main(String[] args) {
        long[][] matrixA = new long[][]{{3, 4}, {2, 1}};
//        long[][] matrixB = new long[][]{{3, 5}, {3, 4}};

        MatrixExp matrixExp = new MatrixExp();
        long[][] res = matrixExp.multiplyMatrix(new long[][]{{1, 1}}, new long[][]{{1, 2}, {2, 3}});
        for (long[] item: res) System.out.println(Arrays.toString(item));
        System.out.println();
        long[][] res2 = matrixExp.matrixPower(new long[][]{{0, 1}, {1, 1}}, 3);
        for (long[] item: res2) System.out.println(Arrays.toString(item));
    }
}
