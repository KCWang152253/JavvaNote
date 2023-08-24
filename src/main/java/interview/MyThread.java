package interview;

import lombok.SneakyThrows;

/**
 * @author KCWang
 * @version 1.0
 * @date 2023/8/24 上午8:33
 */
public class MyThread implements Runnable{
    @SneakyThrows
    @Override
    public void run() {


        Thread.sleep(1000);
        System.out.println("多线程调试"+Thread.currentThread().getName());
    }
}
