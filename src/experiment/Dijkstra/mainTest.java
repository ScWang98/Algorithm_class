package experiment.Dijkstra;


import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.*;

class Point {
    private int x;
    private int y;

    public Point(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }
}

public class mainTest {
    private static ArrayList<Point> points;

    private static DirectedEdge genEdge(int from, int to) {
        int dx = points.get(from).getX() - points.get(to).getX();
        int dy = points.get(from).getY() - points.get(to).getY();
        return new DirectedEdge(from, to, Math.sqrt(dx * dx + dy * dy));
    }

    public static void main(String[] args) throws FileNotFoundException {
        File file = new File("C:\\Users\\scwan\\Desktop\\usa.txt");
        Scanner scanner = new Scanner(new BufferedInputStream(new FileInputStream(file)), "utf-8");
        int V = scanner.nextInt();
        int E = scanner.nextInt();

        points = new ArrayList<Point>(V);
        for (int i = 0; i < V; ++i) {
            scanner.nextInt();
            points.add(new Point(scanner.nextInt(), scanner.nextInt()));
        }

        // init the map
        DrawMap map1 = new DrawMap(1500, 850, "Dijkstra");
        DrawMap map2 = new DrawMap(1500, 850, "Dijkstra with first improvement");
        DrawMap map3 = new DrawMap(1500, 850, "Dijkstra with first and second improvement");
        DrawMap map4 = new DrawMap(1500, 850, "Dijkstra with all improvement");

//        System.out.println(scanner.nextInt() + " " + scanner.nextInt());

        EdgeWeightedDigraph G = new EdgeWeightedDigraph(V);

        int from, to;
        for (int i = 0; i < E; ++i) {
            from = scanner.nextInt();
            to = scanner.nextInt();
            G.addEdge(genEdge(from, to));
            G.addEdge(genEdge(to, from));

            map1.addMapLine(points.get(from).getX(), points.get(from).getY(), points.get(to).getX(), points.get(to).getY());
            map2.addMapLine(points.get(from).getX(), points.get(from).getY(), points.get(to).getX(), points.get(to).getY());
            map3.addMapLine(points.get(from).getX(), points.get(from).getY(), points.get(to).getX(), points.get(to).getY());
            map4.addMapLine(points.get(from).getX(), points.get(from).getY(), points.get(to).getX(), points.get(to).getY());
        }
        map1.repaintMap();
        map2.repaintMap();
        map3.repaintMap();
        map4.repaintMap();


        Scanner cin = new Scanner(System.in);


        System.out.println("Please enter two integers:");
        int p1 = cin.nextInt();
        int p2 = cin.nextInt();

        long time_start1 = System.nanoTime();
        DijkstraSP dijkstraSP1 = new DijkstraSP(G, p1, p2, map1, points, 0);
        long time_end1 = System.nanoTime();

        long time_start2 = System.nanoTime();
        DijkstraSP dijkstraSP2 = new DijkstraSP(G, p1, p2, map2, points, 1);
        long time_end2 = System.nanoTime();
//
        long time_start3 = System.nanoTime();
        DijkstraSP dijkstraSP3 = new DijkstraSP(G, p1, p2, map3, points, 2);
        long time_end3 = System.nanoTime();

        long time_start4 = System.nanoTime();
        DijkstraSP dijkstraSP4 = new DijkstraSP(G, p1, p2, map4, points, 3);
        long time_end4 = System.nanoTime();

        double time1 = (time_end1 - time_start1) / 1000000.0;
        double time2 = (time_end2 - time_start2) / 1000000.0;
        double time3 = (time_end3 - time_start3) / 1000000.0;
        double time4 = (time_end4 - time_start4) / 1000000.0;
        System.out.println("Dijkstra without improvement:                  " + time1 + "ms");
        System.out.println("Dijkstra with first improvement:               " + time2 + "ms");
        System.out.println("Dijkstra with first and second improvement:    " + time3 + "ms");
        System.out.println("Dijkstra with all improvement:                 " + time4 + "ms");


        // 打印路径
        ArrayList<DirectedEdge> path1 = dijkstraSP1.pathTo(p2);
        Collections.reverse(path1);
        System.out.print("Path:\n" + p1 + "  ->  " + path1.get(0).from());
        map1.addPathLine(points.get(p1).getX(), points.get(p1).getY(), points.get(path1.get(0).from()).getX(), points.get(path1.get(0).from()).getY());
        for (DirectedEdge edge : path1) {
            System.out.print("  ->  " + edge.to());
            map1.addPathLine(points.get(edge.from()).getX(), points.get(edge.from()).getY(), points.get(edge.to()).getX(), points.get(edge.to()).getY());
        }
        map1.repaintPath();
        System.out.println();
        // 打印距离
        System.out.println("Distance:\n" + dijkstraSP1.distTo(p2) + "\n");


        ArrayList<DirectedEdge> path2 = dijkstraSP2.pathTo(p2);
        Collections.reverse(path2);
        map2.addPathLine(points.get(p1).getX(), points.get(p1).getY(), points.get(path2.get(0).from()).getX(), points.get(path2.get(0).from()).getY());
        for (DirectedEdge edge : path2) {
            map2.addPathLine(points.get(edge.from()).getX(), points.get(edge.from()).getY(), points.get(edge.to()).getX(), points.get(edge.to()).getY());
        }
        map2.repaintPath();


        ArrayList<DirectedEdge> path3 = dijkstraSP3.pathTo(p2);
        Collections.reverse(path3);
        map3.addPathLine(points.get(p1).getX(), points.get(p1).getY(), points.get(path3.get(0).from()).getX(), points.get(path3.get(0).from()).getY());
        for (DirectedEdge edge : path3) {
            map3.addPathLine(points.get(edge.from()).getX(), points.get(edge.from()).getY(), points.get(edge.to()).getX(), points.get(edge.to()).getY());
        }
        map3.repaintPath();


        ArrayList<DirectedEdge> path4 = dijkstraSP4.pathTo(p2);
        Collections.reverse(path4);
        map4.addPathLine(points.get(p1).getX(), points.get(p1).getY(), points.get(path4.get(0).from()).getX(), points.get(path4.get(0).from()).getY());
        for (DirectedEdge edge : path4) {
            map4.addPathLine(points.get(edge.from()).getX(), points.get(edge.from()).getY(), points.get(edge.to()).getX(), points.get(edge.to()).getY());
        }
        map4.repaintPath();


    }
}
