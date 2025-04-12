package algorithm.排序;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;


public class 桶排序 {

    /**
     *
     *


     算法步驟

     1、设置一个bucketSize（该数值的选择对性能至关重要，性能最好时每个桶都均匀放置所有数值，反之最差），表示每个桶最多能放置多少个数值；
     2、遍历输入数据，并且把数据依次放到到对应的桶里去；
     3、对每个非空的桶进行排序，可以使用其它排序方法（这里递归使用桶排序）；
     4、从非空桶里把排好序的数据拼接起来即可。
     *
     *
     *
     *
     *
     */

    public static void main(String[] args) {
        Integer[] ints = {3, 4, 6, 1, 2, 4, 7};
        List<Integer> list = Arrays.asList(ints);
        List<Integer> integers = bucketSort(list, 2);
        System.out.println(integers);
    }

        private static List<Integer> bucketSort(List<Integer> arr, int bucketSize) {
            int len = arr.size();
            if (len < 2 || bucketSize == 0) {
                return arr;
            }
            int minVal = arr.get(0), maxVal = arr.get(0);
            for (int i = 1; i < len; i++) {
                if (arr.get(i) < minVal) {
                    minVal = arr.get(i);
                } else if (arr.get(i) > maxVal) {
                    maxVal = arr.get(i);
                }
            }
            int bucketNum = (maxVal - minVal) / bucketSize + 1;

            List<List<Integer>> bucket = new ArrayList<>();
            for (int i = 0; i < bucketNum; i++) {
                bucket.add(new ArrayList<>());
            }
            for (int val : arr) {
                int idx = (val - minVal) / bucketSize;
                bucket.get(idx).add(val);
            }
            for (int i = 0; i < bucketNum; i++) {
                if (bucket.get(i).size() > 1) {
                    bucket.set(i, bucketSort(bucket.get(i), bucketSize / 2));
                }
            }

            List<Integer> result = new ArrayList<>();
            for (List<Integer> val : bucket) {
                result.addAll(val);
            }
            return result;
        }
    }

