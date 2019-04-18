import java.util.HashMap;

public class Recursion {
    /**
     * 1 + 2 + 3 + ... + 100
     * f(n) = f(n - 1) + n
     */
    public static int sum(int n) {
        if (n == 1) {
            return 1;
        } else {
            return sum(n - 1) + n;
        }
    }

    /**
     * 斐波那切数列:1 1 2 3 5 8 ...
     * f(n) = f(n - 1) + f(n - 2)
     */
    public static int febonacci(int n) {
        if (n == 1) {
            return 1;
        } else if (n == 2) {
            return 1;
        } else {
            return febonacci(n - 1) + febonacci(n - 2);
        }
    }

    /**
     * 上楼梯问题：有n阶楼梯，一步可以上一步或两步，一共有多少种走法
     * 递归公式同斐波那切数列
     * 用散列表优化，避免重复计算
     */
    public static int climbStairs(int n) {
        HashMap<Integer,Integer> map = new HashMap<>();
        if (n == 0) {
            return 0;
        } else if (n == 1) {
            return 1;
        } else if (n == 2) {
            return 2;
        } else if (map.containsKey(n)) {
            return map.get(n);
        }
        map.put(n,climbStairs(n - 1) + climbStairs(n - 2));
        return map.get(n);
    }

    /**
     * 汉诺塔
     * f(n) = f(n - 1) + 1
     */
    public static void hano(char a, char b, char c, int n) {
        if (n > 0) {
            hano(a, c, b, n - 1);
            move(a, c);
            hano(b, a, c, n - 1);
        }
        return;
    }
    private static void move(char a, char c) {
        System.out.println(a + ">>" + c);
    }
}