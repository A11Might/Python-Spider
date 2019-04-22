package class_8;

/**
 * 汉诺塔
 */

 public class Hanoi {
     public static void hanoi(int n) {
         if (n > 0) {
             process(n , "left", "mid", "right");
         }
     }

     private static void process(int n, String from, String help, String to) {
         if (n == 1) {
             System.out.println("from " + from + " to " + to);
         } else {
             process(n - 1, from, to, help);
             process(1, from, help, to);
             process(n - 1, help, from, to);
         }
     }

     public static void main(String[] args) {
         int n = 3;
         hanoi(n);
     }
 }