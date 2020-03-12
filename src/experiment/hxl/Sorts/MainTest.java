package experiment.hxl.Sorts;

import java.text.DecimalFormat;
import java.util.Scanner;
import java.lang.Runtime;
public class MainTest {
    public static void main(String[] args){
        Scanner input = new Scanner(System.in);
        while(true){
            double sum1 = 0, sum2 = 0;
            System.out.println("请输入需要排序的数据的数量：");
            int n = input.nextInt();
            System.out.println("请选择排序方式：");
            System.out.println("1是插入排序,2自顶向下归并排序,3自底向上归并排序,4随即快速排序,5是Dijkstra 3-路划分快速排序");
            int m = input.nextInt();
            System.out.println("排序结果");
            Comparable<Integer> [] a = new Integer [n];

            for(int j=0; j<11; j++){
                Runtime.getRuntime().gc();
                long starTime = System.nanoTime();//返回时间纳秒
                long m1 = Runtime.getRuntime().totalMemory()-Runtime.getRuntime().freeMemory();
                if(m==1)
                {
                    InsertionSort.sort(a);

                }
                else if(m==2)
                {
                    MergeTDSort.sort(a);

                }
                else if(m==3)
                {
                    MergeBUSort.sort(a);

                }
                else if(m==4)
                {
                    RQuickSort.sort(a);

                }
                else if(m==5)
                {
                    QuickD3PSort.sort(a);

                }
                else{
                    System.out.println("错误！");
                    break;
                }
                long endTime = System.nanoTime();
                long m2 = Runtime.getRuntime().totalMemory()-Runtime.getRuntime().freeMemory();
                double Time= (double) (endTime - starTime)/1000;
                double M = (double) (m2 - m1)/1024 ;
                if(j == 0)
                    continue;
                DecimalFormat df = new DecimalFormat("0.000");
                System.out.println("时间:" + df.format(Time)+ "us"+"    内存:" + df.format(M) + "kb");
                sum1 = sum1 + Time;
                sum2 = sum2 + M;
            }
            DecimalFormat df = new DecimalFormat("0.000");
            System.out.println("平均时间:" + df.format(sum1/10) + "us");
            System.out.println("平均空间:" + df.format(sum2/10) + "kb");}}
}
