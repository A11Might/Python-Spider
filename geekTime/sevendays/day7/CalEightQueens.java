/**
 * 八皇后问题
 */

 public class CalEightQueens {
    public static int calEightQueens() {
        int[] record = new int[8];
        return process(record, 0);
    }

    private static int process(int[] record, int row) {
        if (row == 8) {
            return 1;
        }
        int res = 0;
        for (int column = 0; column < 8; ++column) {
            if (isValid(record, row, column)) {
                record[row] = column;
                res += process(record, row + 1);
            }
        }
        return res;
    }

    private static boolean isValid(int[] record, int row, int column) {
        for (int i = 0; i < row; ++i) {
            if (record[i] == column || Math.abs(record[i] - column) == Math.abs(i - row)) {
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        System.out.println(calEightQueens());   
    }
 }