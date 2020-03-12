package experiment.Dijkstra;

import java.util.ArrayList;
import java.util.Arrays;

public class EdgeWeightedDigraph {
    private final int V;
    private int E;
    private ArrayList<DirectedEdge>[] adj;

    public EdgeWeightedDigraph(int V) {
        this.V = V;
        this.E = 0;
        int[] a = new int[3];
        adj = (ArrayList<DirectedEdge>[]) new ArrayList[V];
        for (int v = 0; v < V; v++) {
            adj[v] = new ArrayList<DirectedEdge>();
        }
    }

    public int V() {
        return this.V;
    }

    public int E() {
        return this.E;
    }

    public void addEdge(DirectedEdge e) {
        adj[e.from()].add(e);
        E++;
    }

    public ArrayList<DirectedEdge> adj(int v) {
        return adj[v];
    }

    public ArrayList<DirectedEdge> edges() {
        ArrayList<DirectedEdge> t = new ArrayList<DirectedEdge>();
        for (ArrayList<DirectedEdge> e : adj) {
            t.addAll(e);
        }
        return t;
    }

    @Override
    public String toString() {
        return "EdgeWeightedDigraph{" +
                "V=" + V +
                ", E=" + E +
                ", adj=" + Arrays.toString(adj) +
                '}';
    }
}
