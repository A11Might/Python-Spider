/**
 * 八皇后问题
 */

public class Cal8Queens {
    private static int count = 0; // 必须为全局变量
    private static int[] arr = new int[8]; // 可以跟在形参里面

    public static void cal8Queens(int row) {
        if (row == 8) {
            count++;
            System.out.println(count);
            printArray(arr);
            return;
        }
        for (int i = 0; i < 8; ++i) {
            if (isOk(row, i)) {
                arr[row] = i;
                cal8Queens(row + 1);
            }
        }
    }

    private static boolean isOk(int row, int column) {
        int leftCol = column - 1;
        int rightCol = column + 1;
        for (int i = row - 1; i >= 0; --i) {
            if (arr[i] == column) {
                return false;
            }
            if (leftCol >= 0 && arr[i] == leftCol) {
                 return false;
            }
            if (rightCol < 8 && arr[i] == rightCol) {
                 return false;
            }
            --leftCol;
            ++rightCol;
        }
        return true;
    }

    private static void printArray(int[] arr) {
        for (int row = 0; row < 8; ++row) {
            for (int column = 0; column < 8; ++column) {
                if (arr[row] == column) {
                    System.out.print("Q ");
                } else {
                    System.out.print("* ");
                }
            }
            System.out.println();
        }
        System.out.println();
    }

    public static void main(String[] args) {
        // new Cal8Queens().cal8Queens(0);
    }
}