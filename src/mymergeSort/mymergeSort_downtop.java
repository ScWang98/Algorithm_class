package mymergeSort;
import java.lang.Math;
import java.util.Scanner;

public class mymergeSort_downtop {
    private static Comparable[] aux;
    public static void mergeSort(Comparable[] arr){
        aux = new Comparable[arr.length];
        for(int i = 1; i < arr.length; i *= 2){
            for(int j = 0; j < arr.length; j += i*2){
                mymergeSort_topdown.merge(arr, j, j + i - 1, Math.min(j+i+i-1, arr.length-1));
            }
        }
    }

    public static void main(String[] args){
        Scanner scan = new Scanner(System.in);
        int n = scan.nextInt();
        Integer[] arr = new Integer[n];
        for(int i = 0; i < n; i++)
            arr[i] = scan.nextInt();
        mergeSort(arr);
        for(int i = 0; i < n; i++)
            System.out.printf("%d ", arr[i]);
    }
}
