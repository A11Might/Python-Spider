/**
 * 归并排序
 * @author 胡启航
 * @date 2019/3/15 - 20:54
 */
public class MergeSort {
    public static void mergeSort(int[] a){
        int n = a.length;
        mergeSortInternally(a,0,n - 1);
    }
    private static void mergeSortInternally(int[] a,int lo,int hi){
        if(lo >= hi) return;
        int mid = lo + (hi - lo) / 2;
        mergeSortInternally(a,lo,mid);
        mergeSortInternally(a,mid + 1,hi);
        merge(a,lo,mid,hi);
    }
    private static void merge(int[] a,int lo,int mid,int hi){
        int i = lo;
        int j = mid + 1;
        int k = 0;
        int[] temp = new int[hi - lo + 1];
        while(i <= mid && j <= hi){
            if(a[i] <= a[j]){
                temp[k++] = a[i++];
            }else{
                temp[k++] = a[j++];
            }
        }
        int start = i;
        int end = mid;
        if(j <= hi){
            start = j;
            end = hi;
        }
        while(start <= end){
            temp[k++] = a[start++];
        }
        for (int t : temp) {
            a[lo++] = t;
        }
    }
}
