/**
 * 0-1背包问题
 * 背包的总承受重量是Wkg
 * 有n个物品，每个物品的重量不一样，并且不可分割
 * 在不超过背包所能装载的重量的前提下，如何让背包中的物品总重量最大
 */

 public class Bag {
     public static int maxW(int presentW, int index, int[] ws, int w, int n) {
         if (index == n || presentW == w) {
             return presentW;
         }
         int notAdd = maxW(presentW, index + 1, ws, w, n);
         if ((presentW + ws[index]) <= w) {
             return Math.max(notAdd, maxW(presentW + ws[index], index + 1, ws, w, n));
         }
         return notAdd;
     }
 }