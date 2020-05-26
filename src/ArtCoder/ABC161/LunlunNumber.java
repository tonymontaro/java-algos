package ArtCoder.ABC161;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.StringTokenizer;

public class LunlunNumber {
    static BufferedReader br;
    static PrintWriter out;
    static StringTokenizer st;

    public static void main(String[] args) throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in));
        out = new PrintWriter(new OutputStreamWriter(System.out));

        out.println(solve(nextInt()));

        out.close();
    }

    static long solve(int k) {
        long[] lunluns = new long[100005];
        for (int i = 1; i < 10; i++) lunluns[i-1] = i;
        int slow = 0;
        int fast = 9;

        while (fast <= k - 1) {
            long last = lunluns[slow] % 10;
            long num = lunluns[slow] * 10;

            if (last != 0) {
                lunluns[fast] = num + last - 1;
                fast++;
            }
            lunluns[fast] = num + last;
            fast++;
            if (last != 9) {
                lunluns[fast] = num + last + 1;
                fast++;
            }
            slow++;
        }
        out.println();

        return lunluns[k-1];
    }


    static String next() throws IOException {
        while (st == null || !st.hasMoreTokens())
            st = new StringTokenizer(br.readLine().trim());
        return st.nextToken();
    }

    static long nextLong() throws IOException {
        return Long.parseLong(next());
    }

    static int nextInt() throws IOException {
        return Integer.parseInt(next());
    }

    static double nextDouble() throws IOException {
        return Double.parseDouble(next());
    }

    static char nextCharacter() throws IOException {
        return next().charAt(0);
    }

    static String nextLine() throws IOException {
        return br.readLine().trim();
    }
}
