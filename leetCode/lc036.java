/*
 * @lc app=leetcode.cn id=36 lang=java
 *
 * [36] 有效的数独
 * 
 * 一次迭代中判断，行中有没有重复的数字,列中有没有重复的数字,3 x 3 子数独内有没有重复的数字
 */
class Solution {
    public boolean isValidSudoku(char[][] board) {
        boolean[][] rows = new boolean[9][9]; // 记录某行，某位数字是否已经被摆放
        boolean[][] cols = new boolean[9][9]; // 记录某列，某位数字是否已经被摆放
        boolean[][] boxes = new boolean[9][9]; // 记录某 3x3 宫格内，某位数字是否已经被摆放
        for (int i = 0; i < 9; ++i) {
            for (int j = 0; j < 9; ++j) {
                if (board[i][j] != '.') {
                    int curNum = board[i][j] - '1';
                    int boxIndex = i / 3 * 3 + j / 3; // i是行标，j是列标。行标决定一组block的起始位置（因为block为3行，所以除3取整得到组号，又因为每组block为3个，所以需要乘3），列标再细分出是哪个block（因为block是3列，所以除3取整）
                    if (rows[i][curNum] || cols[j][curNum] || boxes[boxIndex][curNum]) {
                        return false;
                    }
                    rows[i][curNum] = true;
                    cols[j][curNum] = true;
                    boxes[boxIndex][curNum] = true;
                }
            }
        }
        return true;
    }
}

