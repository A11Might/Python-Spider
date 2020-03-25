[1490. 最长上升子串](https://www.acwing.com/problem/content/1492/)

#### 算法：

有限集最优解问题，只要能枚举出所有情况，就可以找到最优解。

依次枚举删除给定序列中的不同元素，若当前删除的是第 i 个元素，就判断 i - 1 与 i + 1 两个元素的大小关系：

- 当 i - 1 < i + 1 时，以 i - 1 结尾的最长上升子串可以和以 i + 1 开头的最长上升子串可以合并成更大的上升子串，即 ret(i) = f(i - 1) + b(i + 1)。
- 当 i - 1 > i + 1 时，以 i - 1 结尾的最长上升子串可以和以 i + 1 开头的最长上升子串不能合并，即 ret(i) = max(f(i - 1), b(i + 1))。

这样可以枚举所有情况吗？

最优解有两种情况：删除元素对最优解有帮助（上述算法肯定能找到，通过 + 得到）和删除元素对最优解没有帮助。

对于第二种最优解的情况，我们可以在删除该最优解前一个或者后一个元素时得到（通过max得到）。

#### 时间复杂度分析：



#### 代码：

```java
import java.util.*;

public class Main {
    static int n;
    static int[] nums, f, b;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        n = sc.nextInt();
        // 边界情况：需要在前后各加一个位置
        nums = new int[n + 2];
        for (int i = 1; i <= n; i++) {
            nums[i] = sc.nextInt();
        }

        // 预处理 f[i]；以第 i 个字符结尾的最大单调上升子串的长度
        f = new int[n + 2];
        for (int i = 1; i <= n; i++) {
            if (nums[i] > nums[i - 1]) f[i] = f[i - 1] + 1;
            else f[i] = 1;
        }

        // 预处理 b[i]：以第 i 个字符开头的最大单调上升子串的长度
        b = new int[n + 2];
        for (int i = n; i > 0; i--) {
            if (nums[i] < nums[i + 1]) b[i] = b[i + 1] + 1;
            else b[i] = 1;
        }


        int ret = 0;
        // 枚举删除数字
        for (int i = 1; i <= n; i++) {
            if (nums[i - 1] < nums[i + 1]) ret = Math.max(ret, f[i - 1] + b[i + 1]);
            else ret = Math.max(ret, Math.max(f[i - 1], b[i + 1]));
        }

        System.out.println(ret);        
    }
}
```

