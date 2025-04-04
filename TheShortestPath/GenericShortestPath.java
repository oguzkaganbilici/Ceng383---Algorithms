import java.util.*;

class GenericShortestPath
{
    private int V;
    private double[] distTo;
    private DiEdge[] edgeTo;
    
    
    public GenericShortestPath(WeightedDiGraph g, int start, Iterable<DiEdge> order)
    {
        this.V = g.V;
        distTo = new double[V];
        edgeTo = new DiEdge[V];
        Arrays.fill(distTo, Double.POSITIVE_INFINITY);
        distTo[start] = 0.0;
        
        for(DiEdge e: order)
        {
            relax(e);
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
        }
    }
    public double getDistTo(int v)
    {
        return distTo[v];
    }
}