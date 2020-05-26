package ArtCoder.ABC159;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

public class BannedK {
    static PrintWriter out;

    public static void main(String[] args) throws IOException {
        out = new PrintWriter(new OutputStreamWriter(System.out));
        CF_Reader in = new CF_Reader();

        int n = in.Int();
        long[] nums = new long[n];
        Map<Long, Long> counts = new HashMap<>();
        for (int i = 0; i < n; i++) {
            nums[i] = in.Long();
            counts.merge(nums[i], (long) 1, Long::sum);
        }
        long total = 0;
        Map<Long, Long> totalCount = new HashMap<>();
        for (long cnt: counts.values()) {
            long totalVal = (cnt * (cnt - 1)) / 2;
            long totalValMinusOne = ((cnt - 1) * (cnt - 2)) / 2;
            total += totalVal;
            totalCount.put(cnt, totalVal);
            totalCount.put(cnt-1, totalValMinusOne);
        }
        for (long num: nums) {
            long cnt = counts.get(num);

            out.println(total - totalCount.get(cnt) + totalCount.get(cnt-1));
        }


        out.close();
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

        long Long() throws IOException {
            return Long.parseLong(next());
        }

        int Int() throws IOException {
            return Integer.parseInt(next());
        }

        double Double() throws IOException {
            return Double.parseDouble(next());
        }

        char Char() throws IOException {
            return next().charAt(0);
        }

        String Line() throws IOException {
            return br.readLine().trim();
        }
    }
}
