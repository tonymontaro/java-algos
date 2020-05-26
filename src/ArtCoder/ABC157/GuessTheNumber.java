package ArtCoder.ABC157;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.StringTokenizer;

public class GuessTheNumber {
    static PrintWriter out;
    static CF_Reader in;

    public static void main(String[] args) throws IOException {
        out = new PrintWriter(new OutputStreamWriter(System.out));
        in = new CF_Reader();

        int n = in.intNext();
        int m = in.intNext();
        int[] nums = new int[n];
        Arrays.fill(nums, -1);
        if (solve(m, nums) == -1) out.println(-1);
        out.close();
    }

    static int solve(int m, int[] nums) throws IOException {
        for (int i = 0; i < m; i++) {
            int x = in.intNext()-1;
            int y = in.intNext();
            if (nums[x] != -1 && nums[x] != y) return -1;
            nums[x] = y;
        }
        if (nums.length > 1 && nums[0] == 0) return -1;
        if (nums.length > 1 && nums[0] == -1) nums[0] = 1;
        for (int num: nums) out.print((num == -1) ? 0 : num);
        return 0;
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

        String line() throws IOException {
            return br.readLine().trim();
        }
    }
}
