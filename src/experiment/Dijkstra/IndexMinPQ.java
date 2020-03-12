package experiment.Dijkstra;

import java.util.Scanner;

public class IndexMinPQ<Key extends Comparable<Key>> {
    private int maxN;
    private int N;
    private int[] pq;
    private int[] qp;
    private Key[] keys;

    public IndexMinPQ(int maxN){
        this.maxN = maxN;
        pq = new int[maxN + 1];
        qp = new int[maxN + 1];
        keys = (Key[])new Comparable[maxN + 1];
        for(int i = 0; i <= maxN; ++i)
            qp[i] = -1;
    }

    public void insert(int k, Key key){
        ++N;
        qp[k] = N;
        pq[N] = k;
        keys[k] = key;
        swim(N);
        sink(N);
    }

    public void changeKey(int k, Key key){
        keys[k] = key;
        swim(qp[k]);
        sink(qp[k]);
    }

    public boolean contains(int k){
        return qp[k] != -1;
    }

    public void delete(int k){
        if(!contains(k)){
            int index = qp[k];
            exch(index, N);
            N--;
            sink(index);
            keys[index] = null;
            qp[k] = -1;

        }
    }

    public Key minKey(){
        return keys[pq[1]];
    }

    public int minIndex(){
        return pq[1];
    }

    public int delMin(){
        int t = pq[1];
        exch(1, N);
        N--;
        sink(1);

        qp[t] = -1;
        keys[t] = null;

//        System.out.println("\nTree");
//        for(int i = 1; i < N; ++i){
//            System.out.print(" " + keys[pq[i]]);
//            if(i == 1 || i == 3 || i == 7)
//                System.out.println();
//        }
//        System.out.println("Tree\n");

        return t;

    }

    public boolean isEmpty(){
        return N == 0;
    }

    public int size(){
        return N;
    }

    private void swim(int k){
        while(k > 1 && greater(k/2, k)){
            exch(k/2, k);
            k /= 2;
        }
    }

    private void sink(int k){
        while(k*2 <= N){
            int j = k * 2;
            if(j < N && greater(j, j+1))
                j++;
            if(!greater(k, j))
                break;
            exch(k, j);
            k = j;
        }

    }

    private void exch(int i, int j){
        int t = pq[i];
        pq[i] = pq[j];
        pq[j] = t;

        qp[pq[i]] = i;
        qp[pq[j]] = j;
    }

    private boolean greater(int i, int j){
//        System.out.println(keys[pq[i]] + " compared to " + keys[pq[j]]);
        return keys[pq[i]].compareTo(keys[pq[j]]) > 0;
    }

    private boolean less(int i, int j){
        return keys[pq[i]].compareTo(keys[pq[j]]) < 0;
    }


    public static void main(String[] args){
        Scanner cin = new Scanner(System.in);
        int n = cin.nextInt();
//        int n = 10;
        IndexMinPQ<Integer> pq = new IndexMinPQ<Integer>(n);
        int[] t = {0, 345, 675, 1345, 827, 2345, 34756, 1354, 73, 12345, 76245};
        for(int i = 0; i < n; ++i){
            pq.insert(i+1, cin.nextInt());
//            pq.insert(i+1, t[i+1]);
        }
        for(int i = 0; i < n; ++i){
            System.out.println(pq.minKey());
            pq.delMin();
        }
    }

}
