import java.util.*;

class KruskalMST
{
	LinkedList<Edge> mst = new LinkedList<Edge>();
	int V;
	int[] componentIDS;

	KruskalMST(WeightedGraph g)
	{
		this.V = g.Vertex;
		this.componentIDS = new int[V];

		for(int i=0; i<V; i++)
		{
			componentIDS[i] = i;
		}
		LinkedList<Edge> edges = g.allEdges;
		Collections.sort(edges);

		for(Edge e: edges)
		{
			int srcRoot = findRoot(e.source);
			int destRoot =findRoot(e.destination);

			if(srcRoot != destRoot)
			{
				union(srcRoot, destRoot);
				mst.add(e);
			}

			if(mst.size() == V - 1)
				break;
		}
	}

	private void union(int root1, int root2)
	{
        componentIDS[root1] = root2;
	}
	
    private int findRoot(int v)
    {
        if (componentIDS[v] != v)
        {
            componentIDS[v] = findRoot(componentIDS[v]); // PATH COMPRESSION
        }
        return componentIDS[v];
    }

	
	public Iterable<Edge> getPath()
	{
		return mst;
	}
}