package MySort.mymergeSort;

import java.lang.Comparable;
import java.util.Scanner;

public class mymergeSort_topdown {
    private static Comparable[] aux;

    public static void sort(Comparable[] arr) {
        aux = new Comparable[arr.length];
        sort(arr, 0, arr.length - 1);
    }

    private static void sort(Comparable[] arr, int low, int hig) {
        if (hig <= low)
            return;
        int mid = low + (hig - low) / 2;
        sort(arr, low, mid);
        sort(arr, mid + 1, hig);
        merge(arr, low, mid, hig);
    }

    public static void merge(Comparable[] arr, int low, int mid, int hig) {
        int i = low, j = mid + 1;
        for (int k = low; k <= hig; k++)
            aux[k] = arr[k];
        for (int k = low; k <= hig; k++) {
            if (i > mid) // 左已无
                arr[k] = aux[j++];
            else if (j > hig) // 右已无
                arr[k] = aux[i++];
            else if (aux[i].compareTo(aux[j]) < 0) // 左小等
                arr[k] = aux[i++];
            else                   // 右小
                arr[k] = aux[j++];
        }
    }

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        int n = scan.nextInt();
        Integer[] arr = new Integer[n];
        for (int i = 0; i < n; i++)
            arr[i] = scan.nextInt();
        sort(arr);
        for (int i = 0; i < n; i++)
            System.out.printf("%d ", arr[i]);
    }
}
