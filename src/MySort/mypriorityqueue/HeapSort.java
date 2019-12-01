package MySort.mypriorityqueue;

public class HeapSort {
    public static void sort(Comparable[] arr){
        int N = arr.length;

        for(int i = N/2; i > 0; i--){
            sink(arr, i, N);
        }
        for(int i = N; i > 0; i--){
            swap(arr, i, 1);
            sink(arr, 1, i-1);
        }
    }

    private static void sink(Comparable[] arr, int i, int N){
        while (i*2 <= N){
            int max = maxson(arr, i, N);
            if(arr[i-1].compareTo(arr[max-1]) < 0)
                swap(arr, i, max);
            i = max;
        }
    }
    private static void swap(Comparable[] arr, int i, int j){
        Comparable t = arr[i-1];
        arr[i-1] = arr[j-1];
        arr[j-1] = t;
    }
    private static int maxson(Comparable[] arr, int i, int N){
        // Make sure i have a son when use this function
        if(i*2 + 1 <= N){
            if(arr[i*2-1].compareTo(arr[i*2]) >= 0)
                return i * 2;
            else
                return i * 2 + 1;
        }
        else
            return i * 2;
    }
}
