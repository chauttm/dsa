import edu.princeton.cs.algs4.DirectedEdge;
import edu.princeton.cs.algs4.EdgeWeightedDigraph;
import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.Stack;

import java.util.Arrays;

public class TSP
{
    private final EdgeWeightedDigraph G;
    private double[] distTo;          // distTo[v] = distance  of path 0 -> v
    private DirectedEdge[] edgeTo;    // edgeTo[v] = last edge on 0->v path

    private double bestCycleLength = Double.MAX_VALUE;
    private DirectedEdge[] bestEdgeTo;


    public TSP(EdgeWeightedDigraph G) {
        this.G = G;
        distTo = new double[G.V()];
        edgeTo = new DirectedEdge[G.V()];
        distTo[0] = 0;
        dfs(0, G.V());
    }

    private void dfs(int v, int l) {
        if (l == 1) {
            verifySolution(v);
            return;
        }
        for (DirectedEdge edge: G.adj(v)) {
            int w = edge.to();
            if (edgeTo[w] == null && w != 0 && bestCycleLength > distTo[v] + edge.weight()) {
                edgeTo[w] = edge;
                distTo[w] = distTo[v] + edge.weight();
                dfs(w, l-1);
                edgeTo[w] = null;
            }
        }
    }

    private void verifySolution(int lastVertex) {
        for (DirectedEdge edge: G.adj(lastVertex)) {
            if (edge.to() == 0) {
                edgeTo[0] = edge;
                double length = distTo[lastVertex] + edge.weight();
                if (length < bestCycleLength) {
                    bestCycleLength = length;
                    bestEdgeTo = Arrays.copyOf(edgeTo, G.V());
                }
            }
        }
    }

    public Iterable<DirectedEdge> getRoute() {
        Stack<DirectedEdge> path = new Stack<>();
        if (bestEdgeTo != null) {
            for (DirectedEdge e = bestEdgeTo[0]; ; e = bestEdgeTo[e.from()]) {
                path.push(e);
                if (e.from() == 0) break;
            }
        }
        return path;
    }

    private void output() {
        Iterable<DirectedEdge> route = getRoute();
        for (DirectedEdge e: route) {
            System.out.print(e.from());
        }
        System.out.println("\n----" + bestCycleLength);
    }

    public static void main(String[] args) {
        In in = new In("tinyEWD.txt");
        EdgeWeightedDigraph G = new EdgeWeightedDigraph(in);
        System.out.println(G);
        TSP tsp = new TSP(G);
        tsp.output();
    }
}
