import java.util.*;

class Edge implements Comparable<Edge>
{
	int source;
	int destination;
	int weight;

	Edge(int s, int d, int w)
	{
		this.source = s;
		this.destination = d;
		this.weight = w;
	}
	public int compareTo(Edge other)
	{
		if(this.weight < other.weight) return -1;
		if(this.weight > other.weight) return 1;
		return 0;
	}
	public int endTo(int w)
	{
		if(this.source == w ) return this.destination;
		return this.source;
	}
}