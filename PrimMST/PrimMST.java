import java.util.*;


public class PrimMST
{
    private boolean[] visited;
    private Queue<Edge> mst;
    private PriorityQueue<Edge> pq;
    
    public PrimMST(WeightedGraph g)
    {
        pq = new PriorityQueue<Edge>();
        mst = new LinkedList<Edge>();
        visited = new boolean[g.V];
        
        visit(g, 0);
        while(!pq.isEmpty() && mst.size() < g.V - 1)
        {
            Edge minEdge = pq.poll();
            int dest = minEdge.endTo(minEdge.source);
            
            if(visited[minEdge.source] && visited[dest]) continue;
            mst.add(minEdge);
            
            if(!visited[minEdge.source]) visit(g, minEdge.source);
            if(!visited[dest]) visit(g, dest);
        }
    }
    private void visit(WeightedGraph g, int v)
    {
        visited[v] = true;
        for(Edge e: g.getNeighbors(v))
        {
            if(!visited[e.endTo(v)] )
            {
                pq.add(e);
            }
        }
    }
    public Iterable<Edge> getPath()
    {
        return mst;
    }
}