package algorithm.排序;

import java.util.Arrays;


/**
 * 算法步驟
 * <p>
 * 1、如果待排序列只有一个元素，则直接返回，否则将长度为n的待排序列分成两个长度为n/2的子序列，递归进行调用进行分割知道每个子序列中只有一个元素；
 * 2、此时的每个子序列被认为是有序的，然后递归调用的返回子序列进行两两合并；
 * 3、合并过程中完成排序操作，具体操作为设定两个指针，分别指向两个已经排序子序列的起始位置；
 * 4、比较两个指针所指向的元素，选择相对小的元素放入到合并返回的数组，并移动指针到下一位置；
 * 5、重复步骤3~4直到某一指针达到序列尾；
 * 6、将另一序列剩下的所有元素直接复制到合并序列尾，最终得到的新序列就是有序序列。
 */


public class 归并排序 {

    public static void main(String[] args) {
        int[] ints = {3, 4, 6, 1, 2, 4, 7};
        mergeSort(ints);
        System.out.println(Arrays.toString(ints));
    }

    public static int[] mergeSort(int[] arr) {
        int len = arr.length;
        if (len < 2) {
            return arr;
        }

        int mIdx = len / 2;
        return merge(mergeSort(Arrays.copyOfRange(arr, 0, mIdx)), mergeSort(Arrays.copyOfRange(arr, mIdx, len)));
    }

    private static int[] merge(int[] arrLeft, int[] arrRight) {
        int leftLen = arrLeft.length, rightLen = arrRight.length, leftIdx = 0, rightIdx = 0, idx = 0;
        int[] result = new int[leftLen + rightLen];
        while (leftIdx < leftLen && rightIdx < rightLen) {
            if (arrLeft[leftIdx] < arrRight[rightIdx]) {
                result[idx++] = arrLeft[leftIdx++];
            } else {
                result[idx++] = arrRight[rightIdx++];
            }
        }
        while (leftIdx < leftLen) {
            result[idx++] = arrLeft[leftIdx++];
        }
        while (rightIdx < rightLen) {
            result[idx++] = arrRight[rightIdx++];
        }
        return result;
    }
}

