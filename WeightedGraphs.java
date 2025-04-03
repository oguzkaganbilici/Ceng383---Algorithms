/******************************************************************************

Welcome to GDB Online.
GDB online is an online compiler and debugger tool for C, C++, Python, Java, PHP, Ruby, Perl,
C#, OCaml, VB, Swift, Pascal, Fortran, Haskell, Objective-C, Assembly, HTML, CSS, JS, SQLite, Prolog.
Code, Compile, Run and Debug online from anywhere in world.

*******************************************************************************/
import java.util.*;


public class WeightedGraphs
{
	public int Vertex; // vertex sayisini tutmak icin
	private LinkedList<Edge>[] neighbors;
	public LinkedList<Edge> allEdges; // kenar listesi


	public WeightedGraphs(int V)
	{
		this.Vertex = V;
		neighbors = new LinkedList[Vertex];

		for(int i=0; i<Vertex; i++)
		{
			neighbors[i] = new LinkedList<Edge>();
		}

		allEdges = new LinkedList<Edge>();
	}

	public void addEdge(Edge e)
	{
		neighbors[e.source].add(e);
		neighbors[e.destination].add(e);
		allEdges.add(e);
	}
	public LinkedList<Edge> getNeighbors(int v)
	{
		return neighbors[v];
	}

	public void printGraph()
	{
		for(int i=0; i<Vertex; i++)
		{
			System.out.print("Node: " + i + " -> ");
			for(Edge w: neighbors[i])
			{
				System.out.print("Dest: " + w.end2(i) + ", Weight: " + w.weight+" ");
			}
			System.out.println();
		}
	}

	class Edge implements Comparable<Edge>
	{
		public int source;
		public int destination;
		public double weight;

		public Edge(int s, int d, double w)
		{
			this.source = s;
			this.destination = d;
			this.weight = w;
		}
		public int compareTo(Edge e2)
		{
			return Double.compare(this.weight, e2.weight);
		}
		public int end2(int w)
		{
			if(this.source == w) return this.destination;
			return this.source;
		}
	}


	public static class KruskalMST
	{
		LinkedList<Edge> mst = new LinkedList<Edge>();
		int V;
		int componentIDS[];

		public KruskalMST(WeightedGraphs grp)
		{
			this.V = grp.Vertex;
			this.componentIDS = new int[V];

			for(int i = 0; i<V; i++)
			{
				componentIDS[i] = i;
			}
			LinkedList<Edge> edges = grp.allEdges;
			Collections.sort(edges);

			for (Edge e: edges)
			{
				int v = e.source;
				int w = e.destination;

				if(componentIDS[v] != componentIDS[w])
				{
					union(v, w);
					mst.add(e);
				}

				if(mst.size() == V - 1)
					break;
			}
		}

		private void union(int v, int w)
		{
			int vroot = findRoot(v);
			int wroot = findRoot(w);

			componentIDS[vroot] = wroot;
		}

		private int findRoot(int v)
		{
			while(componentIDS[v] != v)
			{
				v = componentIDS[v];
			}
			return v;
		}

		public Iterable<Edge> getEdges() {
			return mst;
		}
	}

	public static class PrimMST
	{
		private boolean[] visited;
		private Queue<Edge> mst;
		private PriorityQueue<Edge> pq;

		PrimMST(WeightedGraphs G)
		{
			pq = new PriorityQueue<Edge>();
			mst = new LinkedList<Edge>();
			visited = new boolean[G.Vertex];

			visit(G, 0);

			while(!pq.isEmpty() && mst.size() < G.Vertex - 1)
			{
				Edge minEdge = pq.poll();// Edge minEdge = pq.delMin();
				int dest = minEdge.end2(minEdge.source);
				if(visited[minEdge.source] && visited[dest]) continue;
				mst.add(minEdge);
				if(!visited[minEdge.source]) visit(G, minEdge.source);
				if(!visited[dest]) visit(G, dest);
			}
		}
		private void visit(WeightedGraphs G, int v)
		{
			visited[v] = true;
			for(Edge e: G.getNeighbors(v))
			{
				if(!visited[e.end2(v)])
				{
					pq.add(e);//pq.insert(e);
				}
			}
		}
		
		public Iterable<Edge> getEdges()
		{
		    return mst;
		}
	}

	public static void main (String[] args) {
		WeightedGraphs g = new WeightedGraphs(8);
		g.addEdge(g.new Edge(1, 2, 0.36));
		g.addEdge(g.new Edge(1,3,0.29));
		g.addEdge(g.new Edge(1,5,0.32));
		g.addEdge(g.new Edge(1,7,0.19));
		g.addEdge(g.new Edge(2,3,0.17));
		g.addEdge(g.new Edge(2,0,0.26));
		g.addEdge(g.new Edge(2,6,0.40));
		g.addEdge(g.new Edge(2,7,0.34));
		g.addEdge(g.new Edge(3,6,0.52));
		g.addEdge(g.new Edge(4,0,0.38));
		g.addEdge(g.new Edge(4,5,0.35));
		g.addEdge(g.new Edge(4,6,0.93));
		g.addEdge(g.new Edge(4,7,0.37));
		g.addEdge(g.new Edge(5,7,0.28));
		g.addEdge(g.new Edge(6,0,0.58));
		g.addEdge(g.new Edge(7,0,0.16));

		//g.printGraph();
		/*
		KruskalMST mst = new KruskalMST(g);
		double total = 0;
		for(Edge e: mst.getEdges())
		{
			total += e.weight;
			System.out.println(e.source + " -> " + e.destination + ": " + e.weight);
		}
		System.out.println("Total mst weight: " + total);
        */
        PrimMST prim = new PrimMST(g);
        double total = 0;
        for(Edge e: prim.getEdges())
        {
            total += e.weight;
            System.out.println(e.source + " -> " + e.destination + ": " + e.weight);
        }
        System.out.println("Total MST weight: " + total);
	}
}

