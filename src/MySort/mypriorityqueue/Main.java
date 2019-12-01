package MySort.mypriorityqueue;

import java.util.Arrays;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
//        System.out.println("Hello,world!");
        Scanner scan = new Scanner(System.in);
        int c = scan.nextInt();
        Integer[] arr = new Integer[c];
//        PriorityQueue p = new PriorityQueue(20);
        for(int i = 0; i < c; i++){
//            p.push(scan.nextInt());
            arr[i] = scan.nextInt();
        }

//        for(int i = 0; i < c; i++){
//            System.out.println(p.pop());
//        }
//        HeapSort.sort(arr);
//        QuickSort.sort(arr);
        System.out.print(Arrays.toString(arr));

    }
}
