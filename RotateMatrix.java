/**
 * 旋转正方形矩阵
 * 【题目】给定一个整型正方形矩阵matrix，请把该矩阵调整成 顺时针旋转90度的样子
 * 【要求】额外空间复杂度为O(1)。
 */

 public class RotateMatrix {

     /**
      * 旋转所有元素
      * @param matrix
      */
     public static void rotate(int[][] matrix) {
         int a = 0;
         int b = 0;
         int c = matrix.length - 1;
         int d = matrix[0].length - 1;
         while (a < c) {//方阵中心位置元素不需要转动
             rotateEdge(matrix, a++, b++, c--, d--);
         }
     }

     /**
      * 旋转矩阵的一圈元素
      */
     public static void rotateEdge(int[][] matrix, int a, int b, int c, int d) {
         int times = d - b;
         int temp = 0;
         /**
          * 从(0,0)到(0,matrix.length - 1)依次旋转
          */
         for (int i = 0; i < times; ++i) {
             temp = matrix[a][b + i];
             matrix[a][b + i] = matrix[c - i][b];
             matrix[c - i][b] = matrix[c][d - i];
             matrix[c][d - i] = matrix[a + i][d];
             matrix[a + i][d] = temp;
         }
     }

     //for test
     public static void printMatrix(int[][] matrix) {
        for (int i = 0; i != matrix.length; i++) {
            for (int j = 0; j != matrix[0].length; j++) {
                System.out.print(matrix[i][j] + " ");
            }
            System.out.println();
        }
    }

    public static void main(String[] args) {
        int[][] matrix = { { 1, 2, 3, 4 }, { 5, 6, 7, 8 }, { 9, 10, 11, 12 }, { 13, 14, 15, 16 } };
        printMatrix(matrix);
        rotate(matrix);
        System.out.println("=========");
        printMatrix(matrix);

    }
 }
