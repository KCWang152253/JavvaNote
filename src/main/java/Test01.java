import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * @author KCWang
 * @version 1.0
 * @date 2025/1/13 上午10:18
 */
public class Test01 {





//
//    public static void main(String[] args) {
//        System.out.println(new Test01().findIndex("abcdabc"));
//
//    }
//
//    public int findIndex(String s){
//        String[] split = s.split("");
//        HashMap<String, Integer> res = new HashMap<>();
//        for (int i = 0; i < split.length; i++) {
//            if(StringUtils.isEmpty(res.get(split[i]))){
//                res.put(split[i],i);
//            }else {
//                res.remove(split[i]);
//            }
//        }
//        if(res.isEmpty()){
//            return -1;
//        }else {
//
//             return res.values().stream().sorted().findFirst().get();
//        }
//    }


    public static void main(String[] args) {
        System.out.println(new Test01().findNumber(new int[]{1,2,1,3,5,6,5,7,6}));

    }

    public List<Integer> findNumber(int[] a){
        List<Integer> res = new ArrayList<>();
        for (int i = 0; i < a.length; i++) {
            if(!res.contains(a[i]) && a[i]/2 !=0){
                res.add(a[i]);

            }
        }
        return res;
    }



}
