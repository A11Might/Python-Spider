[19. 二叉树的下一个节点](https://www.acwing.com/problem/content/31/)

#### 算法：

1. 如果当前节点有右儿子，则右子树中最左侧的节点就是当前节点的后继。
2. 如果当前节点没有右儿子，则需要沿着father一直向上找，找到第一个是其father左儿子的节点，该节点的father就是当前节点的后继。

#### 时间复杂度分析：

不论往上找还是往下找，总共遍历的节点数都不大于树的高度。所以时间复杂度是 O(h)，其中 h 是树的高度。

#### 代码：

```java
/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode father;
 *     TreeNode(int x) { val = x; }
 * }
 */
class Solution {
    public TreeNode inorderSuccessor(TreeNode p) {
        if (p.right != null) {
            // 有右子树，后继节点为右子树的最左节点
            p = p.right;
            while (p.left != null) p = p.left;
            return p;
        }
        // 没有右子树，后继节点为第一个将其作为左子树的父节点
        TreeNode f = p.father;
        while (f != null && f.left != p) {
            p = f;
            f = p.father;
        }
        return f;
    }
}
```

