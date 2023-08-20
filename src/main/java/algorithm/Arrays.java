package algorithm;

/**
 * @author KCWang
 * @version 1.0
 * @date 2023/8/19 下午5:41
 */
public class Arrays {

    /**给你一个有序数组 nums ，请你 原地 删除重复出现的元素，使得出现次数超过两次的元素只出现两次 ，返回删除后数组的新长度。
     不要使用额外的数组空间，你必须在 原地 修改输入数组 并在使用 O(1) 额外空间的条件下完成。
     *
     */
    public static void main(String[] args) {
//        java.util.Arrays.asList(new Arrays().newArray(new int[]{2,3,5,5,5,5,8,9})).stream().peek((s) -> System.out.println(s));

        System.out.println(new Arrays().newArray(new int[]{2, 3, 5, 5, 5, 5, 8, 9}));

        //关于「删除有序数组重复项」的通解
        new Arrays().process(new int[]{2, 3, 5, 5, 5, 5, 8, 9}, 2);
        int[] ints = {2, 3, 5, 5, 5, 5, 8, 9};


    }

    private  int newArray(int[] data) {

        int p0 = 0;
        int p1 = 0;
        int p2 = 1;
        while (p2<data.length){
            if(data[p1]==data[p2]&&p2-p1==1){
                p0++;
                data[p0]=data[p2++];
            }else if(data[p1]==data[p2]&&p2-p1>=2){
                data[p0]=data[p2++];
            }else if(data[p1]<data[p2]){
                p0++;
                p1 =p2;
                data[p0]=data[p2++];
            }
        }
        return p0+1;
    }



    /**
     * 关于「删除有序数组重复项」的通解
     * 为了让解法更具有一般性，我们将原问题的「保留 2 位」修改为「保留 k 位」。
     * @param nums
     * @param k
     * @return
     */
    int process(int[] nums, int k) {
        int u = 0;
        for (int x : nums) {
            if (u < k || nums[u - k] != x){
                nums[u++] = x;
            }
        }
        return u;
    }


}
