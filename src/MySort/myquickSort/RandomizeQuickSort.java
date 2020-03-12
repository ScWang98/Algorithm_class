package MySort.myquickSort;

import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;

public class RandomizeQuickSort {
    public static void sort(Comparable[] arr) {
        sort(arr, 0, arr.length - 1);

    }

    private static void sort(Comparable[] arr, int i, int j) {
        if (j <= i) {
            return;
        }
        int low = i, high = j;
        Random rand = new Random();
        int r = rand.nextInt(high - low + 1) + low;
        Comparable t = arr[r];
        arr[low] = arr[r];
        while (low < high) {
            while (high > low && t.compareTo(arr[high]) < 0) {
                high--;
            }
            arr[low++] = arr[high];
            while (high > low && t.compareTo(arr[low]) > 0) {
                low++;
            }
            arr[high--] = arr[low];
        }
        arr[low] = t;

        sort(arr, i, low - 1);
        sort(arr, low + 1, j);
    }

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        int c = scan.nextInt();
        Integer[] arr = new Integer[c];
        for (int i = 0; i < c; i++) {
            arr[i] = scan.nextInt();
        }
//        QuickSort.sort(arr);
        RandomizeQuickSort.sort(arr);
        System.out.print(Arrays.toString(arr));
    }
}
