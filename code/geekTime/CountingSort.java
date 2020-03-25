/**
 * 计数排序
 * @author 胡启航
 * @date 2019/3/16 - 20:58
 */
public class CountingSort {
    public static void countingSort(int[] a){
        int n = a.length;
        if(n < 2) return;
        countingSortInternally(a,n);
    }
    private static void countingSortInternally(int[] a,int n){
        int max = a[0];
        for (int i = 1; i < n; i++) {
            if(a[i] > max) max = a[i];
        }
        int[] c = new int[max + 1];//[0,max]
        for (int i = 0; i < n; i++) {
            c[a[i]]++;
        }
        for (int i = 1; i < max + 1; i++) {
            c[i] = c[i - 1] + c[i];
        }
        int[] r = new int[n];
        for (int i = 0; i < n; i++) {
            r[--c[a[i]]] = a[i];
        }
        for (int i = 0; i < n; i++) {
            a[i] = r[i];
        }
    }

    public static void main(String[] args){
        int[] a = {2,5,3,0,2,3,0,3};
        countingSort(a);
        for (int i : a) {
            System.out.print(i + " ");
        }
    }
}
