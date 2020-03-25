[1489. 田忌赛马](https://www.acwing.com/problem/content/1491/)

#### 算法

优先级的最优值问题，贪心策略（让田忌赢）

将田忌的马和国王的马按自然序排列，然后从两人的最慢的马匹开始分析。

1. s1 > s2 时，即田忌最慢的马快于国王最慢的马，也就是说国王最慢的马一定输，所以就让田忌最慢的马去赢国王最慢的马。
2. s1 < s2 时，即田忌最慢的马慢于国王最慢的马，也就是说田忌最慢的马一定输，所以就让田忌最慢的马输给国王最快的马。
3. s1 == s2 时，即田忌最慢的马等于国王最慢的马，这样田忌最慢的马可以平也可以输，不确定最优解，分情况讨论：
   - f1 > f2 时，即田忌最快的马快于国王最快的马，也就是说田忌最快的马一定赢，所以就让田忌最快的马赢国王最快的马。
   - f1 < f2 时，即田忌最快的马慢于国王最快的马，也就是说国王最快的马一定赢，所以就让田忌最慢的马输给国王最快的马。
   - f1 == f2 时，有两种情况，不确定最优解
     - 让田忌最慢的马输给国王最快的马（一定有最优解），选这个。
     - 让田忌最慢的马平给国王最慢的马（不一定有最优解）。

#### 时间复杂度分析：



#### 代码：

```java
import java.util.*;

public class Main {
    static int[] a, b;

    private static int judge(int x, int y) {
        if (a[x] > b[y]) return 1;
        else if (a[x] < b[y]) return -1;
        return 0;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        while (n != 0) {
            a = new int[n];
            b = new int[n];
            for (int i = 0; i < n; i++) a[i] = sc.nextInt();
            for (int i = 0; i < n; i++) b[i] = sc.nextInt();
            Arrays.sort(a);
            Arrays.sort(b);
            int s1 = 0, s2 = 0, f1 = n -1, f2 = n - 1;
            int ret = 0;
            while (s1 <= f1) {
                if (judge(s1, s2) > 0) {
                    ret++;
                    s1++;
                    s2++;
                } else if (judge(s1, s2) < 0) {
                    ret += judge(s1, f2);
                    s1++;
                    f2--;
                } else {
                    if (judge(f1, f2) > 0) {
                        ret++;
                        f1--;
                        f2--;
                    } else {
                        ret += judge(s1, f2);
                        s1++;
                        f2--;
                    }
                }
            }

            System.out.println(ret * 200);
            n = sc.nextInt();
        }
    }
}
```

