package algorithm.排序;


import java.util.Arrays;

public class 冒泡排序 {

    /**
     * 算法步驟
     * <p>
     * 1、比较相邻的元素，如果第一个比第二个大，就交换它们两个；
     * 2、对每一对相邻元素作同样的比价，从开始第一对到结尾的最后一对，这样在最后的元素就是最大的数；
     * 3、针对所有的元素重复以上的步骤，除了数组最后已经排好序的数组；
     * 4、重复步骤1~3，直到排序完成。
     *
     * @param args
     */

    public static void main(String[] args) {
        int[] ints = {1, 3, 4, 8, 5};
        bubbleSort(ints);
        System.out.println(Arrays.toString(ints));
    }

    public static void bubbleSort(int[] arr) {
        int len = arr.length;
        for (int i = 0; i < len - 1; i++) {
            boolean flag = true;
            for (int j = 0; j < len - i - 1; j++) {
                if (arr[j] > arr[j + 1]) {
                    int tmp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = tmp;
                    flag = false;
                }
            }
            if (flag) {
                break;
            }
        }
    }
}
