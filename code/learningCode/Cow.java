/**
 * 母牛每年生一只母牛，新出生的母牛成长三年后也能每年生一只母牛，假设不会死
 * 求N年后，母牛的数量。
 */

 public class Cow {
     /**
      * 递归
      * f(n) = f(n - 1) + f(n - 3) 
      * 今年 = 去年 + 新生牛
      */
      public static int cowNumbers1(int n) {
          if (n < 0) {
              return 0;
          }
          if (n == 1 || n == 2 || n == 3) {
              return n;
          }
          return cowNumbers1(n - 1) + cowNumbers1(n - 3);
      }

      /**
       * 迭代
       */
      public static int cowNumbers2(int n) {
          if (n < 0) {
             return 0;
          }
          if (n == 1 || n == 2 || n == 3) {
             return n;
          }
          int cur = 3;
          int pre = 2;
          int prepre = 1;
          int temp1 = 0;
          int temp2 = 0;
          for (int i = 4; i <= n; ++i) {
             temp1 = cur + prepre;
             temp2 = cur;
             cur = temp1;
             prepre = pre;
             pre = temp2;
          }
          return cur;
      }
      
    public static void main(String[] args) {
        int n = 20;
        System.out.println(cowNumbers1(n));
        System.out.println(cowNumbers2(n));
    }
 }