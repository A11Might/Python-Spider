[1488. 最短距离](https://www.acwing.com/problem/content/1490/)

#### 算法：

反过来考虑所有商店到每个村庄的最短距离，建立一个虚拟的超级源点 s，从 s 向每个有商店的村庄连一条边（有向边即可，往回走没有意义），边权为 0。

从超级源点到村庄 a 的最短距离就是从超级源点出发到村庄 a 的所有路径中的最短距离，从超级源点出发的每条路径都经过商店，所以从超级源点到村庄 a 的最短距离每个村庄到商店的最短距离。

使用 spfa 算法计算最短路径。

#### 时间复杂度分析：



#### 代码：

```java
import java.util.*;

public class Main {
    private static int n, m;
    private static List<int[]>[] graph;
    private static int[] dist;
    // 标记节点是否在队列中。
    private static boolean[] st;

    private static void spfa() {
        Arrays.fill(dist, Integer.MAX_VALUE);
        Queue<Integer> queue = new ArrayDeque<>();
        queue.offer(0);
        st[0] = true;
        dist[0] = 0;
        while (!queue.isEmpty()) {
            int cur = queue.poll();
            st[cur] = false;
            for (int[] arr : graph[cur]) {
                int next = arr[0], w = arr[1];
                if (dist[next] > dist[cur] + w) {
                    dist[next] = dist[cur] + w;
                    if (!st[next]) {
                        queue.offer(next);
                        st[next] = true;
                    }
                }
            }
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        n = sc.nextInt();
        m = sc.nextInt();
        graph = new List[n + 1];
        dist = new int[n + 1];
        st = new boolean[n + 1];

        // 建邻接表。
        for (int i = 0; i <= n; i++) graph[i] = new ArrayList<>();
        for (int i = 0; i < m; i++) {
            int a = sc.nextInt();
            int b = sc.nextInt();
            int c = sc.nextInt();

            // 无向图
            graph[a].add(new int[] {b, c});
            graph[b].add(new int[] {a, c});
        }

        // 建超级节点
        int k = sc.nextInt();
        while (k-- > 0) {
            graph[0].add(new int[] {sc.nextInt(), 0});
        }

        spfa();
        int q = sc.nextInt();
        while (q-- > 0) {
            System.out.println(dist[sc.nextInt()]);
        }
    }
}
```

