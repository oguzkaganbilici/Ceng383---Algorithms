/******************************************************************************

Welcome to GDB Online.
GDB online is an online compiler and debugger tool for C, C++, Python, Java, PHP, Ruby, Perl,
C#, OCaml, VB, Swift, Pascal, Fortran, Haskell, Objective-C, Assembly, HTML, CSS, JS, SQLite, Prolog.
Code, Compile, Run and Debug online from anywhere in world.

*******************************************************************************/
import java.util.*;

public class WeightedDiGraph
{
    public int V;
    private LinkedList<DiEdge>[] neighbors;
    
    public WeightedDiGraph(int V)
    {
        this.V = V;
        neighbors = new LinkedList[V];
        for(int i=0;i<V;i++)
        {
            neighbors[i] = new LinkedList<DiEdge>();
        }
    }
    
    public void addEdge(DiEdge e)
    {
        neighbors[e.source].add(e);
    }
    
    public LinkedList<DiEdge> getNeighbors(int w)
    {
        return neighbors[w];
    }
    //generic icin
    public LinkedList<DiEdge> getAllEdges()
    {
        LinkedList<DiEdge> allEdges = new LinkedList<DiEdge>();
        for(int i=0;i<V;i++)
        {
            allEdges.addAll(neighbors[i]);
        }
        return allEdges;
    }
    
    public static void main (String[] args) {
        WeightedDiGraph g = new WeightedDiGraph(5);
        g.addEdge(new DiEdge(0, 1, 3.1));
        g.addEdge(new DiEdge(1, 2, 1.3));
        g.addEdge(new DiEdge(2, 3, 4.4));
        g.addEdge(new DiEdge(0, 3, 17.2));  // Daha uzun yol

        ShortestPath sp = new ShortestPath(g, 0);
        
        LinkedList<DiEdge> allEdges = g.getAllEdges();
        GenericShortestPath gp = new GenericShortestPath(g, 0, allEdges );

        System.out.println("0'dan 3'e en kısa mesafe: " + sp.distTo(3));  // 4.4
        System.out.println("0'dan 3'e en kısa mesafe: " + gp.getDistTo(3));  // 4.4
    }
}