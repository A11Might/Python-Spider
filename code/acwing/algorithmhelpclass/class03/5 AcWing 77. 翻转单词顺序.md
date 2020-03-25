[77. 翻转单词顺序](https://www.acwing.com/problem/content/73/)

#### 算法：

最优解时间复杂度 O(n)，空间复杂度 O(1)。

翻转整个字符串，再翻转每个单词。

#### 时间复杂度分析：



#### 代码：

```java
class Solution {
    public String reverseWords(String s) {
        int len = s.length();
        char[] chrs = s.toCharArray();
        reverse(chrs, 0, len - 1);
        for (int i = 0; i < len; i++) {
            int j = i;
            while (j + 1 < len && chrs[j + 1] != ' ') j++;
            reverse(chrs, i, j);
            i = j + 1;
        }
        return new String(chrs);
    }
    
    private void reverse(char[] chrs, int l, int r) {
        while (l < r) {
            char temp = chrs[r];
            chrs[r--] = chrs[l];
            chrs[l++] = temp;
        }
    }
}
```

