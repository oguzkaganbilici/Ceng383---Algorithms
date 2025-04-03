/******************************************************************************

Welcome to GDB Online.
GDB online is an online compiler and debugger tool for C, C++, Python, Java, PHP, Ruby, Perl,
C#, OCaml, VB, Swift, Pascal, Fortran, Haskell, Objective-C, Assembly, HTML, CSS, JS, SQLite, Prolog.
Code, Compile, Run and Debug online from anywhere in world.

*******************************************************************************/
import java.util.*;

public class Graph{
    private int V;
    private LinkedList<Integer>[] neighbors;
    
    public Graph(int V)
    {
        this.V = V;
        neighbors = new LinkedList[V];
        
        
        for(int i = 0; i < V; i++)
        {
            neighbors[i] = new LinkedList<>();
        }
    }
    
    
    public void addEdge(int startVertex, int sourceVertex)
    {
        neighbors[startVertex].add(sourceVertex);
    }
    
    public LinkedList<Integer> getNeighbors(int vertex)
    {
        return neighbors[vertex];
    }
    
    private void DFSHelper(int node, boolean[] visited)
    {
        visited[node] = true;
        System.out.print(node + " ");
        
        for(int neigh: getNeighbors(node))
        {
            if(!visited[neigh])
            {
                DFSHelper(neigh, visited);
            }
        }
    }
    
    public void DFS(int startVertex)
    {
        boolean[] visited = new boolean[V];
        System.out.print("DFS Traversal: ");
        DFSHelper(startVertex, visited);
        System.out.println();
        
    }
    
    
    public void DFS_stack(int startVertex)
    {
        boolean[] visited = new boolean[V];
        Stack<Integer> stack = new Stack<Integer>();
        
        stack.push(startVertex);
        visited[startVertex] = true;
        
        System.out.print("DFS Traversal(stack): ");
        while(!stack.isEmpty())
        {
            int node = stack.pop();
            System.out.print(node + " ");
            
            for(int neighs: getNeighbors(node))
            {
                if(!visited[neighs])
                {
                    stack.push(neighs);
                    visited[neighs] = true;
                }
            }
        }
    }
    
    public void BFS(int startVertex)
    {
        boolean visited[] = new boolean[V];
        LinkedList<Integer> que = new LinkedList<Integer>();
        
        visited[startVertex] = true;
        que.add(startVertex);
        
        System.out.print("BFS Traversal: ");
        while(!que.isEmpty())
        {
            int node = que.remove();
            System.out.print(node + "->");
            
            for(int w: neighbors[node])
            {
                if(!visited[w])
                {
                    que.add(w);
                    visited[w] = true;
                }
            }
        }
    }
    public void multiBFS(List<Integer> sources)
    {
        int distTo[] = new int[V];
        boolean visited[] = new boolean[V];
        int parents[] = new int[V];
        LinkedList<Integer> que = new LinkedList<Integer>();
        
        for(int source: sources)
        {
            que.add(source);
            visited[source] = true;
            distTo[source] = 0;
            parents[source] = source;
        }
        
        while(!que.isEmpty())
        {
            int node = que.remove();
            
            for(int w: neighbors[node])
            {
                if(!visited[w])
                {
                    que.add(w);
                    visited[w] = true;
                    distTo[w] = distTo[node] + 1;
                    parents[w] = node;
                }
            }
        }
        
        for(int i=0;i<V;i++)
        {
            if(distTo[i] != -1)
            {
                System.out.print("Min path to "+ i + " is: ");
                printPath(i, parents);
                System.out.println("(Hops: " + distTo[i] + ")");
            }
        }
    }
    
    private void printPath(int node, int[] parent)
    {
        if(parent[node] == node)
        {
            System.out.print(node);
            return;
        }
        printPath(parent[node], parent);
        System.out.print(" → " + node);

    }
    
     private void postOrderDFSHelper(int v, boolean[] visited, Stack<Integer> st)
    {
        visited[v] = true;
        
        for(int i: neighbors[v])
        {
            if(!visited[i])
            {
                postOrderDFSHelper(i, visited, st);
            }
        }
        st.push(v);
    }
    
    public void postOrderDFS()
    {
        boolean[] visited = new boolean[V];
        Stack<Integer> stack = new Stack<Integer>();
        
        for(int i = 0; i < V; i++)
        {
            if(!visited[i])
            {
                postOrderDFSHelper(i, visited, stack);
            }
        }
        
        while(!stack.isEmpty())
        {
            System.out.print(stack.pop() + " ");
        }
    }
    
    
    private boolean isCycleHelper(int v, boolean[] visited, boolean[] currentPath)
    {
        if(currentPath[v]) return true;
        if(visited[v]) return false;
        
        visited[v] = true;
        currentPath[v] = true;
        
        for(int w: neighbors[v])
        {
            boolean isCycle = isCycleHelper(w, visited, currentPath);
            if(isCycle) return true;
        }
        
        currentPath[v] = false;
        return false;
    }
    
    public boolean isCycling()
    {
        boolean[] visited = new boolean[V];
        boolean[] currPath = new boolean[V];
        
        for(int i = 0; i<V; i++)
        {
            if(!visited[i])
            {
                boolean isCycle = isCycleHelper(i, visited, currPath);
                if(isCycle) return true;
            }
        }
        
        return false;
    }
    
    public static void main (String[] args) {
        
        Graph g = new Graph(13);
        
        g.addEdge(0, 1);
        g.addEdge(0, 5);
        g.addEdge(2, 0);
        g.addEdge(2, 3);
        g.addEdge(3, 2);
        g.addEdge(3, 5);
        g.addEdge(4, 2);
        g.addEdge(4, 3);
        g.addEdge(5, 4);
        g.addEdge(6, 0);
        g.addEdge(6, 4);
        g.addEdge(6, 8);
        g.addEdge(6, 9);
        g.addEdge(7, 6);
        g.addEdge(7, 9);
        g.addEdge(8, 6);
        g.addEdge(9, 10);
        g.addEdge(9, 11);
        g.addEdge(10, 12);
        g.addEdge(11, 12);
        g.addEdge(12, 9);
        
        
        g.DFS(0);
        g.DFS_stack(0);
        System.out.println();
        
        g.BFS(0);
        
        List<Integer> sources = Arrays.asList(1, 7, 10);
        g.multiBFS(sources);
        System.out.println(g.isCycling());
        
        /*
        Graph g = new Graph(7);
        
        g.addEdge(0, 1);
        g.addEdge(0, 5);
        g.addEdge(0, 2);
        g.addEdge(1, 4);
        g.addEdge(3, 4);
        g.addEdge(3, 5);
        g.addEdge(3, 2);
        g.addEdge(3, 6);
        g.addEdge(5, 2);
        g.addEdge(6, 4);
        g.addEdge(6, 0);
        g.postOrderDFS();
        System.out.println(g.isCycling());
        */
        }
    
}