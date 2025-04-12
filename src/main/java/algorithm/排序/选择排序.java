package algorithm.排序;


import java.util.Arrays;

public class 选择排序 {

    /**
     *
     *

     算法步驟

     1、首先在未排序序列中找到最小（大）元素，存放到排序序列的起始位置；
     2、再从剩余未排序元素中继续寻找最小（大）元素，然后放到已排序序列的末尾；
     3、重复第2步，直到所有元素均排序完毕。

     *
     *
     *
     * @param args
     */
    public static void main(String[] args) {
        int[] ints = {3, 4, 6, 1, 2, 4, 7};
        selectionSort(ints);
        System.out.println(Arrays.toString(ints));
    }

    public static void selectionSort(int[] arr) {
        int len = arr.length;
        for (int i = 0; i < len - 1; i++) {
            int minVal = i;
            for (int j = i + 1; j < len; j++) {
                if (arr[minVal] > arr[j]) {
                    minVal = j;
                }
            }
            if (minVal != i) {
                int tmp = arr[i];
                arr[i] = arr[minVal];
                arr[minVal] = tmp;
            }
        }
    }
}
