/**
 * 给你一个二维数组，二维数组中的每个数都是正数，要求从左上角走到右下角，每一步只能向右或者向下。沿途经过的数字要累加起来
 * 返回最小的路径和
 */

 public class MinPath {
     /**
      * 暴力递归
      * @param matrix
      * @return
      */
     public static int minPath1(int[][] matrix) {
         return process1(matrix, 0, 0);
     }

     public static int process1(int[][] matrix, int a, int b) {
         if (a == matrix.length - 1 && b == matrix[0].length - 1) { // (matrix.length - 1,matrix[0].length - 1)时最短路径为其上的值
             return matrix[a][b];
         }
         if (a == matrix.length - 1) { // 当到达最底层只能向右走，最短路径为其上的值与(a,b + 1)到终点的最短路径的和
             return matrix[a][b] + process1(matrix, a, b + 1);
         }
         if (b == matrix[0].length - 1) { // 当到达最右侧只能向下走，最短路径为其上的值与(a + 1,b)到终点的最短路径的和
             return matrix[a][b] + process1(matrix, a + 1, b);
         }
         return matrix[a][b] + Math.min(process1(matrix, a, b + 1), process1(matrix, a + 1, b)); // 一般情况为，下一步向右和向下到达终点所需路径的最小值
     }

     /**
      * 动态规划
      * @param matrix
      * @return
      */
     public static int minPath2(int[][] matrix) {
         if (matrix == null || matrix.length == 0 || matrix[0] == null || matrix[0].length == 0) {
             return 0;
         }
         int row = matrix.length;
         int col = matrix[0].length;
         int[][] dp = new int[row][col];
         dp[row - 1][col - 1] = matrix[row - 1][col - 1];
         for (int i = col - 2; i >= 0; --i) {
             dp[row - 1][i] = matrix[row - 1][i] + dp[row - 1][i + 1];
         }
         for (int j = row - 2; j >= 0; --j) {
             dp[j][col - 1] = matrix[j][col - 1] + dp[j + 1][col - 1];
         }
         for (int i = row - 2; i >= 0; --i) {
             for (int j = col - 2; j >= 0; --j) {
                 dp[i][j] = matrix[i][j] + Math.min(dp[i + 1][j], dp[i][j + 1]);
             }
         }
         return dp[0][0];
     }

     // for test
    public static int[][] generateRandomMatrix(int rowSize, int colSize) {
        if (rowSize < 0 || colSize < 0) {
            return null;
        }
        int[][] result = new int[rowSize][colSize];
        for (int i = 0; i != result.length; i++) {
            for (int j = 0; j != result[0].length; j++) {
                result[i][j] = (int) (Math.random() * 10);
            }
        }
        return result;
    }

    public static void main(String[] args) {
        int[][] m = { { 1, 3, 5, 9 }, { 8, 1, 3, 4 }, { 5, 0, 6, 1 }, { 8, 8, 4, 0 } };
        System.out.println(minPath1(m));
        System.out.println(minPath2(m));

        m = generateRandomMatrix(6, 7);
        System.out.println(minPath1(m));
        System.out.println(minPath2(m));
    }
 }