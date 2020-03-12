package experiment.percolation;

public class WeightedQuickUnionUF {
    private int[] id;
    private int[] sz;
    private int count;

    public WeightedQuickUnionUF(int n){
        id = new int[n];
        sz = new int[n];
        for(int i = 0; i < n; i++){
            id[i] = i;
            sz[i] = 1;
        }
        count = n;
    }

    public int count(){
        return this.count;
    }

    public int root(int p){
        while(p != id[p]){
            id[p] = id[id[p]]; // path-compression
            p = id[p];
        }
        return p;
    }

    public void union(int p, int q){
        p = root(p);
        q = root(q);
        // 选小树挂到大树
        if(sz[p] < sz[q]) {
            id[p] = q;
            sz[p] += sz[p];
        }
        else{
            id[q] = p;
            sz[q] += sz[q];
        }
        count--;
    }

    public boolean connected(int p, int q){
        return root(p) == root(q);
    }
}
