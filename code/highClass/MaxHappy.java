import java.util.ArrayList;
import java.util.List;

/**
 * 题目：一个公司的上下节关系是一个多叉树，这个公司要举办晚会，你作为组织者已经摸清了大家的心理，
 *      一个员工的直接上级如果到场，这个员工肯定不会来，每个员工都有一个活跃度的值，决定谁来你会 
 *      给这个员工发邀请函，怎么让舞会的气氛最活跃？返回最大活跃值
 * 
 * 思路：树型DP，https://github.com/A11Might/SomePracticeCode/blob/master/highClass/MaxHappyExplain.java
 *      情况：a、当前节点不来 
 *            b、当前节点来 
 *       信息：a、当前节点来时的活跃度 
 *             b、当前节点不来时的活跃度
 */

public class MaxHappy {
    public static class Node {
        public int happy;
        public List<Node> nexts;

        public Node(int happy) {
            this.happy = happy;
            nexts = new ArrayList<>();
        }
    }

    public static int getMaxHappy(Node head) {
        ReturnData data = process(head);
        return Math.max(data.presentHappy, data.absentHappy);
    }

    public static class ReturnData {
        public int presentHappy;
        public int absentHappy;

        public ReturnData(int presentHappy, int absentHappy) {
            this.presentHappy = presentHappy;
            this.absentHappy = absentHappy;
        }
    }

    public static ReturnData process(Node head) {
        if (head == null) {
            return new ReturnData(0, 0);
        }

        int presentHappy = head.happy;
        int absentHappy = 0;

        for (Node node : head.nexts) {
            ReturnData nextData = process(node);
            presentHappy += nextData.absentHappy;
            absentHappy += Math.max(nextData.presentHappy, nextData.absentHappy);
        }

        return new ReturnData(presentHappy, absentHappy);
    }

    public static int maxHappy(int[][] matrix) {
        int[][] dp = new int[matrix.length][2];
        boolean[] visited = new boolean[matrix.length];
        int root = 0;
        for (int i = 0; i < matrix.length; i++) {
            if (i == matrix[i][0]) {
                root = i;
            }
        }
        process(matrix, dp, visited, root);
        return Math.max(dp[root][0], dp[root][1]);
    }

    public static void process(int[][] matrix, int[][] dp, boolean[] visited, int root) {
        visited[root] = true;
        dp[root][1] = matrix[root][1];
        for (int i = 0; i < matrix.length; i++) {
            if (matrix[i][0] == root && !visited[i]) {
                process(matrix, dp, visited, i);
                dp[root][1] += dp[i][0];
                dp[root][0] += Math.max(dp[i][1], dp[i][0]);
            }
        }
    }

    public static void main(String[] args) {
        int[][] matrix = { { 1, 8 }, { 1, 9 }, { 1, 10 } };
        System.out.println(maxHappy(matrix));
    }
}
