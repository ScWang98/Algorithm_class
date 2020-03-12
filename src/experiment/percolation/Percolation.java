package experiment.percolation;

import java.lang.IndexOutOfBoundsException;
import java.lang.IllegalArgumentException;
import java.util.Scanner;
import java.util.Random;

public class Percolation {
    private WeightedQuickUnionUF uf;
    private int N;
    private boolean[][] map;
    private int opennum;

    public Percolation(int N) { // create N-by-N grid, with all sites blocked
        if (N < 0)
            throw new IllegalArgumentException();
        this.N = N;
        map = new boolean[N + 1][N + 1];
        for (int i = 0; i <= N; i++) {
            for (int j = 0; j <= N; j++) {
                map[i][j] = false;
            }
        }
        uf = new WeightedQuickUnionUF(N * N + 2); // +2为上节点和下节点
        for (int i = 0; i < N; i++) {
            uf.union(0, i + 1);
            uf.union(N * N - i, N * N + 1);
        }
        opennum = 0;
    }

    public void open(int i, int j) { // open site (row i, column j) if it is not already
        if (i < 1 || i > N || j < 1 || j > N)
            throw new IndexOutOfBoundsException();
        map[i][j] = true;
        if (i != 1 && map[i - 1][j])
            uf.union((i - 1) * N + j, (i - 2) * N + j);
        if (i != N && map[i + 1][j])
            uf.union((i - 1) * N + j, i * N + j);
        if (j != 1 && map[i][j - 1])
            uf.union((i - 1) * N + j, (i - 1) * N + j - 1);
        if (j != N && map[i][j + 1])
            uf.union((i - 1) * N + j, (i - 1) * N + j + 1);

        opennum++;
    }

    public boolean isOpen(int i, int j) { // is site (row i, column j) open?
        return map[i][j];
    }

    public boolean isFull(int i, int j) { // is site (row i, column j) full?
        return uf.connected(0, (i - 1) * N + j);
    }

    public boolean percolates() { // does the system percolate?
        return uf.connected(0, N * N + 1);
    }

    public void printOpen() {
        for (int i = 1; i <= N; i++) {
            for (int j = 1; j <= N; j++) {
                System.out.print(isOpen(i, j) ? '1' : '0');
            }
            System.out.println();
        }
    }

    public void printFull() {
        for (int i = 1; i <= N; i++) {
            for (int j = 1; j <= N; j++) {
                System.out.print(isFull(i, j) ? '1' : '0');
            }
            System.out.println();
        }
    }

    public int getOpennum() {
        return opennum;
    }

    public static void main(String[] args) { // test client, optional
        System.out.println("Please input N:");
        Scanner scan = new Scanner(System.in);
        int N = scan.nextInt();
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
        System.out.println(p.getOpennum());
    }
}
