package experiment.hxl.Sorts;

import java.util.Random;
import java.util.Scanner;
public class QuickD3PSort {
    public static void sort(Comparable[] a){
        randArry(a);
        sort(a, 0, a.length-1);
    }
    private static void sort(Comparable[] a, int lo, int hi){
        if(hi <= lo)  return;
        int lt = lo;
        int i = lo+1;
        int gt = hi;
        Comparable v = a[lo];
        while(i <= gt){
            int cmp = a[i].compareTo(v);
            if(cmp < 0){
                exch(a, lt++, i++);
            }
            else if(cmp > 0){
                exch(a, i, gt--);
            }
            else{
                i++;
            }
        }
        sort(a, lo, lt-1);
        sort(a, gt+1, hi);
    }
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
        System.out.println();
    }
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
            a[i] = r.nextInt(a.length)+1;}}}
