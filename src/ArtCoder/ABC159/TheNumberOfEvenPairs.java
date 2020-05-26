package ArtCoder.ABC159;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.StringTokenizer;

public class TheNumberOfEvenPairs {
    static PrintWriter out;

    public static void main(String[] args) throws IOException {
        out = new PrintWriter(new OutputStreamWriter(System.out));
        CF_Reader in = new CF_Reader();

        int n = in.Int();
        int m = in.Int();

        int total = ((n - 1) * n) / 2  + ((m - 1) * m) / 2;
        out.println(total);

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

        String nextLine() throws IOException {
            return br.readLine().trim();
        }
    }
}
