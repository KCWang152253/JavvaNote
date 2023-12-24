package baiduyun.juc.cf;


import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @auther zzyy
 * @create 2021-03-02 11:56
 */
public class CompletableFutureDemo
{
    public static void main(String[] args)throws Exception
    {
        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(1, 20, 1L, TimeUnit.SECONDS, new LinkedBlockingQueue<>(50), Executors.defaultThreadFactory(), new ThreadPoolExecutor.AbortPolicy());



        threadPoolExecutor.shutdown();
    }
}
