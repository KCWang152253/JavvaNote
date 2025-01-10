package algorithm.动态规划法;

import java.util.ArrayList;
import java.util.List;

/**
 * @author KCWang
 * @version 1.0
 * @date 2025/1/10 下午1:39
 */
public class 爬楼梯 {

    public static void main(String[] args) {
        System.out.println("爬楼梯算法-------------》》》》》》》》》》");
        System.out.println("爬楼梯-递归: "+ new 爬楼梯().climbStairs(10));
        System.out.println("爬楼梯-递归优化: "+ new 爬楼梯().climbStairsMemo(10));
        System.out.println("爬楼梯-动态规划: "+ new 爬楼梯().climbStairsDynamic(10));
        System.out.println("爬楼梯-数组滚动: "+ new 爬楼梯().climbStairsRoll(10));
    }

    /**
     1、暴力法 递归调用
         时间复杂度：2^n
         空间复杂度： n

     *
     */
    public int climbStairs(int n){
        if(n==1 || n==2){
            return n;
        }else {
            return climbStairs(n-1) +climbStairs(n-2);
        }

    }


    /**
     2、优化递归调用
     时间复杂度： n
     空间复杂度： n

     *
     */


    public int climbStairsMemo(int n){
        ArrayList<Integer> list = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            list.add(0);
        }

        return climbStairsMemo(n,list);
    }

    public  int climbStairsMemo(int n, List<Integer> list){
        if(list.get(n-1)!=0){
            return list.get(n-1);
        }
        if(n==1 ||n==2){
            return n;
        }else {
             list.set(n-1,climbStairsMemo(n-1)+climbStairsMemo(n-2));
            return list.get(n-1);
        }
    }



    /**
     3、动态规划 （实际上是递归方法的非递归实现）
     时间复杂度： n
     空间复杂度： n

     *
     */
    public  int climbStairsDynamic(int n){
        if (n==1){
            return 1;
        }
        int[] dp = new int[n];
        dp[0]=1;
        dp[1]=2;
        for (int i = 2; i < n; i++) {
            dp[i]=dp[i-1]+dp[i-2];
        }
        return dp[n-1];
    }


    /**
     4、数组滚动
     时间复杂度： n
     空间复杂度： 1

     *
     */
    public int climbStairsRoll(int n){
        if(n==1||n==2){
            return n;
        }
        int first = 1;
        int second = 2;
        int third = 0;
        for (int i = 3; i < n+1; i++) {
            third=first+second;
            first=second;
            second=third;
        }
        return third;
    }

    /**
     5、斐波那契额矩阵
     时间复杂度： log(n)
     空间复杂度： 1

     *
     */

}
