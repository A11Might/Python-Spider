[30. 正则表达式匹配](https://www.acwing.com/problem/content/28/)

#### 算法：

闫氏 DP 分析法：

- 状态表示：f(i, j)

  - 集合：所有 s[1 ... i] 和 p[1 ... j] 匹配的方案的集合。
  - 属性：集合是否为空。

- 状态计算：

  - p[i] 不是 "_*"：当前 s 中 i 元素需要和 p 中 j 元素匹配，也就是f(i - 1, j - 1) && s[i] == p[j]。其中 s[i] == p[j] 有两种情况，s[i] == p[j] 或者 p[j] == '.'。

  - p[i] 是 "_*"：需要分情况讨论，"\*" 可以匹配 s 中零个字符，一个字符……一直到不能匹配为止。

    - 匹配零个字符：f(i, j - 2) 。
    - 匹配一个字符：f(i - 1, j - 2) && s[i] == p[j]。
    - 匹配两个字符：f(i - 2, j - 2) && s[i] == p[j] && s[i - 1] == p[j]。
    - ……
    - 匹配 k 个字符：f(i - k, j - 2) && s[i] == p[j] && s[i - 1] == p[j] ... s[i - k + 1] == p[j]。

    那么 f(i, j) = f(i, j - 2) || f(i - 1, j - 2) && s[i] == p[j] || f(i - 2, j - 2) && s[i] == p[j] && s[i - 1] == p[j] ...。这样的话时间复杂度就是 O(n ^ 3)。

    我们可以使用类似完全背包的方法优化：

    f(i - 1, j) = f(i - 1, j - 2) || f(i - 2, j - 2) && s[i - 1] == p[j] || f(i - 3, j - 2) && s[i - 1] == p[j] && s[i - 2] == p[j] ...

    因为 && 是具有结合律的，可以化简 f(i, j) 为：

    f(i, j) = f(i, j - 2) || f(i - 1, j) && s[i] == p[j]

#### 时间复杂度分析：



#### 代码：

```java
class Solution {
    public boolean isMatch(String s, String p) {
        int n = s.length(), m = p.length();
        // 为了方便让下标从 1 开始
        s = " " + s;
        p = " " + p;

        // dp[i][j] 表示字符串前 i 个字符和模式串前 j 个字符的匹配情况
        boolean[][] dp = new boolean[n + 1][m + 1];
        // 两个空字符串是匹配的
        dp[0][0] = true;
        // 字符串为空模式串不为空的情况
        for (int j = 2; j <= m; j++) {
            dp[0][j] = dp[0][j - 2] && p.charAt(j) == '*';
        }
        // 字符串不为空模式串为空的情况，不可能匹配

        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= m; j++) {
                // '_*' 算作一个字符，单独遇到 '_' 则跳过
                if (j + 1 <= m && p.charAt(j + 1) == '*') continue;

                if (p.charAt(j) != '*') {
                    dp[i][j] = dp[i - 1][j - 1] && (s.charAt(i) == p.charAt(j) || p.charAt(j) == '.');
                } else {
                    dp[i][j] = dp[i][j - 2] || dp[i - 1][j] && (s.charAt(i) == p.charAt(j - 1) || p.charAt(j - 1) == '.');
                }
            }
        }

        return dp[n][m];
    }
}
```

