package myquickSort;

import java.lang.Comparable;
import java.util.Arrays;
import java.util.Scanner;

public class Dijsktra_3way_quicksort {
    public static void sort(Comparable[] arr){
        sort(arr, 0, arr.length - 1);
    }
    private static void sort(Comparable[] arr, int low, int high){
        if (low >= high)
            return;

        int i = low, j = high;
        Comparable v = arr[low];
        int p = low;
        while (p <= j){
            if(arr[p].compareTo(v) < 0)
                swap(arr, p++, i++);
            else if (arr[p].compareTo(v) > 0)
                swap(arr, p, j--);
            else
                p++;
        }

        sort(arr, low, i - 1);
        sort(arr, j + 1, high);
    }

    private static void swap(Comparable[] arr, int i, int j){
        Comparable t = arr[i];
        arr[i] = arr[j];
        arr[j] = t;
    }

    public static void main(String[] args){
        Scanner scan = new Scanner(System.in);
        int c = scan.nextInt();
        Integer[] arr = new Integer[c];
        for(int i = 0; i < c; i++){
            arr[i] = scan.nextInt();
        }
//        QuickSort.sort(arr);
        Dijsktra_3way_quicksort.sort(arr);
        System.out.print(Arrays.toString(arr));
    }
}
