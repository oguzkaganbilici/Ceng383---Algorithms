/******************************************************************************

Welcome to GDB Online.
GDB online is an online compiler and debugger tool for C, C++, Python, Java, PHP, Ruby, Perl,
C#, OCaml, VB, Swift, Pascal, Fortran, Haskell, Objective-C, Assembly, HTML, CSS, JS, SQLite, Prolog.
Code, Compile, Run and Debug online from anywhere in world.

*******************************************************************************/
import java.util.*;


public class KruskalExample
{

	public static void main (String[] args) {
		WeightedGraph graph = new WeightedGraph(6);
		graph.addEdge(new Edge(0, 1, 4));
		graph.addEdge(new Edge(0, 2, 5));
		graph.addEdge(new Edge(1, 2, 2));
		graph.addEdge(new Edge(1, 3, 3));
		graph.addEdge(new Edge(2, 3, 1));
		graph.addEdge(new Edge(2, 4, 6));
		graph.addEdge(new Edge(3, 4, 7));
		graph.addEdge(new Edge(3, 5, 3));
		graph.addEdge(new Edge(4, 5, 5));

		KruskalMST newMst = new KruskalMST(graph);
		int totalCost = 0;
		for(Edge e: newMst.getPath())
		{
			totalCost += e.weight;
			System.out.println(e.source + " -> " + e.destination + ": " + e.weight);
		}
		System.out.println("totalCost: " + totalCost);
	}
}

