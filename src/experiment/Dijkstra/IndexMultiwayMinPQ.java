package experiment.Dijkstra;

import java.util.Scanner;

public class IndexMultiwayMinPQ<Key extends Comparable<Key>> {
    private int way;
    private int maxN;
    private int N;
    private int[] pq;
    private int[] qp;
    private Key[] keys;

    public IndexMultiwayMinPQ(int way, int maxN) {
        this.way = way;
        this.maxN = maxN;
        pq = new int[maxN];
        qp = new int[maxN];
        keys = (Key[]) new Comparable[maxN];
        for (int i = 0; i < maxN; ++i)
            qp[i] = -1;
    }

    public void insert(int k, Key key) {
        ++N;
        qp[k] = N - 1;
        pq[N - 1] = k;
        keys[k] = key;
        swim(N - 1);
        sink(N - 1);
    }

    public void changeKey(int k, Key key) {
        keys[k] = key;
        swim(qp[k]);
        sink(qp[k]);
    }

    public boolean contains(int k) {
        return qp[k] != -1;
    }

    public void delete(int k) {
        if (!contains(k)) {
            int index = qp[k];
            exch(index, N - 1);
            N--;
            sink(index);
            keys[index] = null;
            qp[k] = -1;
        }
    }

    public Key minKey() {
        return keys[pq[0]];
    }

    public int minIndex() {
        return pq[1];
    }

    public int delMin() {
        int t = pq[0];
        exch(0, N - 1);
        N--;
        sink(0);

        qp[t] = -1;
        keys[t] = null;

        return t;

    }

    public boolean isEmpty() {
        return N == 0;
    }

    public int size() {
        return N;
    }

    private void swim(int k) {
        while (k > 0 && greater((k - 1) / way, k)) {
            exch((k - 1) / way, k);
            k = (k - 1) / way;
        }
    }

    private void sink(int k) {
        while (hasChild(k)) {
            int j = minChild(k);
            if (!greater(k, j))
                break;
            exch(k, j);
            k = j;
        }

    }

    private boolean hasChild(int i) {
        return N > i * way + 1;
    }

    private int minChild(int f) {
        int minkey = f * way + 1;
        for (int i = f * way + 2; i < N && i <= f * way + way; ++i) {
            if (less(i, minkey)) {
                minkey = i;
            }
        }
        return minkey;
    }

    private void exch(int i, int j) {
        int t = pq[i];
        pq[i] = pq[j];
        pq[j] = t;

        qp[pq[i]] = i;
        qp[pq[j]] = j;
    }

    private boolean greater(int i, int j) {
        return keys[pq[i]].compareTo(keys[pq[j]]) > 0;
    }

    private boolean less(int i, int j) {
        return keys[pq[i]].compareTo(keys[pq[j]]) < 0;
    }


    public static void main(String[] args) {
        Scanner cin = new Scanner(System.in);
//        int n = cin.nextInt();
        int n = 10;
        IndexMultiwayMinPQ<Integer> pq = new IndexMultiwayMinPQ<>(3, 10);
        int[] t = {345, 675, 1345, 827, 2345, 34756, 1354, 73, 12345, 76245};
        for (int i = 0; i < n; ++i) {
//            pq.insert(i, cin.nextInt());
            pq.insert(i, t[i]);
        }
        for (int i = 0; i < n; ++i) {
            System.out.println(pq.minKey());
            pq.delMin();
        }
    }

}
