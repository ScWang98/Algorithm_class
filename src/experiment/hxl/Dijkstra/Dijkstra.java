package experiment.hxl.Dijkstra;

import java.util.*;
import java.awt.Color;
import experiment.hxl.Dijkstra.IndexPQ;
import java.io.*;
class MyPoint {
    private final static double SCALEX =0.14;
    private final static double SCALEY =0.18;
    private int x;
    private int y;
    public MyPoint(int x, int y) { this.x = x; this.y = y; }
    public double distanceTo(MyPoint p) {
        double dx = this.x - p.x;
        double dy = this.y - p.y;
        return Math.sqrt(dx*dx + dy*dy);
    }
    public void draw() {
        UI.fly(x * SCALEX, y * SCALEY);
        UI.spot(2);
    }
    public void drawTo(MyPoint q) {
        MyPoint p = this;
        UI.fly(p.x * SCALEX, p.y * SCALEY);
        UI.go (q.x * SCALEX, q.y * SCALEY);
    }
}
class Node {
    int v;
    Node next;
    Node(int v, Node next) { this.v = v; this.next = next; }
}
class AdjListIterator{
    private Node x;
    AdjListIterator(Node x)  { this.x = x; }
    public boolean hasNext() { return x != null; }
    public int next() {
        int v = x.v;
        x = x.next;
        return v;
    }
}
public class Dijkstra {
    static Scanner scanner;
    static int V;
    static int E;
    static Node[]  adj;
    static MyPoint[] points;
    static double INFINITY = Double.MAX_VALUE;
    static double EPSILON  = 0.000001;
    static double[] dist;
    static int[] pred;
    public static void main(String[] args) throws FileNotFoundException {
        File file = new File("C:\\Users\\scwan\\Desktop\\usa.txt");
        Scanner scanner = new Scanner(new BufferedInputStream(new FileInputStream(file) ),"utf-8");
        UI.createUI(1400, 800);
        V = Integer.parseInt(scanner.next());
        E = Integer.parseInt(scanner.next());
        points = new MyPoint[V];
        for (int i = 0; i < V; i++) {
            int v = Integer.parseInt(scanner.next());
            int x = Integer.parseInt(scanner.next());
            int y = Integer.parseInt(scanner.next());
            points[v] = new MyPoint(x, y);
        }
        adj = new Node[V];
        for (int i = 0; i < E; i++) {
            int v = Integer.parseInt(scanner.next());
            int w = Integer.parseInt(scanner.next());
            adj[v] = new Node(w, adj[v]);
            adj[w] = new Node(v, adj[w]);
        }
        for (int v = 0; v < V; v++) {
            points[v].draw();
            for (Node x = adj[v]; x != null; x = x.next) {
                int w = x.v;
                points[v].drawTo(points[w]);
            }
        }
        for(int ii=1;ii<=3;ii++){
            System.out.println("请输入两个顶点：");
            int s = new Scanner(System.in).nextInt();
            int d = new Scanner(System.in).nextInt();
            dist = new double[V];
            pred = new int[V];
            for (int v = 0; v < V; v++) dist[v] = INFINITY;
            for (int v = 0; v < V; v++) pred[v] = -1;
            IndexPQ pq = new IndexPQ(V);
            for (int v = 0; v < V; v++) pq.insert(v, dist[v]);
            dist[s] = 0.0;
            pred[s] = s;
            pq.change(s, dist[s]);
            while (!pq.isEmpty()) {
                int v = pq.delMin();
                if (pred[v] == -1) break;
                AdjListIterator i = new AdjListIterator(adj[v]) ;
                while (i.hasNext()) {
                    int w = i.next();
                    if (dist[v] + points[v].distanceTo(points[w])  < dist[w] - EPSILON) {
                        dist[w] = dist[v] +  points[v].distanceTo(points[w]);
                        pq.change(w, dist[w]);
                        pred[w] = v;
                    }
                }
            }
            if (pred[d] == -1)   System.out.println(d + "不能到达 " + s);
            System.out.print(d+"到"+s+"的最短路径：");
            for (int v = d; v != s; v = pred[v])
                System.out.print(v + "->");
            System.out.println(s);
            System.out.println(d+"到"+s+"的最短路径的距离为："+dist[d]);
            if(ii==1)UI.setColor(Color.black);
            if(ii==2)UI.setColor(Color.red);
            if(ii==3)UI.setColor(Color.blue);
            for (int v = d; v != s; v = pred[v])
                points[v].drawTo(points[pred[v]]);
            UI.render();
        }
    }
}
