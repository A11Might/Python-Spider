import java.util.ArrayList;
import java.util.List;

/**
 * 题目：一个公司的上下节关系是一个多叉树，这个公司要举办晚会，你作为组织者已经摸清了大家的心理，
 *      一个员工的直接上级如果到场，这个员工肯定不会来，每个员工都有一个活跃度的值，决定谁来你会 
 *      给这个员工发邀请函，怎么让舞会的气氛最活跃？返回最大活跃值
 * 思路：树型DP 
 *          情况：a、当前节点不来 
 *                b、当前节点来 
 *          信息：a、当前节点来时的活跃度 
 *                b、当前节点不来时的活跃度
 */

public class MaxHappyExplain {
    public static int maxHappy(Node root) {
        return Math.max(process(root).absentHappy, process(root).presentHappy);
    }

    public static ReturnData process(Node node) {
        // 第三步，定义递归基
        if (node == null) {
            return new ReturnData(0, 0);
        }
        // 第一步，把process当做黑盒
        // 讨论每种情况
        int curAbsentHappy = 0; // 当前节点不来
        int curPresentHappy = node.happy; // 当前节点来
        for (Node cur : node.nexts) {
            ReturnData nextData = process(cur);
            curAbsentHappy += Math.max(nextData.absentHappy, nextData.presentHappy); // 当前节点不出席，则其直接下级可以出席也可以不出席，选取活跃值较大的
            curPresentHappy += nextData.absentHappy; // 当前节点出席，则其直接下级一定不出席
        }
        // 第二步将黑盒实现
        return new ReturnData(curAbsentHappy, curPresentHappy);
    }
    
    // 定制返回值
    public static class ReturnData {
        public int absentHappy;
        public int presentHappy;

        public ReturnData(int absentHappy, int presentHappy) {
            this.absentHappy = absentHappy;
            this.presentHappy = presentHappy;
        }
    }

    // 多叉树节点类
    public static class Node {
        public int happy;
        public List<Node> nexts;

        public Node(int happy) {
            this.happy = happy;
            nexts = new ArrayList<>();
        }
    }
}
