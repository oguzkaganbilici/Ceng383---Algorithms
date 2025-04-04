import java.util.*;

public class Dijkstra
{
    private int V;
    private double[] distTo;
    private DiEdge[] edgeTo;
    private PriorityQueue<Double> pq;
    
    public Dijkstra(WeightedDiGraph g, int s)
    {
        this.V = g.V;
        distTo = new double[V];
        edgeTo = new DiEdge[V];
        pq = new PriorityQueue<Double>(V);
        
        Arrays.fill(distTo, Double.POSITIVE_INFINITY);
        distTo[s] = 0.0;
        
        pq.add(s, 0.0);
        
        while(!pq.isEmpty())
        {
            int v = pq.poll();
            for(DiEdge e: g.getNeighbors(v))
            {
                relax(e);
            }
        }
        
    }
    
    private void relax(DiEdge e)
    {
        int v = e.source;
        int w = e.destination;
        
        if(distTo[w] > distTo[v] + e.weight)
        {
            distTo[w] = distTo[v] + e.weight;
            edgeTo[w] = e;
            
            if(pq.contains(w)) pq.decreaseKey(w, distTo[w]);
            else{
                pq.add(w, distTo[w]);
            }
            
        }
    }
}