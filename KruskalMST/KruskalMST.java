import java.util.*;

public class KruskalMST
{
    int V;
    LinkedList<Edge> mst = new LinkedList<Edge>();
    int[] componentIDS;
    
    public KruskalMST(WeightedGraph g)
    {
        this.V = g.V;
        this.componentIDS = new int[V];
        
        for(int i=0;i<V;i++)
        {
            componentIDS[i] = i;
        }
        LinkedList<Edge> allEdges = g.allEdges;
        Collections.sort(allEdges);
        
        for(Edge e: allEdges)
        {
            int sourceRoot = findRoot(e.source);
            int destRoot = findRoot(e.destination);
            
            if(sourceRoot != destRoot)
            {
                union(sourceRoot, destRoot);
                mst.add(e);
            }
            if(mst.size() == V - 1)
                break;
        }
    }
    private void union(int v, int w)
    {
        componentIDS[v] = w;
    }
    private int findRoot(int v)
    {
        if(componentIDS[v] != v)
        {
            componentIDS[v] = findRoot(componentIDS[v]);
        }
        return componentIDS[v];
    }
    
    public Iterable<Edge> getMST()
    {
        return mst;
    }
}