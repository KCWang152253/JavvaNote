/**
 * @author KCWang
 * @version 1.0
 * @date 2023/8/15 下午4:37
 */
public class Max {

    public static void main(String[] args) {
        System.out.println(new Max().max());
    }
    private int max(){
        int[] a = {7,6,5,4,3};
        int max = a[0];
        int min = a[0];
        boolean flag = false;
        for (int i = 1; i < a.length; i++) {
            if (a[i]>=max ){
                max = a[i];
                flag = true;
            }else if(a[i]<=min && flag) {
                min = a[i];
                continue;
            }
        }
        return max-min;
    }
}
