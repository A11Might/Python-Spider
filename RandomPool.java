import java.util.HashMap;

/**
 * 设计RandomPool结构 设计一种结构，在该结构中有如下三个功能： insert(key)：将某个key加入到该结构，做到不重复加入。
 * delete(key)：将原本在结构中的某个key移除。 getRandom()：等概率随机返回结构中的任何一个key。 【要求】
 * Insert、delete和getRandom方法的时间复杂度都是 O(1)
 */

 public class RandomPool {
     public static class Pool<K> {
         private HashMap<K, Integer> keyIndexMap;
         private HashMap<Integer, K> indexKeyMap;
         private int size;

         public Pool() {
             keyIndexMap = new HashMap<>();
             indexKeyMap = new HashMap<>();
             size = 0;
         }

         public void insert(K key) {
             if (!keyIndexMap.containsKey(key)) {
                 keyIndexMap.put(key, size);
                 indexKeyMap.put(size++, key);
             }
         }

         public void delete(K key) {
             int removeIndex = keyIndexMap.get(key);
             int lastIndex = --size;
             K lastKey = indexKeyMap.get(lastIndex);
             keyIndexMap.put(lastKey, removeIndex);
             keyIndexMap.remove(key);
             indexKeyMap.put(removeIndex, lastKey);
             indexKeyMap.remove(lastIndex);
         }

         public K getRandom() {
             if (size == 0) {
                 return null;
             }
             int randomIndex = (int) (Math.random() * size);
             return indexKeyMap.get(randomIndex);
         }
     }

     //for test
     public static void main(String[] args) {
        Pool<String> pool = new Pool<String>();
        pool.insert("zuo");
        pool.insert("cheng");
        pool.insert("yun");
        System.out.println(pool.getRandom());
        System.out.println(pool.getRandom());
        System.out.println(pool.getRandom());
        System.out.println(pool.getRandom());
        System.out.println(pool.getRandom());
        System.out.println(pool.getRandom());

    }
 }
