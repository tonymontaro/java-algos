package ArtCoder.ABC156;

import java.io.*;
import java.util.*;
import static java.lang.System.out;

public class Bouquet_yama2019 {
    static MyReader in = new MyReader();
    static long MOD = 1000000007;

    public static void main(String[] args){
        int n, a, b; {int[] c = in.ii(); n = c[0]; a = c[1]; b = c[2];}
        long ans = (ModOp.pow(2, n) - 1 + MOD - ModOp.nCr(n, a) + MOD - ModOp.nCr(n, b)) % MOD;
        out.println(ans);
    }

    static class ModOp {
        static long MOD = 1000000007;

        static long pow(long a, long b){
            long ret = 1;
            while(b > 0){
                if(b%2 == 1){
                    ret = ret*a%MOD;
                }
                a = a*a%MOD;
                b /= 2;
            }
            return ret;
        }

        static long mul(int s, int e){
            long ret = 1;
            for(int i = s; i <= e; i++){
                ret = ret*i%MOD;
            }
            return ret;
        }

        static long fact(int n){
            return mul(1, n);
        }

        static long nCr(int n, int r){
            return mul(n-r+1, n) * pow(fact(r), MOD-2) % MOD;
        }

    }

    static class MyReader extends BufferedReader{
        MyReader(){
            super(new InputStreamReader(System.in));
        }

        String s(){
            try{return readLine();}catch(IOException e){return "";}
        }

        String[] ss(){
            return s().split(" ");
        }

        int i(){
            return Integer.parseInt(s());
        }

        int[] ii(){
            String[] ss = ss();
            int[] ii = new int[ss.length];
            for(int j = 0; j < ss.length; j++) ii[j] = Integer.parseInt(ss[j]);
            return ii;
        }

        ArrayList<Integer> li(){
            String[] ss = ss();
            ArrayList<Integer> li = new ArrayList<>();
            for(int j = 0; j < ss.length; j++) li.add(Integer.parseInt(ss[j]));
            return li;
        }

        long l(){
            return Long.parseLong(s());
        }

        long[] ll(){
            String[] ss = ss();
            long[] ll = new long[ss.length];
            for(int j = 0; j < ss.length; j++) ll[j] = Long.parseLong(ss[j]);
            return ll;
        }
    }
}
