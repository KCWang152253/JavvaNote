package algorithm.分治法;

/**
 * @author KCWang
 * @version 1.0
 * @date 2025/7/12 13:22
 */
public class 二分查找 {

    public static void main(String[] args) {

        int[] ints = {3, 4, 6, 1, 2, 5, 7};

        System.out.println("递归 结果 :"+binarysearch(ints,0,ints.length-1,5));
        System.out.println("非递归 结果 :"+bsearchWithoutRecursion(ints,5));
    }

    public static int binarysearch(int array[], int low, int high, int target) {
        if (low > high) return -1;
        int mid = low + (high - low) / 2;
        if (array[mid] > target)
            return binarysearch(array, low, mid - 1, target);
        if (array[mid] < target)
            return binarysearch(array, mid + 1, high, target);
        return mid;
    }



    public static int bsearchWithoutRecursion(int a[], int key) {
        int low = 0;
        int high = a.length - 1;
        while (low <= high) {
            int mid = low + (high - low) / 2;
            if (a[mid] > key)
                high = mid - 1;
            else if (a[mid] < key)
                low = mid + 1;
            else
                return mid;
        }
        return -1;
    }

}
