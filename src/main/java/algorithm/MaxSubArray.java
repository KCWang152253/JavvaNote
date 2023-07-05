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

    public static void main(String[] args) {
        int[] a = {1, 4, 6, 8, -9, 3, 5, -6, 4, 2};
        System.out.println(findMax(a));
        int[] ints = finaTwoMax(a, 2);
        for (int i = 0; i < ints.length; i++) {
            System.out.println(ints[i]);
        }
        Arrays.stream(finaTwoMax(a,2)).peek(s -> System.out.println(s)).mapToObj(String::valueOf).collect(Collectors.joining("-"));


        String target  = "abcdefghaa";
        System.out.println(findSunString(target));
    }


   /***
    * @Description 连续序列的最大子集
    * @param  a
    * @Return int
    * @Author K.C.Wang
    * @Date 2023/7/1 下午12:16
    **/
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
    /***
     * @Description [数组] 两数之和等于K
     * @param a
     * @param target
     * @Return int[]
     * @Author K.C.Wang
     * @Date 2023/7/1 下午12:49
     **/
    public static int[] finaTwoMax(int[] a,int target){
        HashMap<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < a.length; i++) {
            //计算预期差值
           int perfect = target -a[i];
           if (map.containsKey(perfect)){
               return new int[]{map.get(perfect),i};
           }else {
               map.put(a[i],i);
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
    public  static  String findSunString(String s){
        String buffer = new String();
        String target ;
        String[] strings = s.split("");
        HashMap map = new HashMap<>();
        if(strings.length==1){
            return  strings[0];
        }
        target=(strings[0]);
        map.put(strings[0],0);
        for (int i = 1; i < strings.length-1; i++) {
            String res = strings[i];
            if(!map.containsKey(strings[i])){
                map.put(res,i);
                target+=(strings[i]);
            }else {
                if(target.length()>buffer.length()){
                    buffer =target;
                }
                target = strings[i+1];
                map.clear();
                map.put(res,i+1);
            }
        }

        return buffer;

    }
}
