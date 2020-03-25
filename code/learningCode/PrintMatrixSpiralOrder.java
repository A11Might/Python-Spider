/**
 * 转圈打印矩阵 
 * 给定一个整型矩阵matrix，请按照转圈的方式打印它
 * 例如：
 *       1  2   3  4
 *       5  6   7  8
 *       9  10 11 12
 *       13 14 15 16 
 * 打印结果为：1，2，3，4，8，12，16，15，14，13，9，5，6，7，11，10 
 * 【要求】额外空间复杂度为O(1)。
 */

public class PrintMatrixSpiralOrder {
    /**
     * 从外圈向内圈打印
     * @param matrix
     */
    public static void spiralOrderPrint(int[][] matrix) {
        int a = 0;
        int b = 0;
        int c = matrix.length - 1;
        int d = matrix[0].length - 1;
        while (a <= c && c <= d) {
            printEdge(matrix, a++, b++, c--, d--);
        }
    }

    /**
     * 打印矩阵一圈上的元素
     * @param matrix
     * @param a
     * @param b
     * @param c
     * @param d
     */
    public static void printEdge(int[][] matrix, int a, int b, int c, int d) {
        if (a == c) {//只有一行的情况
            for (int i = b; i <= d; ++i) {
                System.out.println(matrix[a][i]);
            }
        } else if (b == d) {//只有一列的情况
            for (int i = a; a <= c; ++i) {
                System.out.println(matrix[i][b]);
            }
        } else {//普通情况
            int curR = a;
            int curC = b;
            while (curC != d) {
                System.out.print(matrix[curR][curC++] + " ");
            }
            while (curR != c) {
                System.out.print(matrix[curR++][curC] + " ");
            }
            while (curC != b) {
                System.out.print(matrix[curR][curC--] + " ");
            }
            while (curR != a) {
                System.out.print(matrix[curR--][curC] + " ");
            }
        }
    }

    //for test
    public static void main(String[] args) {
        int[][] matrix = { { 1, 2, 3, 4 }, { 5, 6, 7, 8 }, { 9, 10, 11, 12 }, { 13, 14, 15, 16 } };
        spiralOrderPrint(matrix);

    }
}
