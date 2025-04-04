import java.util.*;

class WeightedGraph
{
	public int Vertex;
	public LinkedList<Edge> allEdges;

	public WeightedGraph(int V)
	{
		this.Vertex = V;

		allEdges = new LinkedList<Edge>();
	}

	public void addEdge(Edge e)
	{
		allEdges.add(e);
	}

}
