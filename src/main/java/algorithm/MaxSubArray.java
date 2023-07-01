package algorithm;

import java.util.Random;
import java.util.stream.IntStream;

import static java.lang.Integer.max;

/**
 * @author KCWang
 * @version 1.0 连续序列的最大子集
 * java 动态规划解决最大连续子数列和
 * 很多动态规划算法非常像数学中的递推。我们如果能找到一个合适的递推公式，就能很容易的解决问题。
 * 我们用dp[n]表示以第n个数结尾的最大连续子序列的和，这里第n个数必须在子序列中。于是存在以下递推公式：
 * 动态规划 d[n] = max(0,d[n-1])+a[n]
 * @date 2023/7/1 上午10:09
 */
public class MaxSubArray {


    public static void main(String[] args) {
        int[] a = {1, 4, 6, 8, -9, 3, 5, -6, 4, 2};
        System.out.println(findMax(a)) ;
    }

    public static int findMax(int[] a) {
        if (a.length == 1) {
            return a[0];
        }
        int maxRes = a[0];
        for (int i = 1; i < a.length; i++) {
            a[i] = max(a[i - 1] + a[i], a[i]);
            if (maxRes < a[i]) {
                maxRes = a[i];
            }
        }
        return maxRes;
    }
}
