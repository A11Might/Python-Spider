import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

/*
 * @lc app=leetcode.cn id=350 lang=java
 *
 * [350] 两个数组的交集 II
 * 
 * 1、nums1元素注册HashMap<Integer, Integer>，用nums2中元素查看是否注册过
 * 2、排序后，双指针判断元素是否相同
 */
class Solution {
    public int[] intersect(int[] nums1, int[] nums2) {
        HashMap<Integer, Integer> map = new HashMap<>();
        for (int num : nums1) {
            if (map.containsKey(num)) {
                map.put(num, map.get(num) + 1);
            } else {
                map.put(num, 1);
            }
        }
        List<Integer> list = new LinkedList<>();
        for (int num : nums2) {
            if (map.containsKey(num)) {
                if (map.get(num) == 0) {
                    map.remove(num);
                } else {
                    list.add(num);
                    map.put(num, map.get(num) - 1);
                }
            }
        }
        int[] res = new int[list.size()];
        int count = 0;
        for (int num : list) {
            res[count++] = num;
        }
        return res;
    }

    public int[] intersect2(int[] nums1, int[] nums2) {
        java.util.Arrays.sort(nums1);
        java.util.Arrays.sort(nums2);
        List<Integer> list = new LinkedList<>();
        int i = 0, j = 0;
        while (i < nums1.length && j < nums2.length) {
            if (nums1[i] == nums2[j]) {
                list.add(nums1[i]);
                i++;
                j++;
            } else if (nums1[i] < nums2[j]) {
                i++;
            } else {
                j++;
            }
        }
        int[] res = new int[list.size()];
        int count = 0;
        for (int num : list) {
            res[count++] = num;
        }
        return res;
    }
}

