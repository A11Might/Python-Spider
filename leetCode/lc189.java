/*
 * @lc app=leetcode.cn id=189 lang=java
 *
 * [189] 旋转数组
 */
class Solution {
    public void rotate(int[] nums, int k) {
        int n = nums.length;
        if (nums == null || n < 2) {
            return;
        }
        k %= n;
        while (k > 0) {
            int temp = nums[nums.length - 1];
            for (int i = nums.length - 2; i >= 0; --i) {
                nums[i + 1] = nums[i];
            }
            nums[0] = temp;
            k--;
        }
    }

//     arr=[1,2,3,4,5]--右移两位-->[4,5,1,2,3]
//     假设 n = arr.length，k=右移位数，可得：[1,2,3,4,5]--翻转索引为[0,n-1]之间的元素-->[5,4,3,2,1]--翻转索引为[0,k-1]之间的元素-->[4,5,3,2,1]--翻转索引为[k,n-1]之间的元素-->[4,5,1,2,3]
//     旋转数组其实就是把数组分成了两部分，
//     解题关键就是在保证原有顺序的情况下 把后面一部分移到前面去。数组整体翻转满足了第二个要素，
//     但是打乱了数组的 原有顺序，所以此时再次对两部分进行翻转，让他们恢复到原有顺序（翻转之后
//     再翻转，就恢复成原有顺序了）。
    /**
     * 翻转 时间复杂度：O(n) 空间复杂度：O(1)
     */
    public void rotate_2(int[] nums, int k) {
        int n = nums.length;
        k %= n;
        reverse(nums, 0, n - 1);
        reverse(nums, 0, k - 1);
        reverse(nums, k, n - 1);
    }

    private void reverse(int[] nums, int start, int end) {
        while (start < end) {
            int temp = nums[start];
            nums[start++] = nums[end];
            nums[end--] = temp;
        }
    }

    /**
     * 循环交换 时间复杂度：O(n) 空间复杂度：O(1)
     */
    public void rotate_3(int[] nums, int k) {
        int n = nums.length;
        k %= n;
        // 第一次交换完毕后，前 k 位数字位置正确，后 n-k 位数字中最后 k 位数字顺序错误，继续交换
        for (int start = 0; start < nums.length && k != 0; n -= k, start += k, k %= n) {
            for (int i = 0; i < k; i++) {
                swap(nums, start + i, nums.length - k + i);
            }
        }
    }

    private void swap(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }

}

