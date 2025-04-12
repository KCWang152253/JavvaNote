package algorithm.排序;


import java.util.Arrays;

public class 希尔排序 {

    /**
     * 算法步驟
     * <p>
     * 1、选择一个增量序列{t1, t2, …, tk}；
     * 2、按增量序列个数k，对序列进行k趟排序；
     * 3、每趟排序，根据对应的增量t，将待排序列分割成若干长度为m的子序列，分别对各子表进行直接插入排序。
     * 仅增量因子为1时，整个序列作为一个表来处理，表长度即为整个序列的长度。
     * <p>
     * 其中，增量gap=length/2，缩小增量继续以gap = gap/2的方式，这种增量选择我们可以用一个序列来表示，
     * {n/2, (n/2)/2, …, 1}，称为增量序列。一般的增量序列都选择以上说明的这个，但不一定是最优的。
     *
     * @param arr
     */

    public static void main(String[] args) {
        int[] ints = {3, 4, 6, 1, 2, 4, 7};
        shellSort(ints);
        System.out.println(Arrays.toString(ints));
    }

    public static void shellSort(int[] arr) {
        int len = arr.length, tmp, j;
        for (int gap = len / 2; gap >= 1; gap = gap / 2) {
            for (int i = gap; i < len; i++) {
                tmp = arr[i];
                j = i - gap;
                while (j >= 0 && arr[j] > tmp) {
                    arr[j + gap] = arr[j];
                    j -= gap;
                }
                arr[j + gap] = tmp;
            }
        }
    }
}
