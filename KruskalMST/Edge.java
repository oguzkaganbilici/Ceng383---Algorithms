import java.util.*;

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
    
    public int compareTo(Edge e)
    {
        if(this.weight < e.weight) return -1;
        if(this.weight > e.weight) return 1;
        return 0;
    }
}