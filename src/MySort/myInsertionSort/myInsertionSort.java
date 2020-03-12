package MySort.myInsertionSort;

import java.lang.Comparable;

public class myInsertionSort {
    public static void sort(Comparable[] arr) {
        sort(arr, arr.length);
    }

    private static void sort(Comparable[] arr, int len) {
        int i, j;
        Comparable temp;
        for (i = 1; i < len; i++) {
            temp = arr[i];
            for (j = i - 1; j >= 0; j--) {
                if (temp.compareTo(arr[j]) > 0)
                    break;
                arr[j + 1] = arr[j];
            }
            arr[j + 1] = temp;
        }
    }
}
