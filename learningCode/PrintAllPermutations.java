package class_8;

import java.util.HashSet;

/**
 * 打印一个字符串的全部排列
 */

 public class PrintAllPermutations {
     /**
      * 字符串全排列，字符串中不含重复字符
      * @param str
      */
     public static void printAllPermutations1(String str) {
         char[] chars = str.toCharArray();
         int length = chars.length;
         process1(chars, 0, length);
     }

     private static void process1(char[] chars, int index, int length) {
         if (index == length) {
             System.out.println(String.valueOf(chars));
             return;
         }
         for (int i = index; i < length; ++i) {
             swap(chars, i, index); // 为第index位选择一个字符
             process1(chars, index + 1, length); // 全排剩下的字符
             swap(chars, i, index); // 还原char数组
         }
     }

     /**
     * 字符串全排列，字符串中含重复字符
     * 
     * @param str
     */
     public static void printAllPermutations2(String str) {
         char[] chars = str.toCharArray();
         int length = chars.length;
         process2(chars, 0, length);
     }

     public static void process2(char[] chars, int index, int length) {
         if (index == length) {
             System.out.println(String.valueOf(chars));
         }
         HashSet<Character> set = new HashSet<>(); // 每个index上有一个HashSet，判断该位置是否出现过某个字符
         for (int i = index; i < length; ++i) {
             if (!set.contains(chars[i])) {
                 set.add(chars[i]);
                 swap(chars, i, index);
                 process2(chars, index + 1, length);
                 swap(chars, i, index); // 还原char数组
             }
         }
     }

     private static void swap(char[] chars, int a, int b) {
         char temp = chars[b];
         chars[b] = chars[a];
         chars[a] = temp;
     }

     public static void main(String[] args) {
        String test1 = "abc";
        printAllPermutations1(test1);
        System.out.println("======");
        printAllPermutations2(test1);
        System.out.println("======");

        String test2 = "acc";
        printAllPermutations1(test2);
        System.out.println("======");
        printAllPermutations2(test2);
        System.out.println("======");
    }
 }
