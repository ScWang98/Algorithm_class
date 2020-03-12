package experiment.Dijkstra;

import java.util.ArrayList;

public class DijkstraSP {
    private DirectedEdge[] edgeTo;
    private double[] distTo;
    private IndexMultiwayMinPQ<Double> pq;
    private int s;
    private int d;
    private ArrayList<Point> points;

    public DijkstraSP(EdgeWeightedDigraph G, int s, int d, DrawMap map, ArrayList<Point> points, int improvement) {
        this.s = s;
        this.d = d;
        this.points = points;
        edgeTo = new DirectedEdge[G.V()];
        distTo = new double[G.V()];
        if (improvement == 3)
            pq = new IndexMultiwayMinPQ<Double>(3, G.V());
        else
            pq = new IndexMultiwayMinPQ<Double>(2, G.V());
        for (int i = 0; i < G.V(); ++i) {
            distTo[i] = Double.POSITIVE_INFINITY;
        }

        // 置空path和relaxed
        map.clearPath();
        map.clearRelaxed();

        // 初始化edgeTo
        edgeTo[s] = new DirectedEdge(s, s, 0.0);

        distTo[s] = 0.0;
        pq.insert(s, 0.0);
        if (improvement == 0) {
            while (!pq.isEmpty()) {
                int t = pq.delMin();
                relax(G, t);
                DirectedEdge edge = edgeTo[t];
                map.addRelaxedLine(points.get(edge.from()).getX(), points.get(edge.from()).getY(), points.get(edge.to()).getX(), points.get(edge.to()).getY());
            }
        } else if (improvement == 1) {
            while (!pq.isEmpty()) {
                int t = pq.delMin();
                if (t == d) // 想法 1
                    break;
                relax(G, t);
                DirectedEdge edge = edgeTo[t];
                map.addRelaxedLine(points.get(edge.from()).getX(), points.get(edge.from()).getY(), points.get(edge.to()).getX(), points.get(edge.to()).getY());
            }
        } else {
            while (!pq.isEmpty()) {
                int t = pq.delMin();
                if (t == d) // 想法 1
                    break;
                relax_using2(G, t);
                DirectedEdge edge = edgeTo[t];
                map.addRelaxedLine(points.get(edge.from()).getX(), points.get(edge.from()).getY(), points.get(edge.to()).getX(), points.get(edge.to()).getY());
            }
        }

        map.repaintRelaxed();
    }

    private double distance(int from, int to) {
        int dx = points.get(from).getX() - points.get(to).getX();
        int dy = points.get(from).getY() - points.get(to).getY();
        return Math.sqrt(dx * dx + dy * dy);
    }

    private void relax(EdgeWeightedDigraph G, int v) {
        for (DirectedEdge e : G.adj(v)) {
            int w = e.to();
            if (distTo[w] > distTo[v] + e.weight()) {
                distTo[w] = distTo[v] + e.weight();
                edgeTo[w] = e;
                if (pq.contains(w))
                    pq.changeKey(w, distTo[w]);
                else
                    pq.insert(w, distTo[w]);
            }
        }
    }

    private void relax_using2(EdgeWeightedDigraph G, int v) {
        for (DirectedEdge e : G.adj(v)) {
            int w = e.to();
            if (distTo[w] > distTo[v] - distance(v, d) + e.weight() + distance(w, d)) {
                distTo[w] = distTo[v] - distance(v, d) + e.weight() + distance(w, d);
                edgeTo[w] = e;
                if (pq.contains(w))
                    pq.changeKey(w, distTo[w]);
                else
                    pq.insert(w, distTo[w]);
            }
        }
    }

    public double distTo(int v) {
        return distTo[v];
    }

    public boolean hasPathTo(int v) {
        return distTo[v] < Double.POSITIVE_INFINITY;
    }

    public ArrayList<DirectedEdge> pathTo(int v) {
        if (!hasPathTo(v))
            return null;

        ArrayList<DirectedEdge> path = new ArrayList<DirectedEdge>();
        for (int to = v; edgeTo[to].from() != s; to = edgeTo[to].from()) {
            path.add(edgeTo[to]);
        }
        return path;
    }

}
