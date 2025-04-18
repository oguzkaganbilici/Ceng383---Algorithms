/******************************************************************************

Welcome to GDB Online.
GDB online is an online compiler and debugger tool for C, C++, Python, Java, PHP, Ruby, Perl,
C#, OCaml, VB, Swift, Pascal, Fortran, Haskell, Objective-C, Assembly, HTML, CSS, JS, SQLite, Prolog.
Code, Compile, Run and Debug online from anywhere in world.

*******************************************************************************/
import java.util.*;

public class WeightedGraph
{
    public int V;
    private LinkedList<Edge>[] neighbors;
    
    public WeightedGraph(int V)
    {
        this.V = V;
        neighbors = new LinkedList[V];
        for(int i=0;i<V;i++)
        {
            neighbors[i] = new LinkedList<Edge>();
        }
    }
    
    public void addEdge(Edge e)
    {
        neighbors[e.source].add(e);
        neighbors[e.destination].add(e);
    }
    public LinkedList<Edge>  getNeighbors(int v)
    {
        return neighbors[v];
    }
    
    public static void main (String[] args) {
        WeightedGraph g = new WeightedGraph(8);
        g.addEdge(new Edge(0, 7, 0.16));
        g.addEdge(new Edge(0, 4, 0.38));
        g.addEdge(new Edge(0, 2, 0.26));
        g.addEdge(new Edge(0, 6, 0.58));
        g.addEdge(new Edge(1, 7, 0.19));
        g.addEdge(new Edge(1, 7, 0.19));
        g.addEdge(new Edge(1, 5, 0.32));
        g.addEdge(new Edge(1, 3, 0.29));
        g.addEdge(new Edge(1, 2, 0.36));
        g.addEdge(new Edge(2, 3, 0.17));
        g.addEdge(new Edge(2, 6, 0.4));
        g.addEdge(new Edge(2, 7, 0.34));
        g.addEdge(new Edge(3, 6, 0.52));
        g.addEdge(new Edge(4, 6, 0.93));
        g.addEdge(new Edge(4, 7, 0.37));
        g.addEdge(new Edge(4, 5, 0.35));
        g.addEdge(new Edge(5, 7, 0.28));
        
        PrimMST prim_mst = new PrimMST(g);
        double total = 0.0;
        for(Edge e: prim_mst.getPath())
        {
            total += e.weight;
            System.out.println(e.source + " -> " + e.destination + ": " + e.weight);
        }
        System.out.println("Total cost: " + total);
    }
}