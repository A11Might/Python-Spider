/**
 * 快速排序
 * @author 胡启航
 * @date 2019/3/15 - 21:53
 */
public class QuickSort {
    public static void quickSort(int[] a){
        int n = a.length;
        quickSortInternally(a,0,n - 1);
    }
    private static void quickSortInternally(int[] a,int lo,int hi){
        if(lo >= hi) return;
        int mid = partition(a,lo,hi);
        quickSortInternally(a,lo,mid - 1);
        quickSortInternally(a,mid + 1,hi);
    }
    private static int partition(int[] a,int lo,int hi){
        int pivot = a[hi];
        int i = lo;
        for (int j = lo; j < hi; j++) {
            if(a[j] < pivot) {
                if (i == j) i++;
                else{
                    int temp = a[i];
                    a[i] = a[j];
                    a[j] = temp;
                    i++;
                }
            }
        }
        int temp = a[i];
        a[i] = a[hi];
        a[hi] = temp;
        return i;
    }
}
