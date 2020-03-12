package MySort.mypriorityqueue;
import java.lang.Comparable;

public class PriorityQueue {
    // Max Priority Queue
    private Comparable[] pq;
    private int N;

    public PriorityQueue(int maxN){
        pq = new Comparable[maxN];
        N = 0;
    }

    public boolean empty(){
        return N == 0;
    }

    public int size(){
        return N;
    }

    public void push(Comparable t){
        pq[++N] = t;
        rise(N);
    }

    public Comparable top(){
        return pq[1];
    }

    public Comparable pop(){
        Comparable t = pq[1];
        pq[1] = pq[N--];
        sink(1);
        return t;
    }

    private void rise(int i){
        while (i > 1 && pq[i / 2].compareTo(pq[i]) < 0){
            swap(i, i/2);
            i /= 2;
        }
    }
    private void sink(int i){
        while (i*2 <= N){
            int max = maxson(i);
            if(pq[i].compareTo(pq[max]) < 0)
                swap(i, max);
            i = max;
        }
    }
    private void swap(int i, int j){
        Comparable t = pq[i];
        pq[i] = pq[j];
        pq[j] = t;
    }
    private int maxson(int i){
        // Make sure i have a son when use this function
        if(i*2 + 1 <= N){
            if(pq[i*2].compareTo(pq[i*2+1]) >= 0)
                return i * 2;
            else
                return i * 2 + 1;
        }
        else
            return i * 2;
    }
}
