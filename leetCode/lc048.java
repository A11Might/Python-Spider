/*
 * @lc app=leetcode.cn id=48 lang=java
 *
 * [48] 旋转图像
 * 
 * 一圈一圈旋转
 */
class Solution {
    public void rotate(int[][] matrix) {
        int a = 0;
        int b = 0;
        int c = matrix.length - 1;
        int d = matrix[0].length - 1;
        while (b < d) {
            rotateCycle(matrix, a++, b++, c--, d--);
        }
    }

    private void rotateCycle(int[][] matrix, int a, int b, int c, int d) {
        int times = d - b;
        for (int i = 0; i < times; ++i) {
            int temp = matrix[a][b + i];
            matrix[a][b + i] = matrix[c - i][b];
            matrix[c - i][b] = matrix[c][d - i];
            matrix[c][d - i] = matrix[a + i][d];
            matrix[a + i][d] = temp;
        }
        
    }
}

