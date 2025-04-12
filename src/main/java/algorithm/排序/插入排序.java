package algorithm.排序;


import java.util.Arrays;

public class 插入排序 {


    /**
     *
     *


     算法步驟

     1、首先从第一个元素开始，该元素被认为是有序的；
     2、取出下一个元素，在已经排序的元素序列中从后往前进行扫描；
     3、如果该已排好序的元素大于新元素，则将该元素移到下一位置；
     4、重复步骤3一直往前进行扫描比较，直到找到已排序的元素小于或者等于新元素的位置；
     5、将新元素插入到该位置后；
     6、重复步骤2~5。


     *
     *
     *
     *
     * @param args
     */

    public static void main(String[] args) {
        int[] ints = {3, 4, 6, 1, 2, 4, 7};
        insertionSort(ints);
        System.out.println(Arrays.toString(ints));
    }
    public static void insertionSort(int[] arr) {
        for (int i = 1; i < arr.length; i++) {
            int val = arr[i], j = i;
            while (j > 0 && val < arr[j - 1]) {
                arr[j] = arr[j - 1];
                j--;
            }
            arr[j] = val;
        }
    }
}
