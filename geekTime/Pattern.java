/**
 * 正则表达式
 * "*"匹配任意多个任意字符(>=0)
 * "?"匹配零个或者一个任意字符
 */

 public class Pattern {
     public static void match(char[] pattern, int cl, char[] string, int sl, int indexP, int indexS, boolean res) {
         if (res == true) {
             return;
         }
         if (indexP == cl && indexS == sl) {
             res = true;
             return;
         }
         if (pattern[indexP] == '*') {
             for (int i = indexS; i < sl; ++indexS) {
                 match(pattern, cl, string, sl, indexP + 1, i, res);
             }
         } else if(pattern[indexP] == '?') {
             match(pattern, cl, string, sl, indexP + 1, indexS, res);
             match(pattern, cl, string, sl, indexP + 1, indexS + 1, res);
         } else if(indexS < sl && pattern[indexP] == string[indexS]) {
             match(pattern, cl, string, sl, indexP + 1, indexS + 1, res);
         }
     }
 }