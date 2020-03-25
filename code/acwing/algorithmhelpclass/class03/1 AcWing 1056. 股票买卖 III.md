[1056. 股票买卖 III](https://www.acwing.com/problem/content/1058/)

#### 算法：

进行两次交易或者划分成两段的问题，可以通过分割线分成两个子问题。

前一段：先考虑在前 i 天完成第一笔交易可以获得的最大收益。我们可以从前往后枚举在第 i 天卖出获得的收益中取最大值，那么第 i 天卖出可以获得的最大收益方式就是从第 i 天前的最低价买入，再在第 i 天卖出。

后一段：再考虑在第 i 天后完成第二笔可以获得的最大收益，第二次交易必须在第 i 天后进行。我们可以采用同前一段相同的方式求解第 i 天后可以获得的最大收益，但复杂度就为 O(n ^ 2)。为此我们可以预处理后一段：从后往前枚举在第 i 天买入获得的收益中取最大值，那么第 i 天可以获得的最大收益就是从第 i 天买入，再在第 i 天后的最大价卖出。

可以得到递推方程：dp(i) = f(i) + b(i + 1)。

#### 时间复杂度分析：



#### 代码：

```java
import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int[] nums = new int[n + 2];
        for (int i = 1; i <= n; i++) {
            nums[i] = sc.nextInt();
        }

        // 预处理 f[i]：前 i 天买卖一次的最大收益
        int[] f = new int[n + 2];
        for (int i = 2, minv = nums[1]; i <= n; i++) {
            f[i] = Math.max(f[i - 1], nums[i] - minv);
            minv = Math.min(minv, nums[i]);
        }

        // 预处理 b[i]：i 天后买卖一次的最大收益
        int[] b = new int[n + 2];
        for (int i = n - 2, maxv = nums[n]; i > 0; i--) {
            b[i] = Math.max(b[i + 1], maxv - nums[i]);
            maxv = Math.max(maxv, nums[i]);
        }

        // 枚举所有情况
        int ret = 0;
        for (int i = 2; i <= n; i++) {
            ret = Math.max(ret, f[i] + b[i + 1]);
        }

        System.out.println(ret);
    }
}
```

