package ArtCoder.ABC158;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.StringTokenizer;

public class StringFormation {
    static PrintWriter out;

    public static void main(String[] args) throws IOException {
        // O(n^2) time | O(n) space
        out = new PrintWriter(new OutputStreamWriter(System.out));
        CF_Reader in = new CF_Reader();

        StringBuilder middle = new StringBuilder(in.next());
        StringBuilder head = new StringBuilder();
        StringBuilder tail = new StringBuilder();
        boolean reverse = false;

        int tests = in.intNext();
        for (int t = 0; t < tests; t++) {
            if (in.intNext() == 1) {
                reverse = !reverse;
            } else if (in.intNext() == 1) {
                if (!reverse) head.append(in.charNext());
                else tail.append(in.charNext());
            } else {
                if (!reverse) tail.append(in.charNext());
                else head.append(in.charNext());
            }
        }
        StringBuilder result = new StringBuilder();
        if (!reverse) {
            head.reverse();
            result.append(head);
            result.append(middle).append(tail);
        } else {
            tail.reverse();
            middle.reverse();
            result.append(tail).append(middle).append(head);
        }
        out.println(result);
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
