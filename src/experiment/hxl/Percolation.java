package experiment.hxl;
import java.util.Random;
import java.util.Scanner;
import java.util.*;
public class Percolation{
    private WeightedQuickUnionUF UF;
    private int N;
    public int opennum;
    private boolean[] a;
    public Percolation(int n){
        N=n;
        opennum=0;
        UF=new WeightedQuickUnionUF(n*n+2);
        a=new boolean[n*n];
//        UF.
    }
    public boolean percolates(){
        return UF.connected(N*N, N*N+1);
    }
    public boolean isOpen(int i,int j){
        return a[(i-1)*N+j-1];
    }
    public boolean isFull(int i,int j){
        return UF.connected((i-1)*N+j-1, N*N);
    }
    public void open(int row,int col){
        int self=(row-1)*N+col-1;
        if(a[self]){
            return;
        }
        a[self]=true;
        if(row==1){
            UF.union(N*N, self);
        }
        if(row==N){
            UF.union(N*N+1, self);
        }
        if(row>1){
            int around=(row-2)*N+col-1;
            if(a[around]){
                UF.union(around,self);
            }
        }
        if(row<N){
            int around=row*N+col-1;
            if(a[around]){
                UF.union(around, self);
            }
        }
        if(col>1){
            int around=(row-1)*N+col-2;
            if(a[around]){
                UF.union(around, self);
            }
        }
        if(col<N){
            int around=(row-1)*N+col;
            if(a[around]){
                UF.union(around, self);
            }
        }
        opennum++;
    }
    public static void main(String[] args) {
        System.out.println("请输入n*n渗透系统的n值：");
        Scanner sc=new Scanner(System.in);
        int n=sc.nextInt();
        Random r=new Random();
        Percolation pcl=new Percolation(n);
        while (true) {
            int row=r.nextInt(n)+1;
            int col=r.nextInt(n)+1;
            pcl.open(row, col);
            if(pcl.percolates())
                break;
        }
        System.out.println("系统渗透时open点个数："+pcl.opennum);
    }
}
