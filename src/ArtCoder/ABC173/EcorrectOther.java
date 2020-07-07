package ArtCoder.ABC173;

import java.io.*;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.*;



public class EcorrectOther {
    static class Reader
    {
        final private int BUFFER_SIZE = 1 << 16;
        private DataInputStream din;
        private byte[] buffer;
        private int bufferPointer, bytesRead;

        public Reader()
        {
            din = new DataInputStream(System.in);
            buffer = new byte[BUFFER_SIZE];
            bufferPointer = bytesRead = 0;
        }

        public Reader(String file_name) throws IOException
        {
            din = new DataInputStream(new FileInputStream(file_name));
            buffer = new byte[BUFFER_SIZE];
            bufferPointer = bytesRead = 0;
        }

        public String readLine() throws IOException
        {
            byte[] buf = new byte[64]; // line length
            int cnt = 0, c;
            while ((c = read()) != -1)
            {
                if (c == '\n')
                    break;
                buf[cnt++] = (byte) c;
            }
            return new String(buf, 0, cnt);
        }

        public int nextInt() throws IOException
        {
            int ret = 0;
            byte c = read();
            while (c <= ' ')
                c = read();
            boolean neg = (c == '-');
            if (neg)
                c = read();
            do
            {
                ret = ret * 10 + c - '0';
            }  while ((c = read()) >= '0' && c <= '9');

            if (neg)
                return -ret;
            return ret;
        }

        public long nextLong() throws IOException
        {
            long ret = 0;
            byte c = read();
            while (c <= ' ')
                c = read();
            boolean neg = (c == '-');
            if (neg)
                c = read();
            do {
                ret = ret * 10 + c - '0';
            }
            while ((c = read()) >= '0' && c <= '9');
            if (neg)
                return -ret;
            return ret;
        }

        public double nextDouble() throws IOException
        {
            double ret = 0, div = 1;
            byte c = read();
            while (c <= ' ')
                c = read();
            boolean neg = (c == '-');
            if (neg)
                c = read();

            do {
                ret = ret * 10 + c - '0';
            }
            while ((c = read()) >= '0' && c <= '9');

            if (c == '.')
            {
                while ((c = read()) >= '0' && c <= '9')
                {
                    ret += (c - '0') / (div *= 10);
                }
            }

            if (neg)
                return -ret;
            return ret;
        }

        private void fillBuffer() throws IOException
        {
            bytesRead = din.read(buffer, bufferPointer = 0, BUFFER_SIZE);
            if (bytesRead == -1)
                buffer[0] = -1;
        }

        private byte read() throws IOException
        {
            if (bufferPointer == bytesRead)
                fillBuffer();
            return buffer[bufferPointer++];
        }

        public void close() throws IOException
        {
            if (din == null)
                return;
            din.close();
        }
    }

    public static PrintWriter out = new PrintWriter (new BufferedOutputStream(System.out));
    public static Reader sc = new Reader();

    static int mod = (int) (1e9+7);

    public static void main(String[] args) throws IOException {
        int n = sc.nextInt();
        int k = sc.nextInt();
        long[] a = new long[n];
        for(int i=0;i<n;++i) a[i] = sc.nextLong();
        long ans = f(a,n,k);
        out.print(ans);
        out.close();
    }

    static long f(long A[], int n, int k) {

        sort(A);

        long product = 1;

        if (A[n - 1] == 0 && k % 2 != 0)
            return 0;

        if (A[n - 1] <= 0 && k % 2 != 0) {
            for (int i = n - 1; i >= n - k; i--)
                product = (mul(product, A[i])%mod+mod)%mod;
            return product;
        }

        // else
        // i is current left pointer index
        int i = 0;

        // j is current right pointer index
        int j = n - 1;

        if (k % 2 != 0) {
            product =(mul(product, A[j])%mod+mod)%mod;
            j--;
            k--;
        }

        k >>= 1;

        for (int itr = 0; itr < k; itr++) {
            // product from left pointers
            long left_product = (mul(A[i] , A[i + 1])%mod+mod)%mod;

            long right_product = (mul(A[j] ,A[j - 1])%mod+mod)%mod;

            if (A[i]*A[i+1] > A[j]*A[j-1]) {
                product = (mul(product,left_product)%mod+mod)%mod;
                i += 2;
            }
            else {
                product = (mul(product,right_product)%mod+mod)%mod;
                j -= 2;
            }
        }

        return product;
    }

    private static long mul(long a,long b) {
        a = ((a%mod)+ mod)%mod;
        b = ((b%mod)+mod)%mod;
        long ans = ((a*b)%mod+mod)%mod;
        return ans;
    }

    static void sort(long[] A) {
        PriorityQueue<Long> pq = new PriorityQueue<>();
        int i = 0;
        for (i = 0; i < A.length; i++)
            pq.add(A[i]);
        for (i = 0; i < A.length; i++)
            A[i] = pq.poll();
    }

}

