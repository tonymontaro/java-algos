package ArtCoder.ABC158;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.StringTokenizer;

public class StationAndBus {
    static PrintWriter out;

    public static void main(String[] args) throws IOException {
        out = new PrintWriter(new OutputStreamWriter(System.out));
        CF_Reader in = new CF_Reader();

        char[] s = in.next().toCharArray();

        System.out.println((s[0] == s[1] && s[1] == s[2]) ? "No": "Yes");

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
