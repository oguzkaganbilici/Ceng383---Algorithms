#include<iostream>
#include<vector>
#include <algorithm>
#include <queue>
#include <functional>
using namespace std;

struct Edge {
    int u, v, w;
};

class DSU {
    vector<int> parent;
    vector<int> rank;

public:
    DSU(int N) {
        parent.resize(N);
        rank.resize(N, 0);
        for (int i = 0; i < N; i++) parent[i] = i;
    }

    int find(int x) {
        if (parent[x] == x) return x;
        return parent[x] = find(parent[x]);
    }

    bool unite(int a, int b) {
        int rootA = find(a);
        int rootB = find(b);

        if (rootA == rootB) return false;

        if (rootA != rootB) {
            if (rank[rootA] < rank[rootB]) parent[rootA] = rootB;
            else if (rank[rootB] < rank[rootA]) parent[rootB] = rootA;
            else {
                parent[rootB] = rootA;
                rank[rootA]++;
            }
            return true;
        }
    }
};

int kruskalMST(int V, vector<Edge> edges) {
    sort(edges.begin(), edges.end(), [](const Edge& a, const Edge& b)
        {return a.w < b.w; });
    DSU ds(V);
    vector<Edge> mst;
    int mst_weight = 0;
    int taken_edge = 0;

    for (const auto & edge: edges) {
        if (taken_edge == V - 1) break;
        int u = edge.u;
        int v = edge.v;
        int w = edge.w;

        if (ds.find(u) != ds.find(v)) {
            ds.unite(u, v);
            mst.push_back(edge);
            mst_weight += w;
            taken_edge++;
        }
    }
    return mst_weight;
}
int primMST(int V, const vector<vector<pair<int, int>>> & adj, int start) {
    priority_queue<pair<int, int>, vector<pair<int, int>>, greater<pair<int, int>>> pq;
    int mst_weight = 0;
    int taken = 0;
    vector<bool> inMST(V, false);
    pq.push({0, start});
    while (!pq.empty() && taken < V) {
        int weight = pq.top().first;
        int u = pq.top().second;
        pq.pop();

        if (inMST[u]) continue;
        inMST[u] = true;
        mst_weight += weight;
        taken++;
        for (auto & edge: adj[u]) {
            int v = edge.first;
            int w = edge.second;
            if (!inMST[v]) pq.push({w, v});
        }
    }
    return mst_weight;
}