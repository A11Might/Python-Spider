/**
 * 给定一个数组，求子数组的最大异或和
 * 
 * 一个数组的异或和为，数组中所有的数异或起来的结果
 */

public class MaxEOR {
    /**
     * 暴力求解，时间复杂度O(n ^ 3)
     * @param arr
     * @return
     */
    public static int getMaxE1(int[] arr) {
        int max = Integer.MIN_VALUE;
        for (int i = 0; i < arr.length; i++) { // 求到当前位置的为止的最大异或和
            for (int start = 0; start <=i; start++) { // 从start开始到i结束的异或和
                int res = 0;
                for (int k = start; k <= i; k++) {
                    res ^= arr[k];
                }
                max = Math.max(max, res);
            }
        }

        return max;
    }

    /**
     * 优化暴力求解，start到i的异或和 = 0到i的异或和 ^ 0到start - 1的异或和，时间复杂度O(n ^ 2)
     * @param arr
     * @return
     */
    public static int getMaxE2(int[] arr) {
        int max = Integer.MIN_VALUE;
        int[] dp = new int[arr.length];
        int xor = 0;
        for (int i = 0; i < arr.length; i++) {
            xor ^= arr[i]; // 0到i的异或和
            max = Math.max(max, xor);
            for (int start = 1; start <= i; start++) { // start到i的异或和(start从1开始)
                // start到i的异或和 = 0到i的异或和 ^ 0到start - 1的异或和
                int startToIEor = xor ^ dp[start - 1];
                max = Math.max(max, startToIEor);
            }
            dp[i] = xor; // dp记录0到i的异或和
        }

        return max;
    }

    /**
     * 使用前缀树优化求解，类似贪心，时间复杂度O(n)
     * @param arr
     * @return
     */
    public static int maxXorSubarray(int[] arr) {
        if (arr == null || arr.length == 0) {
            return 0;
        }
        int max = Integer.MIN_VALUE;
        int xor = 0;
        NumTrie numTrie = new NumTrie();
        numTrie.add(0); // 需要初始化一条路径，防止第一次取最大异或和时无路可走发生错误
        for (int i = 0; i < arr.length; i++) {
            xor ^= arr[i]; // 0到i的异或和
            max = Math.max(max, numTrie.maxXor(xor)); // maxXor返回当前0到i区间中，子数组最大的异或和
            numTrie.add(xor);
        }

        return max;
    }

    // 前缀树节点类
    public static class Node {
        public Node[] nexts = new Node[2]; // 0, 1
    }

    // 前缀树类
    public static class NumTrie {
        public Node head = new Node();

        // 时间复杂度O(n)
        public void add(int num) {
            Node cur = head;
            for (int move = 31; move >= 0; move--) { // 从符号位到末尾数字依次加入前缀树
                int path = ((num >> move) & 1);
                cur.nexts[path] = cur.nexts[path] == null ? new Node() : cur.nexts[path];
                cur = cur.nexts[path];
            }
        }

        // num是0-i的异或结果
        // 时间复杂度O(n)
        public int maxXor(int num) {
            Node cur = head;
            int res = 0;
            for (int move = 31; move >= 0; move--) {
                int path = (num >> move) & 1; // 从符号位到末尾的每位数字
                int best = move == 31 ? path : (path ^ 1); // 期待选的路(符号位希望为变成0，其他位希望变成1)
                best = cur.nexts[best] != null ? best : (best ^ 1); // 实际选的路(有best选best，没有就选另一个，肯定有路走)
                res |= (path ^ best) << move; // 将最大异或和还原出来
                cur = cur.nexts[best]; // 继续走
            }

            return res;
        }
    }
}
