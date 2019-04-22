package class_8;

import java.util.ArrayList;
import java.util.List;

/**
 * 打印一个字符串的全部子序列， 包括空字符串
 */

 public class PrintAllSubsquences {
     /**
      * 定义一个String变量来储存字符串
      * @param str
      */
     public static void printAllSubsquences1(String str) {
         char[] chars = str.toCharArray();
         int length = chars.length;
         String sub = "";
         process(chars, 0, length, sub);
     }

     public static void process(char[] chars, int index, int length, String sub) {
         if (index == length) {
             System.out.println(sub);
             return;
         }
         process(chars, index + 1, length, sub + chars[index]);
         process(chars, index + 1, length, sub);
     }

     /**
      * 同上
      * @param str
      */
     public static void function(String str) {
         char[] chs = str.toCharArray();
         process3(chs, 0, new ArrayList<Character>());
     }

     public static void process3(char[] chs, int i, List<Character> res) {
         if (i == chs.length) {
             printList(res);
         }
         List<Character> resKeep = copyList(res);
         resKeep.add(chs[i]);
         process3(chs, i + 1, resKeep);
         List<Character> resNoInclude = copyList(res);
         process3(chs, i + 1, resNoInclude);
     }

     public static void printList(List<Character> res) {
         // ...;
     }

     public static List<Character> copyList(List<Character> list) {
         return null;
     }

     /**
      * 在原字符数组上进行操作
      * @param str
      */
     public static void printAllSubsquences2(String str) {
         char[] chs = str.toCharArray();
         process(chs, 0);
     }

     public static void process(char[] chs, int i) {
         if (i == chs.length) {
             System.out.println(String.valueOf(chs));
             return;
         }
         process(chs, i + 1);
         char tmp = chs[i];
         chs[i] = 0;
         process(chs, i + 1);
         chs[i] = tmp;//需要复原数组，否则后续递归需要使用的原数组时数组发生了变化
     }

     public static void main(String[] args) {
         String str = "abc";
         printAllSubsquences1(str);
         printAllSubsquences2(str);
     }
 }