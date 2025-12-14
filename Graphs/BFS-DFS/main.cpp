#include<iostream>
#include<vector>
#include<queue>
#include<list>
#include<stack>
using namespace std;

const int V = 7;

vector<vector<int>> adjList;

void initializeGraph() {
    adjList.resize(V);

    adjList[0].push_back(1); adjList[1].push_back(0);
    adjList[0].push_back(3); adjList[3].push_back(0);
    adjList[1].push_back(2); adjList[2].push_back(1);
    adjList[1].push_back(4); adjList[4].push_back(1);
    adjList[2].push_back(5); adjList[5].push_back(2);
    adjList[3].push_back(4); adjList[4].push_back(3);
    adjList[4].push_back(5); adjList[5].push_back(4);
    adjList[5].push_back(6); adjList[6].push_back(5);
}
void DFS(int startNode, vector<bool>& visited) {
    visited[startNode] = true;
    cout<<startNode<<" ";

    for (int u: adjList[startNode]) {
        if (!visited[u]) {
            DFS(u, visited);
        }
    }
}

void runDFS(int startNode) {
    vector<bool> visited(V, false);
    DFS(startNode, visited);
}

void DFS_iterative(int startNode) {
    vector<bool> visited(V, false);
    stack<int> s;

    s.push(startNode);
    while (!s.empty()) {
        int u = s.top();
        s.pop();

        if (visited[u]) continue;
        visited[u] = true;
        cout<< u << " ";
        for (int i = adjList[u].size() -1; i>=0; i--) {
            int v = adjList[u][i];
            if (!visited[v]) s.push(v);
        }
    }
}

void BFS(queue<int>& q, vector<bool> visited) {
    if (q.empty()) return;

    int u = q.front();
    q.pop();
    cout<< u << " ";

    for (int v: adjList[u]) {
        if (!visited[v]) {
            visited[v] = true;
            q.push(v);
        }
    }

    BFS(q, visited);
}

void BFS_run(int startNode) {
    queue<int> q;
    vector<bool> visited(V, false);
    visited[startNode] = true;
    q.push(startNode);
    BFS(q, visited);
}

void BFS_iterative(int startNode) {
    vector<bool> visited(V, false);
    queue<int> q;

    visited[startNode] = true;
    q.push(startNode);

    while (!q.empty()) {
        int u = q.front();
        q.pop();

        cout<<u<<" ";
        for (int v: adjList[u]) {
            if (!visited[v]) {
                visited[v] = true;
                q.push(v);
            }
        }
    }
}

int main() {
    initializeGraph();
    cout<<"DFS: ";
    runDFS(0);

    cout<<"\nDFS iterative: ";
    DFS_iterative(0);

    cout<<"\nBFS: ";
    BFS_run(0);

    cout<<"\nBFS iterative: ";
    BFS_iterative(0);
}