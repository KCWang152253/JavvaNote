package algorithm;

import java.util.ArrayList;
import java.util.List;

/**
 * @author KCWang  排列组合
 * @version 1.0
 * @date 2024/12/5 下午8:49
 */
public class Test {

    public static void main(String[] args) {

        List list = new Test().getList(4, 2);
        System.out.println(list);
    }

    private List getList(int n,int k){
        ArrayList<int[]> list = new ArrayList<>();
        for (int i = 1; i <k +1; i++) {
            int[] tep = new int[k];
            for (int j = i; j < n+1; j++) {
                if(tep.length==k){
                    list.add(tep);
                    break;
                }
                for (int l = 0; l < i; l++) {
                    if(j!=tep[l]){
                        tep[i-1]=j;
                    }
                }
            }
        }

        return list;
    }
}
