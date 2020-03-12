package experiment.percolation;

import java.util.Random;
import java.lang.Math;
import java.util.Scanner;

public class PercolationStats {
    private int N;
    private int T;
    private double[] xt;
    private double mean;
    private double stddev;
    private double confidenceLo;
    private double confidenceHi;

    public PercolationStats(int N, int T) { // perform T independent computational experiments on an N-by-N grid
        this.N = N;
        this.T = T;
        xt = new double[T];
        for (int i = 0; i < T; i++) {
            xt[i] = run(N);
        }

        calculate();
    }

    private double run(int N) {
        Percolation p = new Percolation(N);
        Random r = new Random();
        int x, y;
        while (true) {
            x = r.nextInt(N) + 1;
            y = r.nextInt(N) + 1;
            if (!p.isOpen(x, y)) {
                p.open(x, y);
            }
            if (p.percolates())
                break;
        }
        return p.getOpennum() / (N * N * 1.0);
    }

    private void calculate() {
        // mean
        double sum = 0;
        for (int i = 0; i < T; i++)
            sum += xt[i];
        mean = sum / (T * 1.0);

        stddev = Math.sqrt(deviation());

        double interval = 1.96 * stddev / Math.sqrt(T * 1.0);
        confidenceLo = mean - interval;

        confidenceHi = mean + interval;
    }

    private double deviation() {
        double sum = 0;
        for (int i = 0; i < T; i++)
            sum += Math.pow(xt[i] - mean, 2.0);
        return sum / (T - 1);
    }

    public double mean() { // sample mean of percolation threshold
        return mean;
    }

    public double stddev() { // sample standard deviation of percolation threshold
        return stddev;
    }

    public double confidenceLo() { // returns lower bound of the 95% confidence interval
        return confidenceLo;
    }

    public double confidenceHi() { // returns upper bound of the 95% confidence interval
        return confidenceHi;
    }

    public static void main(String[] args) { // test client, described below
        System.out.println("Please input N and T:");
        Scanner scan = new Scanner(System.in);
        int N = scan.nextInt();
        int T = scan.nextInt();

        PercolationStats ps = new PercolationStats(N, T);
        System.out.printf("Example values after creating PercolationStats(%d, %d)\n", N, T);
        System.out.printf("mean()                =%.16f\n", ps.mean());
        System.out.printf("stddev()              =%.16f\n", ps.stddev());
        System.out.printf("confidenceLow()       =%.16f\n", ps.confidenceLo());
        System.out.printf("confidenceHigh()      =%.16f\n", ps.confidenceHi());
    }
}