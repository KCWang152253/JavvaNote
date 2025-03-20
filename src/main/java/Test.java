import java.util.Arrays;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @author KCWang
 * @version 1.0
 * @date 2023/6/11 下午12:53
 */
public class Test {

    private volatile static Person person;


    public static void main(String[] args) {

        int[] ints = {1, 2, 3, 4};
        Arrays.asList(ints);
        new Test().getPerson();

        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(4, 5, 1, TimeUnit.SECONDS, new ArrayBlockingQueue(10, true));
        for (int i = 0; i < 10; i++) {
            threadPoolExecutor.execute(() -> System.out.println(Thread.currentThread().getName()));
        }
    }

    private Person getPerson() {
        if (person == null) {
            synchronized (Person.class) {
                if (person == null) {
                    person = new Person();
                }
            }
        }
        return person;
    }

    class Person {

    }
}




