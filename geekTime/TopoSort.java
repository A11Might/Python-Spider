package geektime;

import java.util.LinkedList;

/**
 * 拓扑排序
 */

 public class TopoSort {
     private int v;
     private LinkedList<Integer>[] adj;

     public TopoSort(int v) {
         this.v = v;
         adj = new LinkedList[v];
         for (int i = 0; i < v; ++i) {
            adj[i] = new LinkedList<Integer>();
         }
     }

     public void addEdge(int s, int t) { // s先于t，边s->t
         adj[s].add(t);
     }

     public void topoSortByKahn() {
         int[] inDegree = new int[v];
         for (int i = 0; i < adj.length; ++i) {
             for (int j = 0; j < adj[i].size(); ++j) {
                 ++inDegree[adj[i].get(j)];
             }
         }
         LinkedList<Integer> zeroInDegreeQ = new LinkedList<>();
         for (int i = 0; i < inDegree.length; ++i) {
             if (inDegree[i] == 0) {
                 zeroInDegreeQ.add(i);
             }
         }
         while (!zeroInDegreeQ.isEmpty()) {
             int i = zeroInDegreeQ.poll();
             System.out.println("->" + i);
             for (int j = 0; j < adj[i].size(); ++j) {
                 int k = adj[i].get(j);
                 --inDegree[k];
                 if (inDegree[k] == 0) {
                     zeroInDegreeQ.add(k);
                 }
             }
         }
     }

     public void topoSortByDFS() {
         LinkedList<Integer>[] reverseAdj = new LinkedList[v];
         for (int i = 0; i < v; ++i) {
             reverseAdj[i] = new LinkedList<>();
         }
         for (int i = 0; i < v; ++i) { // 构建逆邻接表
             for (int j = 0; j < adj[i].size(); ++j) {
                 int w = adj[i].get(j); // 当前节点指向的节点，i->w
                 reverseAdj[w].add(i); // w->i
             }
         }
         boolean[] visited = new boolean[v];
         for (int i = 0; i < v; ++i) { // 图可能是不连通的，由几个不连通的图构成，排序所有图
             if (visited[i] == false) {
                 visited[i] = true;
                 dfs(i, reverseAdj, visited);
             }
         }
     }

     private void dfs(int i, LinkedList<Integer>[] reverseAdj, boolean[] visited) {
         for (int j = 0; j < reverseAdj[i].size(); ++j) {
             int w = reverseAdj[i].get(j);
             if (visited[w] == false) {
                 visited[w] = true;
                 dfs(w, reverseAdj, visited);
             }
         }
         System.out.println("->" + i); 
     }
 }
