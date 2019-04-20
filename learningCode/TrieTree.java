/**
 * 前缀树
 * 路径上是字符，节点上是次数
 */

 public class TrieTree {
     public static class TrieNode {
         public int path;//经过该节点的字符串个数
         public int end;//依该节点结尾的字符串个数
         public TrieNode[] nexts;//表示路径；如果字符集比较大的话，可以用哈希表：HashMap<char, TrieNode> nexts;

         public TrieNode() {
             path = 0;
             end = 0;
             nexts = new TrieNode[26];
         }
     }

     public static class Trie {
         private TrieNode root;

         public Trie() {
             root = new TrieNode();
         }

         public void insert(String str) {
             if (str == null) {
                 return;
             }
             char[] chars = str.toCharArray();
             TrieNode cur = root;
             int index = 0;
             for (char temp : chars) {
                 index = temp - 'a';
                 if (cur.nexts[index] == null) {
                     cur.nexts[index] = new TrieNode();
                 }
                 cur = cur.nexts[index];
                 ++cur.path;
             }
             ++cur.end;
         }

         public int search(String str) {
             if (str == null) {
                 return 0;
             }
             char[] chars = str.toCharArray();
             TrieNode cur = root;
             int index = 0;
             for (char temp : chars) {
                 index = temp - 'a';
                 if (cur.nexts[index] == null) {
                     return 0;
                 }
                 cur = cur.nexts[index];
             }
             return cur.end;
         }

         public void delete(String str) {
             if (search(str) != 0) {
                 char[] chars = str.toCharArray();
                 TrieNode cur = root;
                 int index = 0;
                 for (char temp : chars) {
                     index = temp - 'a';
                     if (--cur.nexts[index].path == 0) {
                         cur.nexts[index] = null;
                         return;
                     }
                     cur = cur.nexts[index];
                 }
                 --cur.end;
             }
         }

         public int prefixNumber(String pre) {
             if (pre == null) {
                 return 0;
             }
             char[] chars = pre.toCharArray();
             TrieNode cur = root;
             int index = 0;
             for (char temp : chars) {
                 index = temp - 'a';
                 if (cur.nexts[index] == null) {
                     return 0;
                 }
                 cur = cur.nexts[index];
             }
             return cur.path;
         }

         //for test
         public static void main(String[] args) {
            Trie trie = new Trie();
            System.out.println(trie.search("zuo"));
            trie.insert("zuo");
            System.out.println(trie.search("zuo"));
            trie.delete("zuo");
            System.out.println(trie.search("zuo"));
            trie.insert("zuo");
            trie.insert("zuo");
            trie.delete("zuo");
            System.out.println(trie.search("zuo"));
            trie.delete("zuo");
            System.out.println(trie.search("zuo"));
            trie.insert("zuoa");
            trie.insert("zuoac");
            trie.insert("zuoab");
            trie.insert("zuoad");
            trie.delete("zuoa");
            System.out.println(trie.search("zuoa"));
            System.out.println(trie.prefixNumber("zuo"));

        }
     }
 }
