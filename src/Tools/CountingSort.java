package Tools;

import java.util.*;

public class CountingSort {
    public static void countingSort(int[] ar) {
        int minVal = ar[0];
        int maxVal = ar[0];
        for (int num: ar) {
            if (num < minVal) minVal = num;
            if (num > maxVal) maxVal = num;
        }
        countingSort(ar, minVal, maxVal);
    }

    public static void countingSort(int[] ar, int minVal, int maxVal) {
        int sz = maxVal - minVal + 1;
        int[] B = new int[sz];
        for (int value : ar) B[value - minVal]++;
        for (int i = 0, k = 0; i < sz; i++) while (B[i]-- > 0) ar[k++] = i + minVal;
    }

    public static void main(String[] args) {
        int[] items = new int[]{5, 2, 8, 1, 8, 2, 8, 0, 89};
        countingSort(items);
        System.out.println(Arrays.toString(items));
    }
}
