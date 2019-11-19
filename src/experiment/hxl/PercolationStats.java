package experiment.hxl;
import java.util.Scanner;
import java.util.*;

public class PercolationStats{
    private int gridLength;
    private double[] Result;
    private double resultmean;
    private double resultStddev;
    private double resultconfidenceLo;
    private double resultconfidenceHi;
    public PercolationStats(int n, int T){
        gridLength=n;
        Result=new double[T];
        for (int i=0;i<T;i++){
            Result[i]=Getresult(gridLength);
        }
        resultmean=average(Result);
        resultStddev=dev(Result);
        double diff=(1.96*resultStddev)/Math.sqrt(T);
        resultconfidenceLo=resultmean-diff;
        resultconfidenceHi=resultmean+diff;
    }
    private double Getresult(int n){
        int opennum=0;
        Random r=new Random();
        Percolation percolation=new Percolation(n);
        while (!percolation.percolates()){
            int row=r.nextInt(n) + 1;
            int col=r.nextInt(n) + 1;
            if (!percolation.isOpen(row, col)){
                percolation.open(row, col);
                opennum++; }}
        return (double) opennum/(n*n);}
    public double average(double[] array){
        double temp=0;
        double sum=0;
        for (int i=0;i<array.length;i++){
            sum=sum+array[i];
        }
        temp=sum/array.length;
        return temp;
    }
    public double dev(double[] array){
        double temp=0;
        double sum=0;
        for(int i=0;i<array.length;i++){
            sum+=(array[i]-average(array))*(array[i]-average(array));
        }
        temp=Math.sqrt(sum/array.length);
        return temp;
    }
    public double mean(){
        return resultmean;
    }
    public double stddev(){
        return resultStddev;
    }
    public double confidenceLo(){
        return resultconfidenceLo;
    }
    public double confidenceHi(){
        return resultconfidenceHi;
    }
    public static void main(String[] args){
        Scanner sc=new Scanner(System.in);
        System.out.println("边长n和次数t：");
        int N=sc.nextInt();
        int T=sc.nextInt();
        PercolationStats percolations = new PercolationStats(N, T);
        System.out.println("平均值："+percolations.mean());
        System.out.println("标准差："+percolations.stddev());
        System.out.println("置信度为95%的置信区间为："
                + percolations.confidenceLo()+", "+percolations.confidenceHi());
    }
}
