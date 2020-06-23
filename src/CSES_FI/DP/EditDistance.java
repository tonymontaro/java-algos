
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.StringTokenizer;

public class EditDistance {
    static PrintWriter out;
    static CF_Reader in;

    public static void main(String[] args) throws IOException {
        out = new PrintWriter(new OutputStreamWriter(System.out));
        in = new CF_Reader();

        char[] word1 = in.next().toCharArray();
        char[] word2 = in.next().toCharArray();
        out.println(editDistance(word1, word2));

        out.close();
    }

    static int editDistance(char[] w1, char[] w2) {
        int[] prev = new int[w1.length + 1];
        for (int i = 0; i <= w1.length; i++) prev[i] = i;
        for (int i = 0; i < w2.length; i++) {
            int[] distances = new int[w1.length + 1];
            distances[0] = prev[0] + 1;
            for (int j = 0; j < w1.length; j++) {
                if (w2[i] == w1[j]) distances[j + 1] = prev[j];
                else {
                    int tmp = Math.min(distances[j], prev[j + 1]);
                    distances[j + 1] = 1 + (Math.min(tmp, prev[j]));
                }
            }
            prev = distances;
        }
        return prev[w1.length];
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


