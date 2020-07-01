package Tools;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

class ArraysUtil {
    public static void main(String[] args) {

        long[] arr = new long[]{1, 2, 3, 3, 3, 4, 7};
        System.out.println(ArrUtil.upperBound(arr, 1));
        System.out.println(ArrUtil.lowerBound(arr, 6));

        ArrayList<Long> ar = new ArrayList<>();
        for (long a: arr) ar.add(a);
        System.out.println(ArrUtil.upperBound(ar, 1));
        System.out.println(ArrUtil.lowerBound(ar, 6));
        System.out.println(ArrUtil.lowerBound(ar, 8));
        ArrUtil.print(arr);
        System.out.println(ar);
    }


    static class ArrUtil {
        public static int upperBound(long[] array, long obj) {
            int l = 0, r = array.length - 1;
            while (r - l >= 0) {
                int c = (l + r) / 2;
                if (obj < array[c]) {
                    r = c - 1;
                } else {
                    l = c + 1;
                }
            }
            return l;
        }

        public static int upperBound(ArrayList<Long> array, long obj) {
            int l = 0, r = array.size() - 1;
            while (r - l >= 0) {
                int c = (l + r) / 2;
                if (obj < array.get(c)) {
                    r = c - 1;
                } else {
                    l = c + 1;
                }
            }
            return l;
        }

        public static int lowerBound(long[] array, long obj) {
            int l = 0, r = array.length - 1;
            while (r - l >= 0) {
                int c = (l + r) / 2;
                if (obj <= array[c]) {
                    r = c - 1;
                } else {
                    l = c + 1;
                }
            }
            return l;
        }

        public static int lowerBound(ArrayList<Long> array, long obj) {
            int l = 0, r = array.size() - 1;
            while (r - l >= 0) {
                int c = (l + r) / 2;
                if (obj <= array.get(c)) {
                    r = c - 1;
                } else {
                    l = c + 1;
                }
            }
            return l;
        }

        public static void print(long[] arr) {
            System.out.println(Arrays.toString(arr));
        }
        public static void print(int[] arr) {
            System.out.println(Arrays.toString(arr));
        }
        public static void print(char[] arr) {
            System.out.println(Arrays.toString(arr));
        }
    }
}
