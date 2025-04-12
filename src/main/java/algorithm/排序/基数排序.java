package algorithm.排序;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class 基数排序 {

    /**
     * 算法步骤
     * <p>
     * 1、取得数组中的最大数，并取得位数，即为迭代次数n（例如：数组中最大数为123，则 n=3）；
     * 2、arr为原始数组，从最低位（或最高位）开始根据每位的数字组成radix数组（radix数组是个二维数组，其中一维长度为10），例如123在第一轮时存放在下标为3的radix数组中；
     * 3、将radix数组中的数据从0下标开始依次赋值给原数组；
     * 4、重复2~3步骤n次即可
     */

    public static void main(String[] args) {
        int[] ints = {3, 4, 6, 1, 2, 4, 7};
        radixSort(ints);
        System.out.println(Arrays.toString(ints));
    }

    //基数排序
    public static void radixSort(int[] arr) {
        if (arr.length < 2) return;
        int maxVal = arr[0];//求出最大值
        for (int a : arr) {
            if (maxVal < a) {
                maxVal = a;
            }
        }
        int n = 1;
        while (maxVal / 10 != 0) {//求出最大值位数
            maxVal /= 10;
            n++;
        }

        for (int i = 0; i < n; i++) {
            List<List<Integer>> radix = new ArrayList<>();
            for (int j = 0; j < 10; j++) {
                radix.add(new ArrayList<>());
            }
            int index;
            for (int a : arr) {
                index = (a / (int) Math.pow(10, i)) % 10;
                radix.get(index).add(a);
            }
            index = 0;
            for (List<Integer> list : radix) {
                for (int a : list) {
                    arr[index++] = a;
                }
            }
        }
    }
}

