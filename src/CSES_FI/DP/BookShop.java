
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BookShop {
    static PrintWriter out;
    static CF_Reader in;

    public static void main(String[] args) throws IOException {
        out = new PrintWriter(new OutputStreamWriter(System.out));
        in = new CF_Reader();

        int n = in.intNext(), maxPrice = in.intNext();
        int[] prices = in.nextIntArray(n);
        int[] pages = in.nextIntArray(n);

        out.println(maxPages(n, maxPrice, prices, pages));

        out.close();
    }

    static int maxPages(int n, int maxPrice, int[] prices, int[] pages) {
        int[] selectedPages = new int[maxPrice + 1];
        for (int book = 0; book < n; book++) {
            int[] currentPages = new int[maxPrice + 1];
            int bookPrice = prices[book];
            int bookPages = pages[book];
            for (int price = 1; price <= maxPrice; price++) {
                if (price < bookPrice) currentPages[price] = selectedPages[price];
                else currentPages[price] = Math.max(selectedPages[price], bookPages + selectedPages[price - bookPrice]);
            }
            selectedPages = currentPages;
        }
        return selectedPages[maxPrice];
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


