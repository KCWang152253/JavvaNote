package algorithm;

import java.util.Arrays;

/**
 * @author KCWang
 * @version 1.0
 * @date 2023/9/8 上午10:22
 */
public class Hindex {

    public int hIndex(int[] citations) {
        Arrays.sort(citations);
        int h = 0, i = citations.length - 1;
        while (i >= 0 && citations[i] > h) {
            h++;
            i--;
        }
        return h;
    }

    public int hIndex_1(String[] args) {
        int[] data = new int[]{3,0,6,1,5};

        int length = data.length;
        int max = 0;
        int mid =0;
        boolean flag = true;
        while (flag){
            for (int i = 0; i <length ; i++) {
                if(data[i]>max){
                    mid++;
                }
                if(mid>max){
                    max = mid;
                    mid =0;
                    break;
                }
                if(i == length-1){
                    flag = false;
                }
            }
        }
        System.out.println(max);

        return max;
    }

}
