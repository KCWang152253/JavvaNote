package algorithm;

import java.util.Arrays;
import java.util.HashMap;
import java.util.stream.Collectors;

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

    private int res = 0;

    public static void main(String[] args) {
        int[] a = {1, 4, 6, 8, -9, 3, 5, -6, 4, 2};

        System.out.println(new MaxSubArray().findMax(a));
        int[] ints = finaTwoMax(a, 2);
        if (ints != null) {
            for (int i = 0; i < ints.length; i++) {
                System.out.println(ints[i]);
            }
            Arrays.stream(finaTwoMax(a, 2)).peek(s -> System.out.println(s)).mapToObj(String::valueOf).collect(Collectors.joining("-"));
        }


        String target = "abcdefghaa";
        System.out.println(findSunString(target));

        //交易次数不限的最大值
        new MaxSubArray().maxProfit(new int[]{1, 4, 6, 8});
        System.out.println(new MaxSubArray().maxProfit_2(new int[]{1, 4, 6, 8}));
    }


    /***
     * @Description 连续序列的最大子集
     * @param  a
     * @Return int
     * @Author K.C.Wang
     * @Date 2023/7/1 下午12:16
     **/
    private int findMax(int[] a) {
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

    /***
     * @Description [数组] 两数之和等于K
     * @param a
     * @param target
     * @Return int[]
     * @Author K.C.Wang
     * @Date 2023/7/1 下午12:49
     **/
    public static int[] finaTwoMax(int[] a, int target) {
        HashMap<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < a.length; i++) {
            //计算预期差值
            int perfect = target - a[i];
            if (map.containsKey(perfect)) {
                return new int[]{map.get(perfect), i};
            } else {
                map.put(a[i], i);
            }
        }
        return null;
    }

    /***
     * @Description 连续字符串中没有相同字符的最大子字符串
     * @param s
     * @Return java.lang.String
     * @Author K.C.Wang
     * @Date 2023/7/1 下午8:16
     **/
    public static String findSunString(String s) {
        String buffer = new String();
        String target;
        String[] strings = s.split("");
        HashMap map = new HashMap<>();
        if (strings.length == 1) {
            return strings[0];
        }
        target = (strings[0]);
        map.put(strings[0], 0);
        for (int i = 1; i < strings.length - 1; i++) {
            String res = strings[i];
            if (!map.containsKey(strings[i])) {
                map.put(res, i);
                target += (strings[i]);
            } else {
                if (target.length() > buffer.length()) {
                    buffer = target;
                }
                target = strings[i + 1];
                map.clear();
                map.put(res, i + 1);
            }
        }

        return buffer.length() > target.length() ? buffer : target;

    }


    /***
     * @Description 买卖股票的最佳时机 II 一次交易最大值
     * @param
     * @Return int
     * @Author K.C.Wang
     * @Date 2023/8/15 下午9:16
     **/
    private int maxProfit_1() {
        int[] a = {7, 6, 5, 4, 3, 1};
        int max = a[0];
        int min = a[0];
        boolean flag = false;
        for (int i = 1; i < a.length; i++) {
            if (a[i] >= max) {
                max = a[i];
                flag = true;
            } else if (a[i] <= min && flag) {
                min = a[i];
                continue;
            }
        }
        return max - min;
    }


    public int maxProfit(int[] data) {
        if (data.length < 2) {
            return 0;
        }
        this.res = 0;
        maxProfit_2(data, 0, 0, 0);
        System.out.println(this.res);
        return this.res;
    }

    /***
     * @Description 交易次数不限的最大值
     * 「暴力搜索」在树形问题里也叫「回溯搜索」、「回溯法」。
     * @param data
     * @param index
     * @param status
     * @param profit
     * @Return void
     * @Author K.C.Wang
     * @Date 2023/8/20 下午6:20
     **/
    public void maxProfit_2(int[] data, int index, int status, int profit) {

        if (data.length < 2) {
            return;
        }
        if (data.length == index) {
            this.res = Math.max(this.res, profit);
            return;
        }
        maxProfit_2(data, index + 1, status, profit);
        if (status == 0) {
            maxProfit_2(data, index + 1, 1, profit - data[index]);
        } else {
            maxProfit_2(data, index + 1, 0, profit + data[index]);
        }
    }

    /***
     * @Description TODO
    方法二：动态规划（通用）
    第 1 步：状态 dp[i][j] 定义如下：

    dp[i][j] 表示到下标为 i 的这一天，持股状态为 j 时，我们手上拥有的最大现金数。

    注意：限定持股状态为 j 是为了方便推导状态转移方程，这样的做法满足 无后效性。

    第 2 步：思考状态转移方程
    状态从持有现金（cash）开始，到最后一天我们关心的状态依然是持有现金（cash）；
    每一天状态可以转移，也可以不动。状态转移用下图表示：
    第 3 步：确定初始值
    起始的时候：
    如果什么都不做，dp[0][0] = 0；
    如果持有股票，当前拥有的现金数是当天股价的相反数，即 dp[0][1] = -prices[i]；

    第 4 步：确定输出值
    终止的时候，上面也分析了，输出 dp[len - 1][0]，因为一定有 dp[len - 1][0] > dp[len - 1][1]。
     *
     *
     * @param
     * @Return 复杂度分析：
     *
     *  时间复杂度：O(N)O(N)O(N)，这里 NNN 表示股价数组的长度；
     *  空间复杂度：O(N)O(N)O(N)，虽然是二维数组，但是第二维是常数，与问题规模无关。
     *
     ** @Author K.C.Wang
     * @Date 2023/8/20 下午8:33
     **/
    private int maxProfit_2(int[] data) {
        if (data.length < 2) {
            return 0;
        }
        // 0：持有现金
        // 1：持有股票
        // 状态转移：0 → 1 → 0 → 1 → 0 → 1 → 0
        int[][] dp = new int[data.length][2];
        dp[0][0] = 0;
        dp[0][1] = -data[0];
        for (int i = 1; i < data.length; i++) {
            dp[i][0] = Math.max(dp[i - 1][0], dp[i - 1][1] + data[1]);
            dp[i][1] = Math.max(dp[i - 1][1], dp[i - 1][0] - data[1]);
        }
        return dp[data.length - 1][0];
    }

    /***
     * @Description TODO
    时间复杂度：O(N)O(N)O(N)，这里 NNN 表示股价数组的长度；
    空间复杂度：O(1)O(1)O(1)，分别使用两个滚动变量，将一维数组状态优化到常数大小。
     * @param data
     * @Return int
     * @Author K.C.Wang
     * @Date 2023/8/20 下午9:00
     **/
    private int maxProfit_3(int[] data) {
        if (data.length < 2) {
            return 0;
        }
        // 0：持有现金
        // 1：持有股票
        // 状态转移：0 → 1 → 0 → 1 → 0 → 1 → 0
        int[][] dp = new int[data.length][2];

        int cash = 0;
        int hold = -data[0];
        int precash = cash;
        int prehold = hold;
        for (int i = 1; i < data.length; i++) {
            cash = Math.max(precash, prehold + data[1]);
            hold = Math.max(prehold, precash - data[1]);
            precash = cash;
            prehold = hold;
        }
        return cash;
    }

}
