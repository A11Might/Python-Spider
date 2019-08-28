import java.util.Arrays;
import java.util.HashMap;
import java.util.Scanner;

/**
 * [网易，牛牛找工作]
 * 
 * 使用HashMap来记录难度和不超过该难度的最大报酬
 * 
 * 先把工作的难度和报酬映射到HashMap，再将人的能力也放到HashMap中，
 * 报酬可以先设为0，最后维护一个当前可以获得得最大报酬，按难度从小到
 * 大(所以需要先排序)更新HashMap，key为难度，value为不超过难度的最
 * 大报酬
 */

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int m = sc.nextInt();
        HashMap<Integer, Integer> map = new HashMap<>();
        int t1 = 0, t2 = 0;
        int[] friends = new int[m];
        int[] keys = new int[m + n];
        for (int i = 0; i < n; i++) {
            t1 = sc.nextInt();
            t2 = sc.nextInt();
            keys[i] = t1;
            map.put(t1, t2);
        }
        for (int i = 0; i < m; i++) {
            t1 = sc.nextInt();
            if (!map.containsKey(t1)) {
                map.put(t1, 0);
            }
            friends[i] = t1;
            keys[n + i] = t1;
        }
        Arrays.sort(keys);
        int max = 0;
        for (int i = 0; i < keys.length; i++) {
            max = Math.max(max, map.get(keys[i]));
            map.put(keys[i], max);
        }
        for (int i = 0; i < friends.length; i++) {
            System.out.println(map.get(friends[i]));
        }
    }
}
