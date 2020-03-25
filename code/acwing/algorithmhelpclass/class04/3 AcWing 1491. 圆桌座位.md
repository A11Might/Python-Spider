[1491. 圆桌座位](https://www.acwing.com/problem/content/1493/)

#### 算法：

固定一个同学，然后排列所有可能情况。

因为数据集比较小 3 ≤ N ≤ 10，所以可以枚举所有情况，使用暴搜。

题目还说，如果两个方案只有旋转角度不同，则我们将其视为一种方案，即 123 和 231 看做一种方案。处理这个问题，我们可以将同学 1 固定在第一个座位上，这样求出的所有情况都是同学 1 在第一个座位上，就不会出现重复的情况，因为同学 1 在第一个位置，旋转会其一定不在第一个位置，所以不会重复。

#### 时间复杂度分析：



#### 代码：

```java
import java.util.*;

public class Main {
    static int n, m;
    static boolean[][] f;
    static int[] pos;
    static boolean[] visited;

    private static int dfs(int index) {
        if (index == n) {
            // 坐满了则判断最后一个和第一个是否合法
            if (f[pos[n - 1]][pos[0]]) return 0;
            return 1;
        }

        // 未坐满则依次枚举所有可能情况
        int ret = 0;
        for (int i = 1; i <= n; i++) {
            if (!visited[i] && !f[i][pos[index - 1]]) {
                pos[index] = i;
                visited[i] = true;
                ret += dfs(index + 1);
                visited[i] = false;
            }
        }

        return ret;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        n = sc.nextInt();
        m = sc.nextInt();
        pos = new int[n];
        visited = new boolean[n + 1];
        f = new boolean[n + 1][n + 1];
        while (m-- > 0) {
            int a = sc.nextInt();
            int b = sc.nextInt();
            f[a][b] = f[b][a] = true;
        }

        // 题目说旋转相同则视为一个方案，所以固定第一个位置为 1,
        // 这样的到的方案就不会旋转相同
        pos[0] = 1;
        visited[1] = true;
        System.out.println(dfs(1));
    }
}
```

