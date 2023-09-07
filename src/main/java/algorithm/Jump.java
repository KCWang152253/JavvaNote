package algorithm;

/**
 * @author KCWang
 * @version 1.0
 * @date 2023/8/30 下午2:04
 */
public class Jump {
    public static void main(String[] args) {

        System.out.println(canJump(new int[]{2, 3, 1, 1, 4}));
    }

    /**
     * 动态规划
     * 如果i可达target,j可达i,那么j可达target
     */
    public static boolean canJump(int[] nums) {
        int target = nums.length - 1;
        if (target == 0) {
            return true;
        }
        // dp表,表示下标i是否可达下标target
        boolean[] dp = new boolean[nums.length - 1];

        for (int i = target - 1; i >= 0; i--) {
            int step = nums[i];
            // 无法跳跃直接返回
            if (step == 0) {
                continue;
            }
            // 直接可达下标target
            if (i + step >= target) {
                dp[i] = true;
                continue;
            }

            for (int j = i + 1; j <= step + i; j++) {
                // 间接可达下标target
                if (dp[j]) {
                    dp[i] = true;
                    break;
                }
            }

        }

        return dp[0];
    }

    /**
     * 回溯算法(超时)
     */
    public static boolean canJump2(int[] nums) {
        // 如果已经达到目标直接返回
        if (nums.length == 1) {
            return true;
        }

        return cupidity(0, nums.length - 1, nums);
    }


    /**
     * 遇到0判断是否可以跳过,不可以则为false
     */
    public static boolean canJump3(int[] nums) {
        if (nums.length == 1) {
            return true;
        }

        int target = nums.length - 1;
        // 运行到哪个i就表示i可达
        for (int i = 0; i < nums.length; i++) {
            // i可达,加上步长到达目标直接返回true
            if (nums[i] + i >= target) {
                return true;
            }
            // i可达但是为0,查看是否可以跳过
            if (nums[i] == 0) {
                boolean isStep = false;
                for (int j = 0; j < i; j++) {
                    if (j + nums[j] > i) {
                        isStep = true;
                        break;
                    }
                }
                // 无法跳过返回false
                if (!isStep) {
                    return false;
                }
            }
        }
        return true;
    }

    /**
     * 贪心
     * 每次获取最大可达距离
     */
    public static boolean canJump4(int[] nums) {
        int target = nums.length - 1;
        int rightMax = 0;
        for (int i = 0; i < nums.length; i++) {
            // 小于rightMax都是可达的
            if (rightMax >= i) {
                rightMax = Math.max(nums[i] + i, rightMax);
                // 当可达距离>=目标直接返回true
                if (rightMax >= target) {
                    return true;
                }
            } else {
                return false;
            }
        }

        return false;
    }

    /**
     * 动态规划的状态压缩
     */
    public static boolean canJump5(int[] nums) {
        int target = nums.length - 1;
        int dp = target;

        for (int i = target - 1; i >= 0; i--) {
            int step = nums[i];
            // 当下标i可达target的时候判断下标i是否可达
            if (i + step >= dp) {
                dp = i;
            }
        }
        //下标0是否可达target
        return dp == 0;
    }

    public static boolean cupidity(int cur, int target, int[] nums) {

        if (cur >= target) {
            return true;
        }

        if (nums[cur] == 0) {
            return false;
        }

        int step = nums[cur];
        while (!cupidity(cur + step, target, nums)) {
            step -= 1;
            if (step == 0) {
                return false;
            }
            cupidity(cur + step, target, nums);
        }

        return true;
    }


    /***
     * @Description 大部分都是这个思路，贪婪算法，我们每次在可跳范围内选择可以使得跳的更远的位置。
     * @param nums
     * @Return int
     * @Author K.C.Wang
     * @Date 2023/9/7 上午10:11
     **/
    public int jump(int[] nums) {
        int end = 0;
        int maxPosition = 0;
        int steps = 0;
        for(int i = 0; i < nums.length - 1; i++){
            //找能跳的最远的
            maxPosition = Math.max(maxPosition, nums[i] + i);
            //遇到边界，就更新边界，并且步数加一
            if( i == end){
                end = maxPosition;
                steps++;
            }
        }
        return steps;
    }

    /***
     * @Description
     * 我们知道最终要到达最后一个位置，然后我们找前一个位置，遍历数组，找到能到达它的位置，
     * 离它最远的就是要找的位置。然后继续找上上个位置，最后到了第 0 个位置就结束了。
     * 至于离它最远的位置，其实我们从左到右遍历数组，第一个满足的位置就是我们要找的。
     *
     * @param nums
     * @Return int
     * @Author K.C.Wang
     * @Date 2023/9/7 上午10:13
     **/
    public int jump2(int[] nums) {
        //要找的位置
        int position = nums.length - 1;
        int steps = 0;
        //是否到了第 0 个位置
        while (position != 0) {
            for (int i = 0; i < position; i++) {
                if (nums[i] >= position - i) {
                    //更新要找的位置
                    position = i;
                    steps++;
                    break;
                }
            }
        }
        return steps;
    }







}




