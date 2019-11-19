package myquicksort;

import java.lang.Comparable;

public class QuickSort {
    public static void sort(Comparable[] arr){
        sort(arr, 0, arr.length - 1);

    }
    private static void sort(Comparable[] arr, int i, int j){
        int low = i, high = j;
        if(high <= low)
            return;
        Comparable t = arr[low];
        while (low < high){
            while (high > low && t.compareTo(arr[high]) < 0){
                high--;
            }
            arr[low++] = arr[high];
            while (high > low && t.compareTo(arr[low]) > 0){
                low++;
            }
            arr[high--] = arr[low];
        }
        arr[low] = t;

        sort(arr, i, low-1);
        sort(arr, low+1, j);
    }

}
