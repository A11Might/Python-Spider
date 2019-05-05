/*
 * @lc app=leetcode.cn id=64 lang=java
 *
 * [64] 最小路径和
 * 状态转移方程：f(i, j) = grid[i][j] + Math.min(f(i + 1, col), f(i, col + 1))
 */
class Solution {
    // 动态规划
    public int minPathSum(int[][] grid) {
        if (grid.length < 1 || grid[0].length < 1) {
            return 0;
        }
        int row = grid.length;
        int col = grid[0].length;
        int[][] dp = new int[row][col];
        dp[row - 1][col - 1] = grid[row - 1][col - 1];
        for (int i = col - 2; i >= 0; --i) {
            dp[row - 1][i] = grid[row - 1][i] + dp[row - 1][i + 1];
        }
        for (int i = row - 2; i >= 0; --i) {
            dp[i][col - 1] = grid[i][col - 1] + dp[i + 1][col - 1];
        }
        for (int i = row - 2; i >= 0; --i) {
            for (int j = col - 2; j >= 0; --j) {
                dp[i][j] = grid[i][j] + Math.min(dp[i + 1][j], dp[i][j + 1]);
            }
        }
        return dp[0][0];
    }

    // 回溯
    public int minPathSum2(int[][] grid) {
        if (grid.length < 1 || grid[0].length < 1) {
            return 0;
        }
        return process(grid, 0, 0);
    }

    private int process(int[][] matrix, int row, int col) {
        // (matrix.length - 1,matrix[0].length - 1)时最短路径为其上的值
        if (row == matrix.length - 1 && col == matrix[0].length - 1) {
            return matrix[row][col];
        }
        // 当到达最底层只能向右走，最短路径为其上的值与(a,b + 1)到终点的最短路径的和
        if (row == matrix.length - 1) {
            return matrix[row][col] + process(matrix, row, col + 1);
        }
        // 当到达最右侧只能向下走，最短路径为其上的值与(a + 1,b)到终点的最短路径的和
        if (col == matrix[0].length - 1) {
            return matrix[row][col] + process(matrix, row + 1, col);
        }
        // 一般情况为，下一步向右和向下到达终点所需路径的最小值
        return matrix[row][col] + Math.min(process(matrix, row + 1, col), process(matrix, row, col +1));
    }
}

