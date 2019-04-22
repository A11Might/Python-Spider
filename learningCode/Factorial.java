package class_8;

/**
 * 求n!的结果
 */
public class Factorial {
     public static long getFactorial1(int n) {
         long factorial = 1L;
         for (int i = 2; i <= n; ++i) {
             factorial *= i;
         }
         return factorial;
     }

     public static long getFactorial2(int n) {
         if (n == 1) {
             return 1L;//long型常量
         }
         return (long) n * getFactorial2(n - 1);
     }

     public static void main(String[] agrs) {
         int n = 5;
         System.out.println(getFactorial1(n));
         System.out.println(getFactorial2(n));
     }
}