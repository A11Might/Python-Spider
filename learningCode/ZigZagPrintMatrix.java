/**
 * “之”字形打印矩阵
 * 给定一个矩阵matrix，按照“之” 字形的方式打印这个矩阵
 * 例如： 
 *      1 2  3  4 
 *      5 6  7  8 
 *      9 10 11 12
 * “之” 字形打印的结果为： 1， 2， 5， 9， 6， 3， 4， 7， 10， 11，8， 12
 * 【要求】 额外空间复杂度为O(1)。
 */

 public class ZigZagPrintMatrix {
     public static void printMatrixZigZag(int[][] matrix) {
         int a = 0;
         int b = 0;
         int c = 0;
         int d = 0;
         int endR = matrix.length - 1;//总行数
         int endC = matrix[0].length - 1;//总列数
         boolean flag = true;//打印方向
         while (b != endC + 2) {
             printLevel(matrix, a, b, c, d, flag);
             b = a == endR ? ++b : b;//放在a前面，避免跳过(endR,0)节点
             a = a != endR ? ++a : a;
             c = d == endC ? ++c : c;
             d = d != endC ? ++d : d;
             flag = !flag;
         }
     }

     public static void printLevel(int[][] matrix, int a, int b, int c, int d, boolean flag) {
         if (flag) {
             while (a != c - 1) {//向右上打印
                System.out.print(matrix[a--][b++] + " ");
             }
         } else {
             while (c != a + 1) {//向左下打印
                 System.out.print(matrix[c++][d--] + " ");
             }
         }
     }

     //for test
     public static void main(String[] args) {
        int[][] matrix = { { 1, 2, 3, 4 }, { 5, 6, 7, 8 }, { 9, 10, 11, 12 } };
        printMatrixZigZag(matrix);

    }
 }
