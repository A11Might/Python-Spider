/**
 * 岛问题
 * 一个矩阵中只有0和1两种值，每个位置都可以和自己的上、下、左、右四个位置相连
 * 如果有一片1连在一起，这个部分叫做一个岛，
 * 求一个矩阵中有多少个岛？
 * 举例：
 * 0 0 1 0 1 0
 * 1 1 1 0 1 0
 * 1 0 0 1 0 0
 * 0 0 0 0 0 0
 * 这个矩阵中有三个岛。
 */

 public class Islands {
     public static int countIslands(int[][] matrix) {
         if (matrix == null || matrix[0] == null) {
             return 0;
         }
         int res = 0;
         int row = matrix.length;
         int line = matrix[0].length;
         for (int i = 0; i < row; ++i) {
             for (int j = 0; j < line; ++j) {
                 if (matrix[i][j] == 1) {
                     ++res;
                     infect(matrix, i, j, row, line);
                 }
             }
         }
         return res;
     }

     public static void infect(int[][] matrix, int i, int j, int row, int line){
         if (matrix[i][j] == 1 && (i >= 0 && i < row) && (j >= 0 && j < line)) {
             matrix[i][j] = 2;
             infect(matrix, i - 1, j, row, line);//向上感染
             infect(matrix, i + 1, j, row, line);//向下感染
             infect(matrix, i, j - 1, row, line);//向左感染
             infect(matrix, i, j + 1, row, line);//向右感染
         }
     }

     public static void main(String[] args) {
        int[][] m1 = {  { 0, 0, 0, 0, 0, 0, 0, 0, 0 }, 
                        { 0, 1, 1, 1, 0, 1, 1, 1, 0 }, 
                        { 0, 1, 1, 1, 0, 0, 0, 1, 0 },
                        { 0, 1, 1, 0, 0, 0, 0, 0, 0 },
                        { 0, 0, 0, 0, 0, 1, 1, 0, 0 }, 
                        { 0, 0, 0, 0, 1, 1, 1, 0, 0 },
                        { 0, 0, 0, 0, 0, 0, 0, 0, 0 }, };
        System.out.println(countIslands(m1));

        int[][] m2 = {  { 0, 0, 0, 0, 0, 0, 0, 0, 0 }, 
                        { 0, 1, 1, 1, 1, 1, 1, 1, 0 }, 
                        { 0, 1, 1, 1, 0, 0, 0, 1, 0 },
                        { 0, 1, 1, 0, 0, 0, 1, 1, 0 }, 
                        { 0, 0, 0, 0, 0, 1, 1, 0, 0 }, 
                        { 0, 0, 0, 0, 1, 1, 1, 0, 0 },
                        { 0, 0, 0, 0, 0, 0, 0, 0, 0 }, };
        System.out.println(countIslands(m2));

    }
 }
