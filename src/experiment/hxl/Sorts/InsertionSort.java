package experiment.hxl.Sorts;

import java.util.Scanner;
import java.util.Random;
import java.lang.Comparable;
public class InsertionSort{
    public static void sort (Comparable[] a){
        randArry(a);
        for(int i = 1; i < a.length; i++){
            for(int j = i; j > 0 && less(a[j], a[j-1]); j--){
                exch(a, j, j-1);}}}
    private static boolean less(Comparable v, Comparable w){
        return v.compareTo(w) < 0;
    }
    private static void exch(Comparable[] a, int i, int j){
        Comparable t = a[i];
        a[i] = a[j];
        a[j] = t;
    }
    public static void show(Comparable[] a){
        for(Comparable i : a){
            System.out.print(i + " ");
        }
        System.out.println();}
    public static boolean isSorted(Comparable[] a){
        for(int i = 1; i < a.length; i++ ){
            if(less(a[i], a[i-1]))
                return false;
        }
        return true;
    }
    private static void randArry(Comparable a[]){
        Random r = new Random();
        for(int i = 0; i < a.length; i++){
            a[i] = r.nextInt(a.length)+1;	}}}
