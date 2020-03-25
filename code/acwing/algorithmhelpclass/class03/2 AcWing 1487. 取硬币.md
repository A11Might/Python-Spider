[1487. 取硬币](https://www.acwing.com/problem/content/1489/)

#### 算法：

闫氏 DP 分析法：

- 状态表示 f(i, j)：

  - 集合：所有只从前 i 个硬币中选且总面值是 j 的选法的集合。
  - 属性：数量。

- 状态计算：

  求 f(i, j)，一般找最后一个不同点来划分。

  - 不选第 i 个硬币：f(i, j) = f(i - 1, j)，也就是从 1 ~ i - 1 中选且总面值为 j 的所有选法个数。

  - 选第 i 个硬币

    1. 第 i 个硬币为纪念币（0 - 1 背包问题）

       因为所有方案都包含硬币 i，所以可以将每个方案分为两部分：变化的部分和不变的部分。不变的部分就是第 i 个硬币（一定选），变化的部分则为 f(i - 1, j - c[i])，也就是从 1 ~ i - 1 中选取总面值为 j - c[i] 的所有选法个数。

       所以第 i 每硬币为纪念币的递推方程：f(i, j) = f(i - 1, j) + f(i - 1, j - c[i])。优化完空间后需要从右往左遍历硬币。

    2. 第 i 个硬币为普通硬币（完全背包问题）

       第 i 个硬币可以选取 1 次，2 次……一直到不能选取位置。

       假设我们选了 k 个 i 硬币，每个方案的不变部分都是 k 个 i 硬币，变化的部分则为 f(i - 1, j - k * c[i])，也就是从 1 ~ i - 1 中选取总面值为 j - k * c[i] 的所有选法的个数。

       总方案数应该为每类情况之和，所以第 i 每硬币为普通硬币的递推方程：f(i, j) = f(i - 1, j) + f(i - 1, j - c[i]) + f(i - 1, j - 2 * c[i]) + ... 

       完全背包的时间复杂度是 O(n ^ 2) 的，可以优化：

       f(i, j - c[i]) = f(i - 1, j - c[i]) + f(i - 1, j - 2 * c[i]) + f(i - 1, j - 3 * c[i]) + ...

       将原式化简为：

       f(i, j) = f(i - 1, j) + f(i, j - c[i])。 优化完空间后需要从左往右遍历硬币。

#### 时间复杂度分析：



#### 代码：

```java
import java.util.*;

public class Main {
    static final int MOD = (int) 1e9 + 7;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n1 = sc.nextInt();
        int n2 = sc.nextInt();
        int m = sc.nextInt();
        int[] dp = new int[m + 1];
        // 初始情况：面值为 0 只有一种情况
        dp[0] = 1;
        // 普通硬币：完全背包问题
        for (int i = 1; i <= n1; i++) {
            int c = sc.nextInt();
            for (int j = c; j <= m; j++) {
                dp[j] = (dp[j] + dp[j - c]) % MOD;
            }
        }
        // 纪念币：0 - 1 背包问题
        for (int i = 1; i <= n2; i++) {
            int c = sc.nextInt();
            for (int j = m; j >= c; j--) {
                dp[j] = (dp[j] + dp[j - c]) % MOD;
            }
        }

        System.out.println(dp[m]);
    }
}
```

