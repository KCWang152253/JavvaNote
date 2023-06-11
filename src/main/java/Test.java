import designModle.Person;

/**
 * @author KCWang
 * @version 1.0
 * @date 2023/6/11 下午12:53
 */
public class Test {

    public static void main(String[] args) {

        Person person = Person.builder().age(2).sex("男").build();

        System.out.println(person);
    }
}
