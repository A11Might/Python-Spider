[18. 重建二叉树](https://www.acwing.com/problem/content/23/)

#### 算法：

递归建立整棵二叉树：先创建根结点，再递归创建左右子树，并让根结点指针指向两棵子树。

具体步骤如下：

1. 先利用前序遍历找根节点：前序遍历的第一个数，就是根节点的值；
2. 再中序遍历中找到根节点的位置 index，则中序遍历中 index 左边是左子树的中序遍历，右边是右子树的中序遍历；
3. 假设左子树的中序遍历的长度是 offset，则在前序遍历中，根节点后面的 offset 个数，是左子树的前序遍历，剩下的数是右子树的前序遍历；
4. 有了左右子树的前序遍历和中序遍历，我们可以先递归创建重构左右子树。

#### 复杂度分析：

使用哈希表（hashmap）记录每个值在中序遍历中的位置，使在中序遍历中查找节点位置的操作只需要 O(1) 的时间。此时，创建每个节点需要的时间是 O(1)，所以总时间复杂度是 O(n)。

#### 代码：

```java
/**
 * Definition for a binary tree node.
 * class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */
class Solution {
    private HashMap<Integer, Integer> positions = new HashMap<>();
    public TreeNode buildTree(int[] preorder, int[] inorder) {
        int len = preorder.length;
        for (int i = 0; i < len; i++) positions.put(inorder[i], i);
        return buildTreeCore(preorder, 0, len - 1, inorder, 0, len - 1);
    }

    private TreeNode buildTreeCore(int[] pre, int pl, int pr, int[] in, int inl, int inr) {
        if (pl > pr) return null;
        int value = pre[pl];
        TreeNode root = new TreeNode(value);
        int index = positions.get(value);
        int offset = index - inl;
        root.left = buildTreeCore(pre, pl + 1, pl + offset, in, inl, index - 1);
        root.right = buildTreeCore(pre, pl + offset + 1, pr, in, index + 1, inr);
        return root;
    }
}
```

