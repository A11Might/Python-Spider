package geektime;

/**
 * 矩阵最短路径长度
 */

 public class MinDistBT {
     /**
      * 回溯
      * @param matrix
      * @return
      */
     public static int minDistBT1(int[][] matrix) {
         return process(matrix, 0, 0);
     }

     public static int process(int[][] matrix, int row, int col) {
         if (row == matrix.length - 1 && col == matrix[0].length - 1) {
             return matrix[row][col];
         }
         if (row == matrix.length - 1) {
             return matrix[row][col] + process(matrix, row, col + 1);
         }
         if (col == matrix[0].length - 1) {
             return matrix[row][col] + process(matrix, row + 1, col);
         }
         return matrix[row][col] + Math.min(process(matrix, row, col + 1), process(matrix, row + 1, col));
     }

     /**
      * 动态规划：状态转移表法
      * @param matrix
      * @return
      */
     public static int minDistBT2(int[][] matrix) {
         int r = matrix.length;
         int c = matrix[0].length;
         int[][] dp = new int[r][c];
         dp[r - 1][c - 1] = matrix[r - 1][c - 1];
         for (int j = c - 2; j >= 0; --j) {
             dp[r - 1][j] = matrix[r - 1][j] + dp[r - 1][j + 1];
         }
         for (int i = r - 2; i >= 0; --i) {
             dp[i][c - 1] = matrix[i][c - 1] + dp[i + 1][c - 1];
         }
         for (int i = r - 2; i >= 0; --i) {
             for (int j = c - 2; j >= 0; --j) {
                 dp[i][j] = matrix[i][j] + Math.min(dp[i + 1][j], dp[i][j + 1]);
             }
         }
         return dp[0][0];
     }

     /**
      * 动态规划：状态转移方程法
      * @param matrix
      * @param i
      * @param j
      * @param mention
      * @return
      */
     public static int minDistBT3(int[][] matrix,int i, int j, int[][] mention) { // mention为matrix等大的备忘录，需要为全局变量以备反复查用
         if (i == matrix.length - 1 && j == matrix[0].length - 1) {
             return matrix[i][j];
         }
         if (mention[i][j] > 0) {
             return mention[i][j]; // 从备忘录中查找
         }
         int rightMin = Integer.MAX_VALUE; // 若到达右边界，则选择downMin
         if (j + 1 <= matrix[0].length - 1) {
             rightMin = minDistBT3(matrix, i, j + 1, mention); // 从备忘录中查找，若没有则计算
         }
         int downMin = Integer.MAX_VALUE; // 若到达下边界，则选择rightMin
         if (i + 1 <= matrix.length - 1) {
             downMin = minDistBT3(matrix, i + 1, j, mention); // 同上
         }
         int curMin = matrix[i][j] + Math.min(rightMin, downMin);
         mention[i][j] = curMin; // 存入备忘录
         return curMin;
     }

     public static void main(String[] args) {
         int[][] matrix = {{1, 3, 5, 9}, 
                           {2, 1, 3, 4},
                           {5, 2, 6, 7},
                           {6, 8, 4, 2}};
         int[][] mention = new int[4][4]; 
         System.out.println(minDistBT1(matrix));
         System.out.println(minDistBT2(matrix));
         System.out.println(minDistBT3(matrix, 0, 0, mention));
     }
 }
