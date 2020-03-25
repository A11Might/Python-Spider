/**
 * 冒泡排序、插入排序、选择排序
 * @author 胡启航
 * @date 2019/3/14 - 19:51
 */
public class Sorts {
    public static void bubbleSort(int[] a){
        if(a.length < 2) return;
        for (int i = 0; i < a.length - 1; i++) {
            boolean flag = true;
            for (int j = 0; j < a.length - i - 1; j++) {
                if(a[j] > a[j + 1]){
                    int temp = a[j];
                    a[j] = a[j + 1];
                    a[j + 1] = temp;
                    flag = false;
                }
            }
            if(flag) return;
        }
    }

    public static void insertionSort(int[] a){
        if(a.length < 2) return;
        for (int i = 1; i < a.length; i++) {
            int value = a[i];
            int j = i;
            for (; j > 0; j--) {
                if(a[j - 1] > value){
                    a[j] = a[j - 1];
                }else break;
            }
            a[j] = value;
        }
    }

    public static void selectionSort(int[] a){
        if(a.length < 2) return;
        for (int i = 0; i < a.length - 1; i++) {
            int min = i;
            for (int j = i + 1; j < a.length; j++) {
                if(a[min] > a[j]){
                    min = j;
                }
            }
            int temp = a[i];
            a[i] = a[min];
            a[min] = temp;
        }
    }
}
