import java.util.*;


class ShortestPath
{
	private double[] distTo;
	private DiEdge[] edgeTo;
    private int V;
    
	public ShortestPath(WeightedDiGraph graph, int source)
	{
		this.V = graph.V;
		distTo = new double[V];
		edgeTo = new DiEdge[V];
		Arrays.fill(distTo, Double.POSITIVE_INFINITY);
		distTo[source] = 0.0;

		for(int i=0; i<V; i++)
		{
			for(DiEdge edge: graph.getNeighbors(i))
			{
				relax(edge);
			}
		}
	}

	private void relax(DiEdge e)
	{
		int v = e.source, w = e.destination;
		if(distTo[w] > distTo[v] + e.weight)
		{
			distTo[w] = distTo[v] + e.weight;
			edgeTo[w] = e;
		}
	}
	public double distTo(int v) {
		return distTo[v];
	}

}