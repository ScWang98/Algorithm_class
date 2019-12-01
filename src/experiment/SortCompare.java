package experiment;

import java.util.Random;
import java.util.Scanner;

import MySort.myInsertionSort.*;
import MySort.mymergeSort.*;
import MySort.myquickSort.*;

public class SortCompare {
    public static void main(String[] args){
        Scanner cin = new Scanner(System.in);
        Random random = new Random();

        System.out.println("Please enter the number of data to be sorted.");
        int num = cin.nextInt();

        System.out.println("1. Insertion Sort. \n" +
                           "2. Top-down Mergesort. \n" +
                           "3. Bottom-up Mergesort. \n" +
                           "4. Random Quicksort. \n" +
                           "5. Quicksort with Dijkstra 3-way Partition.");
        System.out.println("Please select a way to sort.");
        int way = cin.nextInt();



        Integer[] arr = new Integer[num];

        long sum_time = 0;
        long sum_memory = 0;
        for (int i = 0; i < 10; i++){
            Runtime.getRuntime().gc();
            long time_start = System.nanoTime();
            long memory_start = Runtime.getRuntime().totalMemory()-Runtime.getRuntime().freeMemory();

            for(int j = 0; j < num; j++){
                arr[j] = random.nextInt(1000000);
            }


            switch (way){
                case 1 : {
                    myInsertionSort.sort(arr);
                    break;
                }
                case 2 : {
                    mymergeSort_topdown.sort(arr);
                    break;
                }
                case 3 : {
                    mymergeSort_downtop.sort(arr);
                    break;
                }
                case 4 : {
                    RandomizeQuickSort.sort(arr);
                    break;
                }
                case 5 : {
                    Dijsktra3wayQuickSort.sort(arr);
                    break;
                }
                default:
                    System.out.println("Please enter a valid number.");
            }

            long time_end = System.nanoTime();
            long memory_end = Runtime.getRuntime().totalMemory()-Runtime.getRuntime().freeMemory();

            System.out.printf("Time: %dns \t\tMemory: %d\n", time_end-time_start, memory_end-memory_start);

            sum_time += time_end-time_start;
            sum_memory += memory_end-memory_start;
        }
        System.out.printf("Average time: %.3fns \nAverage memory: %.3fB", sum_time/10.0, sum_memory/10.0);



    }
}
