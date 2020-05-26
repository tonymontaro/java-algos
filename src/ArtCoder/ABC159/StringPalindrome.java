package ArtCoder.ABC159;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.StringTokenizer;

public class StringPalindrome {
    static PrintWriter out;

    public static void main(String[] args) throws IOException {
        out = new PrintWriter(new OutputStreamWriter(System.out));
        CF_Reader in = new CF_Reader();

        char[] word = in.next().toCharArray();
        int ln = word.length;
        int halve = (ln - 1) / 2;
        boolean f = isPalindrome(0, halve-1, word);
        boolean s =  isPalindrome(halve + 1, ln - 1, word);
        boolean l = isPalindrome(0, ln - 1, word);
        System.out.println((f && s && l) ? "Yes": "No");

        out.close();
    }



    static boolean isPalindrome(int start, int end, char[] word) {
        while (start < end) {
            if (word[start] != word[end]) return false;
            start++;
            end--;
        }
        return true;
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

        long nextLong() throws IOException {
            return Long.parseLong(next());
        }

        int nextInt() throws IOException {
            return Integer.parseInt(next());
        }

        double nextDouble() throws IOException {
            return Double.parseDouble(next());
        }

        char nextCharacter() throws IOException {
            return next().charAt(0);
        }

        String nextLine() throws IOException {
            return br.readLine().trim();
        }
    }
}
