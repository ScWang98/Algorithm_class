package test;

import java.util.Random;
import java.util.Scanner;
import java.lang.Runtime;


import experiment.hxl.Sorts.QuickD3PSort;

public class test {
    public static void main(String[] args){
        Scanner cin = new Scanner(System.in);
        Random random = new Random();

        for(int i = 0; i < 10; i++){
            System.out.println("Number: ");
            int num = cin.nextInt();
            Integer[] arr = new Integer[num];
            for(int j = 0; j < num; j++){
                arr[j] = random.nextInt(10000000);
            }
            Runtime.getRuntime().gc();
            long m1 = Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory();
            System.out.println("Max memory: " +
                    (Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory()));


//            QuickSort.sort(arr);
            QuickD3PSort.sort(arr);

            System.out.println("Max memory: " +
                    (Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory()));
            long m2 = Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory();

            System.out.println("Memory: "+ (m2 - m1));

        }
    }
}
