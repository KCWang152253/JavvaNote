package algorithm.排序;

import java.util.Arrays;

public class 计数排序 {

    /**
     *
     *


     算法步驟

     1、找出数组中的最大值maxVal和最小值minVal；
     2、创建一个计数数组countArr，其长度是maxVal-minVal+1，元素默认值都为0；
     3、遍历原数组arr中的元素arr[i]，以arr[i]-minVal作为countArr数组的索引，以arr[i]的值在arr中元素出现次数作为countArr[a[i]-min]的值；
     4、遍历countArr数组，只要该数组的某一下标的值不为0则循环将下标值+minVal输出返回到原数组即可。
     *
     *
     *
     */
    public static void main(String[] args) {
        int[] ints = {3, 4, 6, 1, 2, 4, 7};
        countingSort(ints);
        System.out.println(Arrays.toString(ints));
    }

        public static void countingSort(int[] arr) {
            int len = arr.length;
            if (len < 2) return;
            int minVal = arr[0], maxVal = arr[0];
            for (int i = 1; i < len; i++) {
                if (arr[i] < minVal) {
                    minVal = arr[i];
                } else if (arr[i] > maxVal) {
                    maxVal = arr[i];
                }
            }

            int[] countArr = new int[maxVal - minVal + 1];
            for (int val : arr) {
                countArr[val - minVal]++;
            }
            for (int arrIdx = 0, countIdx = 0; countIdx < countArr.length; countIdx++) {
                while (countArr[countIdx]-- > 0) {
                    arr[arrIdx++] = minVal + countIdx;
                }
            }
        }
    }

