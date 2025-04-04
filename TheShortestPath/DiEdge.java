import java.util.*;

class DiEdge implements Comparable<DiEdge>
{
    int source;
    int destination;
    double weight;
    
    
    DiEdge(int s, int d, double w)
    {
        this.source = s;
        this.destination = d;
        this.weight = w;
    }
    
    public int compareTo(DiEdge e)
    {
        if(this.weight < e.weight) return -1;
        if(this.weight > e.weight) return 1;
        return 0;
    }
}