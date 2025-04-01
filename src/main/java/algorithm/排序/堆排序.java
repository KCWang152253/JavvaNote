package algorithm.排序;


import java.util.Arrays;

public class 堆排序 {

    private static int heapLen;

    public static void main(String[] args) {
        int[] ints = {3, 4, 6, 1, 2, 4, 7};
        heapSort(ints);
        System.out.println(Arrays.toString(ints));
    }


    public static void heapSort(int[] arr) {

        heapLen = arr.length;
        for (int i = heapLen - 1; i > 0; i--) {

            heapfy(arr, i);
        }

    }

    private static void heapfy(int[] arr, int idx) {

        int left = 2 * idx + 1, right = 2 * idx + 2, largest = idx;
        if (left < heapLen && arr[left] > arr[largest]) {
            largest = left;
        }
        if (right < heapLen && arr[right] > arr[largest]) {
            largest = right;
        }
        if (largest != idx) {
            swap(arr, largest, idx);
            heapfy(arr, largest);
        }

    }

    private static void swap(int[] arr, int idx1, int idx2) {

        int tep = arr[idx1];
        arr[idx1] = arr[idx2];
        arr[idx2] = tep;


    }

//    public static void heapSort(int[] arr) {
//        heapLen = arr.length;
//        for (int i = heapLen - 1; i >= 0; i--) {
//            heapify(arr, i);
//        }
//
//        for (int i = heapLen - 1; i > 0; i--) {
//            swap(arr, 0, heapLen - 1);
//            heapLen--;
//            heapify(arr, 0);
//        }
//    }
//
//    private static void heapify(int[] arr, int idx) {
//        int left = idx * 2 + 1, right = idx * 2 + 2, largest = idx;
//        if (left < heapLen && arr[left] > arr[largest]) {
//            largest = left;
//        }
//        if (right < heapLen && arr[right] > arr[largest]) {
//            largest = right;
//        }
//
//        if (largest != idx) {
//            swap(arr, largest, idx);
//            heapify(arr, largest);
//        }
//    }
//
//    private static void swap(int[] arr, int idx1, int idx2) {
//        int tmp = arr[idx1];
//        arr[idx1] = arr[idx2];
//        arr[idx2] = tmp;
//    }
}
