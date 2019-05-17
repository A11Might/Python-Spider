/*
 * @lc app=leetcode.cn id=200 lang=java
 *
 * [200] 岛屿的个数
 * 
 * 使用感染函数，每遇到一个岛就把岛全部感染为2，用于区别岛与岛
 */
class Solution {
    public int numIslands(char[][] grid) {
        if (grid.length < 1 || grid == null) { // 实例 [],grid[0].length数组越界
            return 0;
        }
        int res = 0;
        int row = grid.length;
        int column = grid[0].length;
        for (int i = 0; i < row; ++i) {
            for (int j = 0; j < column; ++j) {
                if (grid[i][j] == '1') {
                    res++;
                    infection(grid, i, j);
                }
            }
        }
        return res;
    }

    private void infection(char[][] grid, int a, int b) {
        if (a >= 0 && a < grid.length && b >= 0 && b < grid[0].length && grid[a][b] == '1') { // 在矩阵中且为同一个岛则感染
            grid[a][b] = '2'; // 感染
            infection(grid, a - 1, b); // 向上感染
            infection(grid, a + 1, b); // 向下感染
            infection(grid, a, b - 1); // 向左感染
            infection(grid, a, b + 1); // 向右感染
        }
    }
}

