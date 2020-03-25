[851. spfa求最短路](https://www.acwing.com/problem/content/853/)

#### 算法：

spfa 算法（队列优化的Bellman-Ford算法）

时间复杂度平均情况下 O(m)，最坏情况下 O(n * m), n 表示点数，m 表示边数。

```java
private int[] dist; // 存储每个点到 1 号点的最短距离
private boolean[] visited; // 存储每个点是否在队列中

// 求 1 号点到 n 号点的最短路距离，如果从 1 号点无法走到 n 号点则返回 -1
public int spfa() {
    Arrays.fill(dist, Integer.MAX_VALUE);
    
    Queue<Integer> queue = new ArrayDeque<>();
    // 将起点放入队列中
    queue.offer(1);
    dist[1] = 0;
    visited[1] = true;
    
    while (!queue.isEmpty()) {
        int cur = queue.poll();
        visited[cur] = false;
        
        for (int[] pair : graph[cur]) {
            int next = pair[0], w = pair[1];
            // 只要节点变小就放入队列中，用来更新所有后面的后继
            if (dist[next] > dist[cur] + w) {
                dist[next] = dist[cur] + w;
                 // 如果队列中已存在 j，则不需要将 j 重复插入
                if (!visited[next]) {
                    queue.offer(next);
                    visited[next] = true;
                }
            }
        }
    }
    
    if (dist[n] == Integer.MAX_VALUE) return -1;
    return dist[n];
}
```

#### 时间复杂度分析：



#### 代码：

```java
import java.util.*;

public class Main {
    private static int n, m;
    private static List<int[]>[] graph;
    // visited 数组标记节点是否在队列中。
    private static boolean[] visited;
    private static int[] dist;

    private static int spfa() {
        Queue<Integer> queue = new ArrayDeque<>();
        queue.offer(1);
        visited[1] = true;
        dist[1] = 0;

        while (!queue.isEmpty()) {
            int cur = queue.poll();
            visited[cur] = false;

            for (int[] pair : graph[cur]) {
                int to = pair[0];
                int w = pair[1];
                if (dist[to] > dist[cur] + w) {
                    dist[to] = dist[cur] + w;
                    if (!visited[to]) {
                        queue.offer(to);
                        visited[to] = true;
                    }
                }
            }
        }

        if (dist[n] == Integer.MAX_VALUE) return -1;
        return dist[n];
    }

    public static void main(String args[]) throws Exception {
        Scanner sc=new Scanner(System.in);
        n = sc.nextInt();
        m = sc.nextInt();

        // 建邻接表
        graph = new List[n + 1];
        for (int i = 1; i <= n; i++) graph[i] = new ArrayList<>();
        for (int i = 0; i < m; i++) {
            int a = sc.nextInt();
            int b = sc.nextInt();
            int w = sc.nextInt();
            graph[a].add(new int[] {b, w});
        }

        visited = new boolean[n + 1];
        dist = new int[n + 1];
        Arrays.fill(dist, Integer.MAX_VALUE);
        int ret = spfa();
        if (ret == -1) {
            System.out.println("impossible");
        } else {
            System.out.println(ret);
        }
    }
}
```